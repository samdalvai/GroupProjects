<?php
include_once 'header.php';
require_once "./db/db-connector.php";
include_once './queries/locations_queries.php';
?>

<link rel="stylesheet" href="./styles/categories.css">

<section class="container-sm padding-lg border-bottom border-light-grey">
  <div class="row">
    <h1><b class="logo-style-left">Event</b><b class="logo-style-right">Locations</b></h1>
  </div>
</section>

<section class="container-sm padding-lg">
  <div class="row" id="images">

    <?php
    $locationpage_name_numberOfrows = sizeof($locationspage_name);
    $index = $locationpage_name_numberOfrows;
    $column_formatting = "column-three";
    $counter = 0;

    foreach ($locationspage_name as $value) {

      if ($index < 3)
        if ($locationpage_name_numberOfrows % 3 == 2) {
          $column_formatting = "column-two";
        }

      if ($index < 2)
        if ($locationpage_name_numberOfrows % 3 == 1) {
          $column_formatting = "column-one";
        }
      
      $image = $counter % 4;

      $display_string = "<div class=\"$column_formatting\">" . "<div class=\"category-text\">$locationspage_name[$counter]</div>" . "<div class=\"tooltip-text\">Click to browse concerts</div>" . "<a href=\"search-page.php?pageLimit=10&page=1&search=$locationspage_name[$counter]\" target=\"_blank\">" . "<img src=\"images/locations/$image.jpg\" alt=\"$locationspage_name[$counter]\">" .  "</a>" . "</div>";
      echo $display_string;

      $counter++;
      $index--;
    }
    ?>

  </div>
</section>

<script>
    var elem = document.getElementById("navbarDropdown");
    elem.classList.add("navbar-set-selected");
</script>


<?php
include_once 'footer.php';
?>
