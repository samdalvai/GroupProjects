<?php

if(isset($_POST["submit"])){
    $username = $_POST["name"];
    $password = $_POST["password"];
    
    require_once "db-connector.php";
    require_once "functions/login-functions.php";

    if(emptyInputLogin($username, $password) !== false){
        header("location: ../login.php?error=emptyInput");
        exit();
    }

    if(loginUser($conn, $username, $password) !== false){
        header("location: ../login.php?error=wrongCredentials");
        exit();
    }
    
}
else{
    header("location: login.php");
    exit();
}

?>
