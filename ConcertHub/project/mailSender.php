<?php
use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\Exception;
require_once "/Applications/MAMP/htdocs/web-engineering-csmontali6/project/vendor/autoload.php";
$counterForTicket = 100;

function sendMailToRegistredUser($eemail,$name){
    $mail = new PHPMailer();

    $mail->IsSmtp();
    $mail->SMTPDebug = 0;
    $mail->SMTPAuth = true;
    $mail->SMTPSecure = "tls";
    $mail->Port = 587;

    $mail->Host = "smtp.gmail.com";
    $mail->Username = "concerthub603@gmail.com";
    $mail->Password = "passconcerthub";

    $mail->From = "concerthub603@gmail.com";
    $mail->FromName = "ConcertHub";
    $mail->addAddress($eemail);
    $mail->isHTML(true);
    $mail->Subject = "Welcome to ConcertHub!";
    $mail->Body = "Dear ". $name . ",<br>  
    Thank you for registering to ConcertHub!   Your registration and payment has been received.
    If you would like to view your registration details, you can login at the following link: URL  <br> 
    You registered with this email: " . $eemail . 
    "<br> If you forgot your password, simply hit “Forgot password” and you’ll be prompted to reset it.
    <br> If you have any questions leading our website, feel free to reply to this email.
    <br> We look forward to seeing you on our website!

    <br> <br>  Kind Regards,
    <br> ConcertHub Staff
    <br> concerthub603@gmail.com
    <br> +399832941515";
    $mail->AltBody = "This is the plain text version of the email content";;


    try {
        $mail->send();
    } catch (Exception $e) {
        echo "Mailer Error: " . $mail->ErrorInfo;
}
}

function sendMailToConfirmTicket($eemail, $firstName, $concertTitle, $concertLocation, $concertDate){
    $mail = new PHPMailer();

    $mail->IsSmtp();
    $mail->SMTPDebug = 0;
    $mail->SMTPAuth = true;
    $mail->SMTPSecure = "tls";
    $mail->Port = 587;

    $mail->Host = "smtp.gmail.com";
    $mail->Username = "concerthub603@gmail.com";
    $mail->Password = "passconcerthub";

    $mail->From = "concerthub603@gmail.com";
    $mail->FromName = "ConcertHub";
    $mail->addAddress($eemail);
    $mail->isHTML(true);
    $mail->Subject = "Confirmation of Reservation - ConcertHub!";
    $mail->Body = "Hey ". $firstName . ", <br>
    Thank you for buying a ticket for the concert <i>" . $concertTitle . "</i>. We can not wait to see you in " . $concertLocation . " on " . $concertDate . "!. <br>
    I am part of our attendee support team, and I will be sending you some useful information about the concert <i>". $concertTitle . "</i> 
    over the coming weeks. In the meantime you can find more useful informations using the following links. <br>
    1. You can stay with the ConcertHub network by booking one of our hotels. <br>
    2. Learn more about the <a href=https://www.google.com/search?q=" . $concertLocation ."> Location </a>   <br>
    3. Tell your friends you are attending. <br>
    4. Follow us on the social medias. <br>
    5. Your ticket reference is TA-" . $GLOBALS["counterForTicket"] . "-AA. <br>
    If you have any questions, you can just hit reply to contact our attendee support team. <br>
    
    <br> Best Regards, <br>
    Anthony <br>
    ConcertHub Support Team";
    $mail->AltBody = "This is the plain text version of the email content";;
    $GLOBALS["counterForTicket"]++ ;

    try {
        $mail->send();
    } catch (Exception $e) {
        echo "Mailer Error: " . $mail->ErrorInfo;
}
}


?>
