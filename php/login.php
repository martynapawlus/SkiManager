<?php
$servername="localhost";
$mysql_user="root";
$mysql_pass="";
$dbname="registration";
$conn=mysqli_connect($servername, $mysql_user, $mysql_pass, $dbname);
$email=$_POST['email'];
$haslo=$_POST['haslo'];
$query="SELECT `email`, `haslo` FROM `registration` WHERE email='$email' and haslo='$haslo'";
$result=mysqli_query($conn, $query);
if(mysqli_num_rows($result)>0){
echo("login success");
}else{
echo("login failed");
}
?>
