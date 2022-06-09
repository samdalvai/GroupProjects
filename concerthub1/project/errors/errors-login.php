<?php
if (isset($_GET["error"])) {
    if ($_GET["error"] == "emptyInput") {
        alert_pop_up("Empty imput", "Please enter all the fields...");
        unset($GLOBALS["error"]);
    } else if ($_GET["error"] == "userDoesntExist") {
        alert_pop_up("User does not exist", "Please check the username...");
        unset($GLOBALS["error"]);
    } else if ($_GET["error"] == "wrongPassword") {
        alert_pop_up("Wrong password", "Please check your password...");
        unset($GLOBALS["error"]);
    } else if ($_GET["error"] == "wrongCredentials") {
        alert_pop_up("Wrong credentials", "Please check user and password...");
        unset($GLOBALS["error"]);
    }
}

function alert_pop_up($cause, $solution){
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
