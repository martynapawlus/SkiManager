<?php
$servername="localhost";
$mysql_user="root";
$mysql_pass="";
$dbname="registration";
$conn=mysqli_connect($servername, $mysql_user, $mysql_pass, $dbname);
$email=$_POST['email'];
$newPassword=$_POST['newPassword'];
$oldPassword=$_POST['oldPassword'];
$query1="SELECT `email`, `haslo` FROM `registration` WHERE `email`='$email' AND `haslo`='$oldPassword'";
$result1 = mysqli_query($conn, $query1);
$query="UPDATE `registration` SET `haslo`='$newPassword' WHERE `email`='$email' AND `haslo`='$oldPassword'";
$result=mysqli_query($conn, $query);
if(mysqli_num_rows($result1)>0){
    echo("password changed");
}else {
    echo("wrong password");
}
?>
