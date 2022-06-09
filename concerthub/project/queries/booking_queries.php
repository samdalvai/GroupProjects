<?php

$concert_id = $_GET['concertId'];

$booking_query_sql = "SELECT concert.concert_id, concert.short_title, concert.venue, concert.datetime_local, concertdetails.remaining_seats, concertdetails.ticket_price
from concert
INNER JOIN concertdetails ON concert.concert_id=concertdetails.concert_id
WHERE concert.concert_id='$concert_id'";
$result_bookingpage = $conn->query($booking_query_sql);

global $bookingpage_idconcert;
global $bookingpage_titleconcert;
global $bookingpage_locationconcert;
global $bookingpage_datetime_concert;
global $remaining_seats;
global $ticket_price;

if ($result_bookingpage->num_rows > 0) {
    $row = $result_bookingpage->fetch_assoc();
    $bookingpage_idconcert = $row["concert_id"];
    $bookingpage_titleconcert = $row["short_title"];
    $bookingpage_locationconcert = $row["venue"];
    $bookingpage_datetime_concert = $row["datetime_local"];
    $remaining_seats = $row["remaining_seats"];
    $ticket_price = $row["ticket_price"];

    if ($ticket_price == 0)
      $ticket_price = 50;

} else {
  echo "0 results";
}


?>
