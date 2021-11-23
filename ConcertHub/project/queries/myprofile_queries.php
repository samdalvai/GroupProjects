<?php

if (isset($_POST["submitButton"])) {
    $oldpass = $_POST["oldPassword"];
    $newpass = $_POST["newPassword"];
    $confnewpass = $_POST["confirmNewPassword"];

    $my_profile_id = $_GET["userID"];

    include_once "../db/db-connector.php";
    include_once "./myprofileAcc_queries.php";

    if (!emptyInputSignup($oldpass, $newpass, $confnewpass, $my_profile_id)) {

        if (checkOldPassword($oldpass, $my_profile_id, $conn)) {}
 
        if(controllPwdMatch($newpass, $confnewpass)!== false){  
            header("location: ../userprofile.php?error=newPasswordDontMatch");  
            exit();
        }
        
        updatePassword($newpass,$my_profile_id, $conn);
        header("location: ../userprofile.php?action=passwordChangedSuccesfully");
    } else {
        header("location: ../userprofile.php?error=inputBoxesNeedToBeFilled");
    }
}
