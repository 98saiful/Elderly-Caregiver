<?php  

if($_SERVER	["REQUEST_METHOD"]=="POST"){
	require 'db.php';
	createOrder();
}

function createOrder(){
	global $conn;

	$caregiver_id = $_POST["caregiver_id"];
	$caregiver_name = $_POST["caregiver_name"];

	$query = "UPDATE 'order_caregiver' SET 
		'caregiver_id' = '$caregiver_id',
		'caregiver_name' = '$caregiver_name'
		WHERE order_id = '&order_id';

	mysqli_query($conn, $query) or die (mysqli_error($conn));
	mysqli_close($conn);


}

?>