<?php 

function emptyInputSignup($name,$email, $username,$pwd,$pwdrepeat){
    $result=false;
    if(empty($name) || empty($username) || empty($email) || empty($pwd) || empty($pwdrepeat)){
        $result=true;
    }
    return  $result;
}

function invalidEmail($email){
    $result=false;
    if(!filter_var($email,FILTER_VALIDATE_EMAIL)){
        $result=true;
    }

    return  $result;
}

function pwdMatch($pwd,$pwdrepeat){
    $result=false;
    if($pwd !== $pwdrepeat){
        $result = true;
    }
    return $result;
}

function userAlreadyExists($connection,$username,$email){
    $sql = "SELECT * FROM users WHERE usersUid = ? OR usersEmail = ?;";
    $stmt= mysqli_stmt_init($connection);
    if(!mysqli_stmt_prepare($stmt,$sql)){
        header("location: signup.php?error=stmtfailed");
        exit();
    }

    mysqli_stmt_bind_param($stmt,"ss",$username, $email);
    mysqli_stmt_execute($stmt);
    
    $resultData = mysqli_stmt_get_result($stmt);
    if($row = mysqli_fetch_assoc($resultData)){
        return $row;
    }
    else{
        $result=false;
        return $result;
    }

    mysqli_stmt_close($stmt);
}

function createUser($connection,$name, $email, $username, $pwd){
    $sql = "INSERT INTO users (usersName, usersEmail, usersUid, usersPassword ) VALUES (?, ?, ?, ?);";
    $stmt = mysqli_stmt_init($connection);
    if(!mysqli_stmt_prepare($stmt, $sql)){
        header("location: signup.php?error=stmtfailed");
        exit();
    }

    $hashedPwd = password_hash($pwd,PASSWORD_DEFAULT);
  
    mysqli_stmt_bind_param($stmt,"ssss",$name, $email, $username, $hashedPwd); 
    mysqli_stmt_execute($stmt);
    mysqli_stmt_close($stmt);
}

?>