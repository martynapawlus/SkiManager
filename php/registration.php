<?php
$servername="localhost";
$mysql_user="root";
$mysql_pass="";
$dbname="registration";
$conn=mysqli_connect($servername, $mysql_user, $mysql_pass, $dbname);
if($_SERVER['REQUEST_METHOD']=='POST'){
    $imie=$_POST["imie"];
    $nazwisko=$_POST["nazwisko"];
    $urodziny=$_POST["urodziny"];
    $urodziny_check="/(19[0-9][0-9]|20[01][1-9])+\.+(0[1-9]|1[012])+\.+(0[1-9]|1[0-9]|2[0-9]|3[01])/";
    $telefon=$_POST["telefon"];
    $telefon_check="/[0-9]{9}/";
    $email=$_POST["email"];
    $email_check="/[a-żA-Ż0-9\_\+-.]{3,}+@+[a-żA-Ż0-9\_\+-.]{2,}/";
    $haslo=$_POST["haslo"];
    if((preg_match($email_check, $email) == true) && (preg_match($urodziny_check, $urodziny) == true) && (preg_match($telefon_check, $telefon) == true)){
        $query="INSERT INTO `registration`(`imie`, `nazwisko`, `urodziny`, `telefon`, `email`, `haslo`) VALUES ('$imie', '$nazwisko', '$urodziny', '$telefon', '$email', '$haslo')";
        if(mysqli_query($conn, $query)){
            echo("registered successfully");
        }else{
            echo("error in registration");
        }
    }
}else{
echo("error in request method");
}
?>
