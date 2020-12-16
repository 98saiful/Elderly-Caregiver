<?php  

if($_SERVER	["REQUEST_METHOD"]=="POST"){
	require 'db.php';
	createOrder();
}

function createOrder(){
	global $conn;

	$elderly_name = $_POST["elderly_name"];
	$elderly_age = $_POST["elderly_age"];
	$elderly_phone = $_POST["elderly_phone"];
	$elderly_gender = $_POST["elderly_gender"];
	$elderly_height = $_POST["elderly_height"];
	$elderly_weight = $_POST["elderly_weight"];
	$elderly_location = $_POST["elderly_location"];
	$elderly_info = $_POST["elderly_info"];
	$elderly_note = $_POST["elderly_note"];
	$user_id = $_POST["user_id"];
	$user_name = $_POST["user_name"];

	$query = "Insert into order_caregiver(elderly_name,elderly_age,elderly_phone,elderly_gender,elderly_height,elderly_weight,elderly_location,elderly_info,elderly_note,user_id,user_name) values ('$elderly_name', '$elderly_age', '$elderly_phone', '$elderly_gender', '$elderly_height', '$elderly_weight', '$elderly_location', '$elderly_info', '$elderly_note', '$user_id', '$user_name');";

	mysqli_query($conn, $query) or die (mysqli_error($conn));
	mysqli_close($conn);


}

?>