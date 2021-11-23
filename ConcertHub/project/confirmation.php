<?php

if(!isset($_SERVER['HTTP_REFERER'])){
    header('location: error.php');
    exit;
}

include_once 'header.php';

$concertId = $_GET['concertId'];
$concertTitle = $_GET['concertTitle'];
$concertLocation= $_GET['concertLocation'];
$concertDate= $_GET['concertDate'];

$firstName = $_GET['firstName'];
$lastName = $_GET['lastName'];
$numOfTickets = $_GET['numOfTickets'];
$totalAmount = $_GET['totalAmount'];
$email = $_GET['email'];

require_once "mailSender.php";
sendMailToConfirmTicket($email, $firstName, $concertTitle, $concertLocation, $concertDate);
?>

<link rel="stylesheet" href="./styles/login.css">
<link rel="stylesheet" href="./styles/booking.css">
<link rel="icon" href="./images/icons/main-icon.png">
<script type="text/javascript" src="./scripts/confirmation.js"></script>

<section class="container-sm padding-lg border-bottom border-light-grey">
    <div class="title">
        <h1><b class="logo-style-left">Payment</b><b class="logo-style-right">Confirmed</b></h1>
    </div>
</section>

<section class="container-sm padding-lg">
    <div class="col-sm-8 centered">
        <div class="row">
            <h3 style="padding-left:0px" class="padding-top-25"><b class="logo-style-right">Ticket Details</b></h3>
        </div>
        <div class="row">
            <table class="table">
                <div class="table-responsive">
                    <tbody>
                        <tr>
                            <th scope="row">Concert id:</th>
                            <?php echo "<td>$concertId</td>"; ?>
                        </tr>
                        <tr>
                            <th scope="row">Concert name:</th>
                            <?php echo "<td>$concertTitle</td>"; ?>
                        </tr>
                        <tr>
                            <th scope="row">Location:</th>
                            <?php echo "<td>$concertLocation</td>"; ?>
                        </tr>
                        <tr>
                            <th scope="row">Date:</th>
                            <?php echo "<td>$concertDate</td>"; ?>
                        </tr>
                        <tr>
                            <th scope="row">First Name:</th>
                            <?php echo "<td>$firstName</td>"; ?>
                        </tr>
                        <tr>
                            <th scope="row">Last name:</th>
                            <?php echo "<td>$lastName</td>"; ?>
                        </tr>
                        <tr>
                            <th scope="row">Number of tickets:</th>
                            <?php echo "<td>$numOfTickets</td>"; ?>
                        </tr>
                        <tr>
                            <th scope="row">Paid amount:</th>
                            <?php echo "<td class=\"green\">$totalAmount$</td>"; ?>
                        </tr>                         
                    </tbody>
                </div>
            </table>
        </div>
        <div class="row ">
            <button class="centered sign-up-button buy-button btn btn-dark" onclick="printConfirmation()">Print this confirmation</button>
        </div>
    </div>
</section>

<?php
include_once 'footer.php';
?>