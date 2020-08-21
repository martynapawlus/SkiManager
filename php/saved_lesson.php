<?php
$servername="localhost";
$mysql_user="root";
$mysql_pass="";
$dbname="registration";
$conn=mysqli_connect($servername, $mysql_user, $mysql_pass, $dbname);
$instructor=$_POST['instructor'];
$email=$_POST['email'];
$year=$_POST['year'];
$month=$_POST['month'];
$day=$_POST['day'];
$hour=$_POST['hour'];
$rate='0';
$query="INSERT INTO `lessons`() VALUES ('$instructor', '$email', '$year', '$month', '$day', '$hour', '$rate')";
$result=mysqli_query($conn, $query);
?>
