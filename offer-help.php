<?php
include 'db.php';
session_start();

if (!isset($_SESSION['user_id'])) {
    header('Location: login.html');
    exit;
}

// Initialize default region or saved region
$defaultLatitude = 19.1631; // Example: Default location (Nanded)
$defaultLongitude = 77.3144;

$savedLatitude = $_SESSION['region']['latitude'] ?? $defaultLatitude;
$savedLongitude = $_SESSION['region']['longitude'] ?? $defaultLongitude;

// Handle form submission to save the selected region
if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['latitude']) && isset($_POST['longitude'])) {
    $latitude = $_POST['latitude'];
    $longitude = $_POST['longitude'];

    // Save the selected region in the session
    $_SESSION['region'] = ['latitude' => $latitude, 'longitude' => $longitude];

    // Refresh saved region
    $savedLatitude = $latitude;
    $savedLongitude = $longitude;
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Offer Help</title>
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css" />
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color:rgba(205, 238, 249, 0.84);
       
        }
        header {
            background: linear-gradient(180deg, #f2f2f2 0%, #1adff5 100%);
            
            color: white;
            padding: 10px 20px;
        }
        nav a {
        color: #000000;
        text-decoration: none;
        margin: 0 15px;
        font-size: 16px;
        border-radius: 10px;
        border: 2px solid black;
        padding: 7px;
        background-color:rgb(34, 201, 209);
        animation-delay: 1s;
        transition: transform 0.3s ease, background-color 0.3s ease;
        display: inline-block;
      }
      nav{
        margin-top:10px;
      }
      nav a:hover{
        background-color: #f8f8f8;
        transform: scale(1.1);
       
      }
      header h1{
            color:rgb(30, 34, 38);
        }
        a{
            text-decoration:none;
        }

       
        main {
            padding: 20px;
            max-width: 800px;
            margin: auto;
             }
        #map {
            height: 400px;
            width: 100%;
            margin-bottom: 20px;
            border-radius:15px;
            border:5px solid black;
        }
        .request {
            background-color: #ffffff;
            border: 1px solid #ddd;
            border-radius: 16px;
            margin-bottom: 20px;
            border: 4px solid rgb(28, 145, 154);
            padding: 20px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        #saveb{
            width: 21vh;
            height: 5vh;
            align-item:center;
            border-radius:6px;
            background-color:rgb(34, 201, 209);
            transition: transform 0.3s ease, background-color 0.3s ease;
        display: inline-block;
        }
        #saveb:hover{
        background-color: #f8f8f8;
        transform: scale(1.1);
       
      }
        form{
            display: flex;
            justify-content:center;
            align-item:center;
        }
    </style>
</head>
<body>
<header>
    <center>
        <a href="dashboard.php">
    <h1
          style="
            font-size: 48px;
            font-weight: 700;
            margin: 0;
            z-index: 2;
            position: relative;
            
          "
        >
        <strong>Offer Help</Strong>
        </h1></a></center>
      
        <center>
        <nav>
            <a href="post-request.php">Post Request</a>
            <a href="offer-help.php">Offer Help</a>
            <a href="profile.php">profile</a>
            <a href="logout.php">Logout</a>
        </nav>
       </center>
    </header>
    <main>
        <h2>Select Your Location</h2>
        <div id="map"></div>
        <form method="POST">
            <input type="hidden" id="latitude" name="latitude" value="<?php echo htmlspecialchars($savedLatitude); ?>">
            <input type="hidden" id="longitude" name="longitude" value="<?php echo htmlspecialchars($savedLongitude); ?>">
            <button type="submit" id="saveb">Save Location</button>
        </form>

        <h2>Help Requests Near You</h2>

        <?php
// Fetch and display help requests
if (isset($_SESSION['region'])) {
    $user_lat = $_SESSION['region']['latitude'];
    $user_lng = $_SESSION['region']['longitude'];

    $sql = "SELECT requests.*, users.name AS user_name, 
                   (6371 * acos(cos(radians(?)) * cos(radians(latitude)) * 
                   cos(radians(longitude) - radians(?)) + 
                   sin(radians(?)) * sin(radians(latitude)))) AS distance 
            FROM requests
            JOIN users ON requests.user_id = users.id
            HAVING distance <= 1.0
            ORDER BY distance ASC";
    $stmt = $conn->prepare($sql);
    $stmt->bind_param("ddd", $user_lat, $user_lng, $user_lat);
    $stmt->execute();
    $result = $stmt->get_result();

    if ($result->num_rows > 0) {
        while ($row = $result->fetch_assoc()) {
            $escrow_id = $row['escrow_id'] ?? ''; // Use empty string if not set
            $provider_id = $row['provider_id'] ?? ''; // Use empty string if not set
            echo "<div class='request'>
                    <h3>" . htmlspecialchars($row['title']) . "</h3>
                    <p>" . htmlspecialchars($row['description']) . "</p>
                    <p><strong>Category:</strong> " . htmlspecialchars($row['category']) . "</p>
                    <p><strong>Requested by:</strong> " . htmlspecialchars($row['user_name']) . "</p>";

            // Show delete button only for the user's own requests
            if ($row['user_id'] == $_SESSION['user_id']) {
                echo "<form action='delete_request.php' method='POST' onsubmit='return confirm(\"Are you sure you want to delete this request?\");'>
                        <input type='hidden' name='request_id' value='" . htmlspecialchars($row['id']) . "'>
                        <button type='submit' class='delete-button'>Delete Request</button>
                      </form>
                      <form action='task_complete.php' method='POST'>
                        <input type='hidden' name='escrow_id' value='" . htmlspecialchars($escrow_id) . "'>
                        <label>Confirm Completion:</label>
                        <button type='submit' name='poster_confirmation' value='yes'>Yes</button>
                        <button type='submit' name='poster_confirmation' value='no'>No</button>
                      </form>";
            }

            // Offer Help button for all requests
            echo "<form action='messages.php' method='POST'>
                    <input type='hidden' name='request_id' value='" . htmlspecialchars($row['id']) . "'>
                    <button type='submit'>Offer Help</button>
                  </form>
                  <form action='task_accept.php' method='POST'>
                    <input type='hidden' name='escrow_id' value='" . htmlspecialchars($escrow_id) . "'>
                    <input type='hidden' name='provider_id' value='" . htmlspecialchars($provider_id) . "'>
                    <button type='submit'>Accept Task</button>
                  </form>
                  </div>";
        }
    } else {
        echo "<p>No requests found in your region within 1km radius.</p>";
    }
} else {
    echo "<p>Please select your region first.</p>";
}
?>


    </main>

    <script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"></script>
    <script>
        const map = L.map('map').setView([<?php echo $savedLatitude; ?>, <?php echo $savedLongitude; ?>], 13);

        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        }).addTo(map);

        const marker = L.marker([<?php echo $savedLatitude; ?>, <?php echo $savedLongitude; ?>], { draggable: true }).addTo(map);

        marker.on('moveend', (event) => {
            const { lat, lng } = event.target.getLatLng();
            document.getElementById('latitude').value = lat.toFixed(6);
            document.getElementById('longitude').value = lng.toFixed(6);
        });
    </script>
</body>
</html>
