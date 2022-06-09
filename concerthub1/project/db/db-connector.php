<?php

$servername = "127.0.0.1";
$username_db = "mamp";
$password_db = "";
$dbname = "test";

$conn = new mysqli($servername, $username_db, $password_db, $dbname);

if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
}

?>
