<?php

require_once 'db.php';

//creating a query
	$stmt = $conn->prepare("SELECT order_id, elderly_name, elderly_age, elderly_phone, elderly_gender, elderly_height, elderly_weight, elderly_location, elderly_info, elderly_note, user_name FROM order_caregiver;");
	
	//executing the query 
	$stmt->execute();
	
	//binding results to the query 
	$stmt->bind_result($order_id, $elderly_name, $elderly_age, $elderly_phone, $elderly_gender, $elderly_height, $elderly_weight, $elderly_location, $elderly_info, $elderly_note, $user_name);
	
	$order_caregiver = array(); 
	
	//traversing through all the result 
	while($stmt->fetch()){
		$temp = array();
		$temp['order_id'] = $order_id; 
		$temp['elderly_name'] = $elderly_name; 
		$temp['elderly_age'] = $elderly_age;
		$temp['elderly_phone'] = $elderly_phone;
		$temp['elderly_gender'] = $elderly_gender; 
		$temp['elderly_height'] = $elderly_height;
		$temp['elderly_weight'] = $elderly_weight;
		$temp['elderly_location'] = $elderly_location;
		$temp['elderly_info'] = $elderly_info;
		$temp['elderly_note'] = $elderly_note;
		$temp['user_name'] = $user_name; 
		array_push($order_caregiver, $temp);
	}
	
	//displaying the result in json format 
	echo json_encode($order_caregiver);

?>