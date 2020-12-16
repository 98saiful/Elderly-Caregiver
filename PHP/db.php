<?php
 
$servername = "lrgs.ftsm.ukm.my";
$username = "a166118";
$password = "smallbluedonkey";
$database = "a166118";

$conn = mysqli_connect($servername, $username, $password, $database);
 

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
 
?>