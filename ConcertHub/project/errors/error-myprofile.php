<?php 


if (isset($_GET["error"])) {
    if ($_GET["error"] == "oldPasswordIsNotCorrect") {
        alert_pop_up("Please enter correctly your actual password...", "");
        unset($GLOBALS["error"]);
    } else if ($_GET["error"] == "newPasswordDontMatch") {
        alert_pop_up("Please make sure that the new password that you entered match...", "");
        unset($GLOBALS["error"]);
    } else if ($_GET["error"] == "newPasswordUpdateFailedToBeCompleted") {
        alert_pop_up("Password change failed due to some internal error...","");
        unset($GLOBALS["error"]);
    }
    else if ($_GET["error"] == "inputBoxesNeedToBeFilled") {
        alert_pop_up("Please input all fields...","");
        unset($GLOBALS["error"]);
    } 
}

function alert_pop_up($cause, $solution)
{
    echo "<section class=\"container-sm padding-alert\">
    <div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\">
        <strong>$cause! </strong>$solution
        <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">
            <span aria-hidden=\"true\">&times;</span>
        </button>
    </div>
    </section>";
}

?>