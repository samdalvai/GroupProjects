<?php

$categoriespage_query_sql = "select DISTINCT name, image from genre ORDER BY name";
$result_categoriespage = $conn->query($categoriespage_query_sql);

global $categoriespage_name;
$categoriespage_name = [];
global $categoriespage_image;
$categoriespage_image = [];

if ($result_categoriespage->num_rows > 0) {
  while ($row = $result_categoriespage->fetch_assoc()) {
    array_push($categoriespage_name, $row["name"]);
    array_push($categoriespage_image, $row["image"]);
  }
} else {
  echo "0 results";
}
?>