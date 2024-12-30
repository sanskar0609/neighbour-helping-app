<?php
include 'db.php';
session_start();

if (!isset($_SESSION['user_id'])) {
    header('Location: login.html');
    exit;
}

// Initialize default region or saved region
$defaultLatitude = 19.1631; // Example: Nanded
$defaultLongitude = 77.3144;

$savedLatitude = $_SESSION['region']['latitude'] ?? $defaultLatitude;
$savedLongitude = $_SESSION['region']['longitude'] ?? $defaultLongitude;

// Handle form submission
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    // Handle the help request submission
    if (isset($_POST['title']) && isset($_POST['description']) && isset($_POST['category'])) {
        $title = $_POST['title'];
        $description = $_POST['description'];
        $category = $_POST['category'];
        $user_id = $_SESSION['user_id'];
        $latitude = $_POST['latitude'];
        $longitude = $_POST['longitude'];

        // Save the region in the session
        $_SESSION['region'] = ['latitude' => $latitude, 'longitude' => $longitude];

        // Insert help request into the database
        $sql = "INSERT INTO requests (title, description, category, user_id, latitude, longitude) 
                VALUES ('$title', '$description', '$category', '$user_id', '$latitude', '$longitude')";
        if ($conn->query($sql) === TRUE) {
            header('Location: dashboard.php');
            exit;
        } else {
            echo "Error: " . $conn->error;
        }
    }
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Post Help Request</title>
    <link rel="stylesheet" href="styles.css">
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css" />
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            text-align: center;
        }
        h1, h2 {
            margin: 20px 0;
            color: #333;
        }
        #map {
            height: 400px;
            width: 100%;
            margin-bottom: 20px;
        }
        form {
            display: inline-block;
            text-align: left;
            max-width: 400px;
            margin: auto;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        input, textarea, select {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        button {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
        #saved-region {
            margin-top: 20px;
            text-align: center;
            font-size: 18px;
            color: #333;
        }
    </style>
</head>
<body>
    <h1>Post Help Request</h1>
    <div id="map"></div>

    <form   method="POST">
        <label for="title">Request Title:</label>
        <input type="text" id="title" name="title" placeholder="Request Title" required>

        <label for="description">Request Details:</label>
        <textarea id="description" name="description" placeholder="Request Details" required></textarea>

        <label for="category">Category:</label>
        <select id="category" name="category">
            <option value="groceries">Groceries</option>
            <option value="repairs">Repairs</option>
            <option value="other">Other</option>
        </select>
        <input type="hidden" name="poster_id" value="<?php echo htmlspecialchars($_SESSION['user_id']); ?>">
    <input type="text" name="task_id" placeholder="Task ID">
    <input type="number" name="amount" placeholder="Amount">

        <label for="latitude">Latitude:</label>
        <input type="text" id="latitude" name="latitude" value="<?php echo htmlspecialchars($savedLatitude); ?>" readonly required>

        <label for="longitude">Longitude:</label>
        <input type="text" id="longitude" name="longitude" value="<?php echo htmlspecialchars($savedLongitude); ?>" readonly required>


        <button type="submit">Post Request</button>
    </form>

    <div id="saved-region">
        <p><strong>Saved Region:</strong></p>
        <p>Latitude: <?php echo htmlspecialchars($savedLatitude); ?></p>
        <p>Longitude: <?php echo htmlspecialchars($savedLongitude); ?></p>
    </div>

    <script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"></script>
    <script>
        // Initialize the map and set its view to the saved region or default
        const map = L.map('map').setView([<?php echo $savedLatitude; ?>, <?php echo $savedLongitude; ?>], 14);

        // Add OpenStreetMap tiles to the map
        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        }).addTo(map);

        // Add a draggable marker to the map
        const marker = L.marker([<?php echo $savedLatitude; ?>, <?php echo $savedLongitude; ?>], { draggable: true }).addTo(map);

        // Update latitude and longitude fields when the marker is dragged
        marker.on('moveend', (event) => {
            const { lat, lng } = event.target.getLatLng();
            document.getElementById('latitude').value = lat.toFixed(6); // Format to 6 decimal places
            document.getElementById('longitude').value = lng.toFixed(6);
        });
    </script>
</body>
</html>
