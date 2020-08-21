<?php
$servername="localhost";
$mysql_user="root";
$mysql_pass="";
$dbname="registration";
$conn=mysqli_connect($servername, $mysql_user, $mysql_pass, $dbname);
$email=$_POST['email'];
$instructor=$_POST['instructor'];
$year=$_POST['year'];
$month=$_POST['month'];
$day=$_POST['day'];
$hour=$_POST['hour'];
$rate=$_POST['rate'];
$query="UPDATE `lessons` SET `rate`='$rate' WHERE `email`='$email' AND `instructor`='$instructor' AND `year`='$year' AND `month`='$month' AND `day`='$day' AND `hour`='$hour'";
$result=mysqli_query($conn, $query);
?>
