<?php 

if(isset($_POST["submit"])){
    $name = $_POST["name"];
    $email =  $_POST["email"];
    $username = $_POST["uid"];
    $pwd = $_POST["passWord"];
    $pwdrepeat = $_POST["passWordRepeat"];

    require_once "db-connector.php";
    require_once "functions/signup-functions.php";
    require_once "../mailSender.php";

    if(emptyInputSignup($name, $email, $username, $pwd, $pwdrepeat) !== false){
        header("location: ../signup.php?error=emptyInput");
        exit();
    }

    if(invalidEmail($email) !== false){
        header("location: ../signup.php?error=invalidEmail");
        exit();
    }

    if(pwdMatch($pwd, $pwdrepeat)!== false){
        header("location: ../signup.php?error=passwordDontMatch");
        exit();
    }

    if(userAlreadyExists($conn, $username, $email) !== false){
        header("location: ../signup.php?error=userAlreadyExists");
        exit();
    }
    
    createUser($conn,$name, $email, $username, $pwd);
    sendMailToRegistredUser($email,$name);
    header("location: ../login.php");
    exit();
   

}
else{
    header("location: signup.php");
    exit();
}

?>
