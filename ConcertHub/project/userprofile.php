<?php
include_once "header.php";
require_once "./db/db-connector.php";
include_once './queries/myprofile_queries.php';
include_once "./queries/myprofileAcc_queries.php";
$my_profile_id =  $_SESSION["usersId"];
?>

<link rel="stylesheet" href="./styles/profile.css">
<link rel="stylesheet" href="./styles/login.css">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<?php
include_once "./errors/error-myprofile.php";
?>

<div class="container">
    <div class="main-body">
        <div class="row">
            <div class="col-lg-4">
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex flex-column align-items-center text-center">
                            <img src="./images/icons/user-icon.jpeg" alt="Admin" class="rounded-circle p-1 bg-primary" width="227.5" style="background-color: #000000!important">
                            <div class="mt-3">
                            <?php fullname_profile($conn, $_SESSION["usersId"]);
                                echo "<h4> $result_fullname_profile[0]</h4>" ?>
                                <p class="text-secondary mb-1">General User</p>
                            </div>
                        </div>
                        
                    </div>
                </div>
            </div>
            <div class="col-lg-8">
            <?php  echo  "<form class='card' action=./queries/myprofile_queries.php?userID=$my_profile_id  method='post'>"  ?>
                    <div class="card-body">
                        <div class="row mb-3">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Full Name</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <?php fullname_profile($conn, $_SESSION["usersId"]);
                                echo "<input type='text' class='form-control'  value=$result_fullname_profile[0] readonly>" ?>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Email</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                            <?php email_profile($conn, $_SESSION["usersId"]);
                            echo "<input type='text' class='form-control'  value=$result_email_profile[0] readonly>" ?>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Old password</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <input type='password' name='oldPassword' class='form-control'  value="" >
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-sm-3">
                                <h6 class="mb-0">New Password</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <input type='password' name='newPassword' class='form-control'  value="" >
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Confirm New Password</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <input type='password' name='confirmNewPassword' class='form-control'  value="" >
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-3"></div>
                            <div class="col-sm-9 text-secondary">
                                <button type="submit" name="submitButton" class="sign-up-button btn btn-dark">Sign-up</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<?php
include_once "footer.php";
?>