<?php

$locationspage_query_sql = "select DISTINCT city from venue ORDER BY city";
$result_locationspage = $conn->query($locationspage_query_sql);

global $locationspage_name;
$locationspage_name = [];
global $locationspage_image;
$locationspage_image = [];

if ($result_locationspage->num_rows > 0) {
    while ($row = $result_locationspage->fetch_assoc()) {
        array_push($locationspage_name, $row["city"]);
    }
} else {
    echo "0 results";
}
?>