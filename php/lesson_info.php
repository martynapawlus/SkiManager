<?php
$servername="localhost";
$mysql_user="root";
$mysql_pass="";
$dbname="registration";
$conn=mysqli_connect($servername, $mysql_user, $mysql_pass, $dbname);
$email=$_POST['email'];
$rate='0';
$query="SELECT `instructor`, `year`, `month`, `day`, `hour` FROM `lessons` WHERE `email`='$email' AND `rate`='$rate'";
$result=mysqli_query($conn, $query);
if($result){
    while($row=mysqli_fetch_array($result)){
        $flag[]=$row;
    }
    print(json_encode($flag));
}
mysqli_close($conn);
?>
