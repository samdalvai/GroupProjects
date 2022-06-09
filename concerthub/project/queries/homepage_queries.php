<?php

$performerms_query_sql = "select DISTINCT P.image, P.name, P.score_performer, P.id FROM performer AS P JOIN concert C WHERE P.name = C.performer order by score_performer desc
    limit 4";

$result_performers = $conn->query($performerms_query_sql);

global $performer_name;
$performer_name = [];
global $performer_images;
$performer_images = [];
global $performer_id;
$performer_id = [];


if ($result_performers->num_rows > 0) {
    while ($row = $result_performers->fetch_assoc()) {
        array_push($performer_images, $row["image"]);
        array_push($performer_name, $row["name"]);
        array_push($performer_id, $row["id"]);
    }
} else {
    echo "0 results";
}


$genres_query_sql = "select G.name, G.image from genre as G INNER JOIN (SELECT genre_name, max(score_performer) as maxP FROM performer where genre_name IS NOT NULL group BY (genre_name) order BY max(score_performer) DESC limit 4) AS P ON G.name = P.genre_name order BY P.maxP DESC";
$result_genres = $conn->query($genres_query_sql);

global $genres_name;
$genres_name = [];
global $genres_images;
$genres_images = [];

if ($result_genres->num_rows > 0) {
    while ($row = $result_genres->fetch_assoc()) {
        array_push($genres_name, $row["name"]);
        array_push($genres_images, $row["image"]);
    }
} else {
    echo "0 results";
}


$locations_query_sql = "select V.city, V.venue_name, V.score_venue FROM venue AS V ORDER BY V.score_venue DESC LIMIT 4";
$result_locations = $conn->query($locations_query_sql);

global $locations_name;
$locations_name = [];

if ($result_locations->num_rows > 0) {
    while ($row = $result_locations->fetch_assoc()) {
        array_push($locations_name, $row["city"]);
    }
} else {
    echo "0 results";
}


?>
