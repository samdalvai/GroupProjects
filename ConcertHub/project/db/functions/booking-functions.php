<?php 

function emptyInput($input){
    return empty($input);
}

function validateName($input){
    $pattern = "/[0-9]/";

    preg_match($pattern, $input, $output);

    return (sizeof($output) == 0);
}

function validateCreditCardNum($input){

    $pattern = "/^[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}$/";

    preg_match_all($pattern, $input, $matches);

    return (sizeof($matches[0]) != 0);
}

function validateCVV($input){

    $pattern = "/^[0-9]{3}$/";

    preg_match_all($pattern, $input, $matches);

    return (sizeof($matches[0]) != 0);
}

function validateMonth($input){
        return ($input != "MM");
}

function validateYear($input){
    return ($input != "YY");

}

function validateExpirationDate($year, $month){
    if ($year < date('y'))
        return false;
    else if ($year > date('y'))
        return true;
    else
        return ($month >= date('m')); // same year as now

}

function checkSeats($conn,$concertId,$numOfTickets){

    // check if the current number of seats is enough
    $query = "SELECT remaining_seats FROM concertdetails
    WHERE concert_id=$concertId;";

    $results = $conn->query($query);

    $numOfSeats = $results->fetch_assoc()["remaining_seats"];

    return ($numOfSeats - $numOfTickets);
}

function reserveSeats($conn,$concertId,$newSeats){

    // reserve the seats
    $query = "UPDATE concertdetails SET remaining_seats=($newSeats) WHERE concert_id=$concertId;";
    $conn->query($query);

    $stmt = mysqli_stmt_init($conn);
    if(!mysqli_stmt_prepare($stmt, $query)){
        header("location: booking.php?error=stmtfailed");
        exit();
    }

    mysqli_stmt_execute($stmt);
    mysqli_stmt_close($stmt);
    //exit();
}

function getConfirmation($concertId, $concertTitle, $concertLocation, $concertDate, $numOfTickets, $firstName, $lastName, $totalAmount, $email){

    header("location: ../confirmation.php?concertId=$concertId&concertTitle=$concertTitle&concertLocation=$concertLocation&concertDate=$concertDate&numOfTickets=$numOfTickets&firstName=$firstName&lastName=$lastName&totalAmount=$totalAmount&email=$email");
    exit();
}

?>