<?php

function emptyInputSignup($oldpassword,$newpassword, $confnewpassword){
    $result=true;
    if(!empty($oldpassword) && !empty($newpassword) && !empty($confnewpassword)){
        $result=false;
    }
    return  $result;
}

function email_profile($conn, $usersId){
    $sql = "SELECT usersEmail FROM users where usersId=$usersId";
    $result = $conn->query($sql);
   
    global $result_email_profile;
    $result_email_profile = [];

    if ($result->num_rows > 0) {
        while ($row = $result->fetch_assoc()) {
            array_push($result_email_profile, $row["usersEmail"]);
        }
    } else {
        echo "0 results";
    }
}


function fullname_profile($conn, $usersId){
    $sql = "SELECT usersName FROM users where usersId=$usersId";
    $result = $conn->query($sql);
   
    global $result_fullname_profile;
    $result_fullname_profile = [];

    if ($result->num_rows > 0) {
        while ($row = $result->fetch_assoc()) {
            array_push($result_fullname_profile, $row["usersName"]);
        }
    } else {
        echo "0 results";
    }
}


function controllPwdMatch($pwd,$pwdrepeat){
    $result=false;
    if($pwd !== $pwdrepeat){
        $result = true;
    }
    return $result;
}


function checkOldPassword($pwd, $usersId, $conn){
    $queryPass="SELECT * From users  WHERE usersId=$usersId";
    $result_profile_pass = $conn->query($queryPass);

    global $profile_changepsw;
    $profile_changepsw = [];   
    
    if ($result_profile_pass->num_rows > 0) {
        while ($row = $result_profile_pass->fetch_assoc()) {
            array_push($profile_changepsw, $row["usersPassword"]);
            echo "tttt";
        }
    } else {
        echo "0 results";
    }

    if (!password_verify($pwd,$profile_changepsw[0])) {  // When passwords entered is incorrect
        header("location: ../userprofile.php?error=oldPasswordIsNotCorrect");   
        exit();
    }
    else{
        echo "nothing";
    }
}

function updatePassword($newpassword, $usersId, $conn){
    $sql = "UPDATE users SET usersPassword=? WHERE usersId=$usersId;";
    $stmt= $conn->prepare($sql);

    if(!mysqli_stmt_prepare($stmt, $sql)){
        header("location: userprofile.php?error=newPasswordUpdateFailedToBeCompleted");
        exit();
    }

    $hashedPwd = password_hash($newpassword,PASSWORD_DEFAULT);
  
    mysqli_stmt_bind_param($stmt,"s", $hashedPwd); 
    mysqli_stmt_execute($stmt);
    mysqli_stmt_close($stmt);
}
?>