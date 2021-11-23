<?php
    include_once "signup-functions.php";

    function emptyInputLogin($name, $password){
        $result = false;
        if(empty($name) || empty($password)){
            $result = true; 
        }
        return $result;
    }

    function loginUser($connection ,$username, $password){

        $userExist = userAlreadyExists($connection,$username,$username);
        if($userExist == false){
            header("location: ../login.php?error=userDoesntExist");
            exit();
        }

        $passwordHashed = $userExist["usersPassword"];
        $checkPassword= password_verify($password, $passwordHashed);

        if($checkPassword == false){
            header("location: ../login.php?error=wrongPassword");
            exit();
        }

        else if($checkPassword == true){
            session_start();
            $_SESSION["usersId"] = $userExist["usersId"];
            $_SESSION["usersUid"] = $userExist["usersUid"];
            
            header("location: ../index.php");
            exit();
        }
}



?>
