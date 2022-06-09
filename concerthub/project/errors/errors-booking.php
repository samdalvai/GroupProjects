<?php

if (isset($_GET["error"])) {
    if ($_GET["error"] == "emptyInput") {
        alert_pop_up("Empty imput", "Please enter all the fields...");
        unset($GLOBALS["error"]);
    } else if ($_GET["error"] == "invalidName") {
        alert_pop_up("Invalid name", "A name cannot contain numbers...");
        unset($GLOBALS["error"]);
    }
    else if ($_GET["error"] == "wrongCardNumber") {
        alert_pop_up("Invalid card number", "Number must be in the format XXXX-XXXX-XXXX-XXXX...");
        unset($GLOBALS["error"]);
    } else if ($_GET["error"] == "wrongCVV") {
        alert_pop_up("Invalid CVV", "Number must be in the format XXX... ");
        unset($GLOBALS["error"]);
    } else if ($_GET["error"] == "invalidMonth") {
        alert_pop_up("Invalid expiration month", "Please select the month... ");
        unset($GLOBALS["error"]);
    } else if ($_GET["error"] == "invalidYear") {
        alert_pop_up("Invalid expiration year", "Please select the year... ");
        unset($GLOBALS["error"]);
    } else if ($_GET["error"] == "expiredCard") {
        alert_pop_up("Your card is expired", "Please use another card... ");
        unset($GLOBALS["error"]);
    }
    else if ($_GET["error"] == "notEnoughSeats") {
        alert_pop_up("Not enough seats", "Please select less seats or book another concert... ");
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