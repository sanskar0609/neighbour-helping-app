<?php
require "C:\Users\sansk\vendor\autoload.php"; // Load Composer's autoloader

// Set your Stripe Secret Key
\Stripe\Stripe::setApiKey('sk_test_51QZAZQFDu12sFaxLkSx6RbYSFiihYzyYYxfzXB1lPCCAwhbuXsB2UfS9FTpyNL1NPj5AQhmxBdn05PznyNTwlFFr00jJnudKZ0'); // Replace with your Secret Key

header('Content-Type: application/json');  // Ensure the response is in JSON format

try {
    // Create a Payment Intent
    $paymentIntent = \Stripe\PaymentIntent::create([
        'amount' => 1000, // Amount in cents (e.g., $10.00)
        'currency' => 'usd',
        'payment_method_types' => ['card'],
    ]);

    // Return the client secret as JSON
    echo json_encode([
        'clientSecret' => $paymentIntent->client_secret,
    ]);
    exit;  // Ensure no further code is executed

} catch (Exception $e) {
    // Handle errors and return a JSON error message
    http_response_code(500);
    echo json_encode(['error' => $e->getMessage()]);
}
