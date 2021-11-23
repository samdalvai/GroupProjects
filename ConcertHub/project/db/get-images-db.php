<?php
require_once "db-connector.php";

$search = $_GET['type'];

$search = mysqli_real_escape_string($connection, $search);
$search = filter_var($search, FILTER_SANITIZE_STRING);

$query = "";
$query = "SELECT * FROM $search ORDER BY name";

$qry_result = mysqli_query($connection, $query);
$num_rows = mysqli_num_rows($qry_result);

$display_string = "";

$index = $num_rows;
$column_formatting = "column-three";


while ($row = mysqli_fetch_array($qry_result)) {

   if ($index < 3)
      if ($num_rows % 3 == 2) {
         $column_formatting = "column-two";
      }

   if ($index < 2)
      if ($num_rows % 3 == 1) {
         $column_formatting = "column-one";
      }

   $display_string .= "<div class=\"$column_formatting\">";
   $display_string .= "<div class=\"category-text\">$row[name]</div>";
   $display_string .= "<div class=\"tooltip-text\">Click to browse concerts</div>";
   $display_string .= "<a href=\"search-page.php?searchValue=$row[name]\" target=\"_blank\">";
   $display_string .= "<img src=\"$row[link]\" alt=\"$row[name]\">";
   $display_string .=  "</a>";
   $display_string .= "</div>";

   $index--;
}

if ($display_string == "")
   echo "<h3\">Error, retrieval of images failed..</h3>";

echo $display_string . "<br/>";

exit();
