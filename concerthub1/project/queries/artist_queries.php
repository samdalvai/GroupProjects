<?php

$artist_id = $_GET['artist'];

$artistpage_query_sql = "SELECT concert_id, short_title, performer, datetime_local from concert, performer
where performer = performer.name and performer.id=$artist_id
ORDER BY datetime_local";
$result_artistpage = $conn->query($artistpage_query_sql);

global $artistpage_idconcert;
$artistpage_idconcert = [];
global $artistpage_name;
$artistpage_name = [];
global $artistpage_titleconcert;
$artistpage_titleconcert = [];
global $artistpage_datetime_concert;
$artistpage_datetime_concert = [];

if ($result_artistpage->num_rows > 0) {
  while ($row = $result_artistpage->fetch_assoc()) {
    array_push($artistpage_idconcert, $row["concert_id"]);
    array_push($artistpage_name, $row["performer"]);
    array_push($artistpage_titleconcert, $row["short_title"]);
    array_push($artistpage_datetime_concert, $row["datetime_local"]);
  }
} else {
  echo "0 results";
}



$artistpage_query_sql_image = "SELECT Distinct P.image FROM performer P, concert C WHERE P.name= C.performer AND P.id='$artist_id'";
$image_result_artistpage = $conn->query($artistpage_query_sql_image);

global $artistpage_image;
$artistpage_image = [];

if ($image_result_artistpage->num_rows > 0) {
  while ($row = $image_result_artistpage->fetch_assoc()) {
    array_push($artistpage_image, $row["image"]);
  }
} else {
  echo "0 results";
}

?>
