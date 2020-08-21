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
$query="SELECT `hour`, `free_flag` FROM `instructor_date` WHERE `instructor`='$instructor' AND `year`='$year' AND`month`='$month' AND `day`='$day'";
$result=mysqli_query($conn, $query);
if($result){
    while($row=mysqli_fetch_array($result)){
        $flag[]=$row;
    }
    print(json_encode($flag));
}
mysqli_close($conn);
?>
