<?php

$db_name = "steem";
$mysql_user = "root";
$mysql_pass = "";
$server_name = "localhost";

$con = mysqli_connect($server_name,$mysql_user,$mysql_pass,$db_name);

$sql_statement = "select * from profiles;";

$result = mysqli_query($con,$sql_statement);

$response = array();

while ($row = mysqli_fetch_array($result)) {
	array_push($response,array("name"=>$row[0],"image"=>$row[1],"title"=>$row[2],"dob"=>$row[3],"pob"=>$row[4],"discoveries"=>$row[5]));
}

echo json_encode($response);

mysqli_close($con);

?>