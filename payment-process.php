<?php
// Include Stripe PHP library
require 'C:\Users\sansk\vendor\autoload.php';

// Set your Stripe secret key
\Stripe\Stripe::setApiKey('sk_test_51QZAZQFDu12sFaxLkSx6RbYSFiihYzyYYxfzXB1lPCCAwhbuXsB2UfS9FTpyNL1NPj5AQhmxBdn05PznyNTwlFFr00jJnudKZ0'); // Use your Stripe secret key

// Get the token sent by the frontend
$data = json_decode(file_get_contents('php://input'), true);
$token = $data['token'];
$amount = 5000;  // Example: Amount in cents (50 USD)

// Create PaymentIntent to hold funds
try {
    // Create a PaymentIntent with the "manual" capture method
    $paymentIntent = \Stripe\PaymentIntent::create([
        'amount' => $amount,  // Amount in cents
        'currency' => 'usd',
        'description' => 'Payment for Help Request/Offer',
        'payment_method' => $token, // Card token from the frontend
        'confirmation_method' => 'manual',  // Hold the funds
        'capture_method' => 'manual', // Funds are not captured yet
    ]);

    // Respond with the client secret to confirm payment
    echo json_encode(['clientSecret' => $paymentIntent->client_secret]);
} catch (\Stripe\Exception\ApiErrorException $e) {
    echo json_encode(['success' => false, 'error' => $e->getMessage()]);
}
?>
