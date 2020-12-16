<?php 

	
	//database constants
	$servername = "lrgs.ftsm.ukm.my";
	$username = "a166118";
	$password = "smallbluedonkey";
	$database = "a166118";

	$conn = new mysqli($servername, $username, $password, $database);
	
	//Checking if any error occured while connecting
	if (mysqli_connect_errno()) {
		echo "Failed to connect to MySQL: " . mysqli_connect_error();
		die();
	}
	
	//creating a query
	$stmt = $conn->prepare("SELECT id, title, subtitle, description, picture FROM guide;");
	
	//executing the query 
	$stmt->execute();
	
	//binding results to the query 
	$stmt->bind_result($id, $title, $subtitle, $description, $picture);
	
	$guide = array(); 
	
	//traversing through all the result 
	while($stmt->fetch()){
		$temp = array();
		$temp['id'] = $id; 
		$temp['title'] = $title; 
		$temp['subtitle'] = $subtitle; 
		$temp['description'] = $description; 
		$temp['picture'] = $picture; 
		array_push($guide, $temp);
	}
	
	//displaying the result in json format 
	echo json_encode($guide);