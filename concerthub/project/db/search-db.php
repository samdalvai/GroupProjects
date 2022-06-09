<?php
require_once "db-connector.php";

$search = $_GET['search'];
$pageLimit = $_GET['pageLimit'];
$page = $_GET['page'];

$search = mysqli_real_escape_string($conn, $search);
$search = filter_var($search, FILTER_SANITIZE_STRING);

$pageLimit = filter_var($pageLimit, FILTER_SANITIZE_STRING);
$page = filter_var($page, FILTER_SANITIZE_STRING);

$query = "";

if ($search == "") {
   $query = "SELECT DISTINCT concert.concert_id, concert.performer, performer.id, performer.genre_name, concert.venue, venue.city, concert.datetime_local
   FROM concert
   INNER JOIN performer ON concert.performer=performer.name
   INNER JOIN venue ON concert.venue=venue.venue_name
   order by concert.datetime_local";
} else{
   $query = "SELECT DISTINCT concert.concert_id, concert.performer, performer.id, performer.genre_name, concert.venue, venue.city, concert.datetime_local
   FROM concert
   INNER JOIN performer ON concert.performer=performer.name
   INNER JOIN venue ON concert.venue=venue.venue_name
   WHERE concert.performer LIKE '%$search%' OR performer.genre_name LIKE '%$search%' OR concert.venue LIKE '%$search%' OR venue.city LIKE '%$search%'
   order by concert.datetime_local";
}

$qry_result = mysqli_query($conn, $query);
$display_string = "";

$index = 1;
$min_index = $page * $pageLimit - $pageLimit + 1;
$results_counter = 0;

while ($row = mysqli_fetch_array($qry_result)) {
   if ($results_counter < $pageLimit && $index >= $min_index){
      if ($index % 2 != 0){
         $display_string .= "<tr>";
      }
      else{
         $display_string .= "<tr class=\"light-grey\">";
      }
      $artist_id = $row['id'];
     
      $display_string .= "<th scope=\"row\">$index</th>";
      $display_string .= "<td><a href='artist.php?artist=$artist_id'>$row[performer]</a></td>";

      if ($row['genre_name'] != NULL)
         $display_string .= "<td><a href=\"https://en.wikipedia.org/wiki/$row[genre_name]_music\" target=\"_blank\">$row[genre_name]</a></td>";
      else
      $display_string .= "<td></td>";
      
      $display_string .= "<td><a href=\"https://maps.google.com?q=$row[venue]\" target=\"_blank\">$row[venue]</a></td>";
      $display_string .= "<td>$row[city]</td>";
      $display_string .= "<td>$row[datetime_local]</td>";
      $display_string .= "<td><a href='booking.php?concertId=$row[concert_id]'><button class=\"text-medium btn btn-dark\" type=\"submit\">Book</button></a></td>";
      $display_string .= "</tr>";

      $results_counter++;
   }
   $index++;
}

if ($display_string == ""){
   if ($index == 1)
      echo "<h3 class=\"logo-style-right\">No results for \"$search\"</h3>";
   else
      echo "<h3 class=\"logo-style-right\">No additional results for \"$search\"</h3>";
}

echo $display_string . "<br/>";

exit();

?>
