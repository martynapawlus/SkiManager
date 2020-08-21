<?php
$servername="localhost";
$mysql_user="root";
$mysql_pass="";
$dbname="registration";
$conn=mysqli_connect($servername, $mysql_user, $mysql_pass, $dbname);
$instructor=$_POST['instructor'];
$year=$_POST['year'];
$month=$_POST['month'];
$day=$_POST['day'];
$hour=(int)$_POST['hour'];
$quantity=(int)$_POST['quantity'];
for($i = 0; $i < $quantity; $i++){
    $new_hour=strval($hour + $i);
    $query="UPDATE `instructor_date` SET `free_flag`='f' WHERE `instructor`='$instructor' AND `year`='$year' AND `month`='$month' AND `day`='$day' AND `hour`='$new_hour'";
    $result=mysqli_query($conn, $query);
    if($result){
        echo("lesson saved");
    }
}
?>
