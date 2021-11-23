<?php 

if(isset($_POST["submit"])){

    $concertId = $_POST["concertId"];
    $concertTitle = $_POST["concertTitle"];
    $concertLocation = $_POST["concertLocation"];
    $concertDate = $_POST["concertDate"];

    $fullName = $_POST["fullName"];
    $cardNumber = $_POST["cardNumber"];
    $expMonth = $_POST["expMonth"];
    $expYear = $_POST["expYear"];
    $CVV = $_POST["CVV"];
    $numTickets = $_POST["numTickets"];
    $totalAmount = $_POST["totalAmount"];

    $firstName = $_POST["firstName"];
    $lastName = $_POST["lastName"];
    $emailUser= $_POST["email-booking"];

    require_once "db-connector.php";
    require_once "functions/booking-functions.php";
    
    if(emptyInput($fullName) || emptyInput($cardNumber) || emptyInput($expMonth) || emptyInput($expYear) || emptyInput($CVV) || emptyInput($numTickets) || emptyInput($totalAmount)){
        header("location: ../booking.php?concertId=$concertId&error=emptyInput");
        exit();
    }

    if(validateName($fullName) == false || validateName($firstName) == false || validateName($lastName) == false){
        header("location: ../booking.php?concertId=$concertId&error=invalidName");
        exit();
    }

    if(validateCreditCardNum($cardNumber) == false){
        header("location: ../booking.php?concertId=$concertId&error=wrongCardNumber");
        exit();
    }

    if(validateCVV($CVV) == false){
        header("location: ../booking.php?concertId=$concertId&error=wrongCVV");
        exit();
    }

    if(validateMonth($expMonth) == false){
        header("location: ../booking.php?concertId=$concertId&error=invalidMonth");
        exit();
    }

    if(validateYear($expYear) == false){
        header("location: ../booking.php?concertId=$concertId&error=invalidYear");
        exit();
    }

    if(validateExpirationDate($expYear,$expMonth) == false){
        header("location: ../booking.php?concertId=$concertId&error=expiredCard");
        exit();
    }
    
    $newSeats = checkSeats($conn,$concertId,$numTickets);

    if($newSeats < 0){
        header("location: ../booking.php?concertId=$concertId&error=notEnoughSeats");
        exit();
    }
    
    reserveSeats($conn,$concertId,$newSeats);

    getConfirmation($concertId,$concertTitle,$concertLocation,$concertDate,$numTickets,$firstName,$lastName,$totalAmount, $emailUser);


}
else{
    header("location: ../booking.php");
    exit();
}


?>
