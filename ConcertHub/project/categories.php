<?php
include_once 'header.php';
require_once "./db/db-connector.php";
include_once './queries/categories_queries.php';
?>

<link rel="stylesheet" href="./styles/categories.css">

<section class="container-sm padding-lg border-bottom border-light-grey">
  <div class="row">
    <h1><b class="logo-style-left">Music</b><b class="logo-style-right">Categories</b></h1>
  </div>
</section>

<section class="container-sm padding-lg">
  <div class="row" id="images">

    <?php
    $categoriespage_name_numberOfrows = sizeof($categoriespage_name);
    $index = $categoriespage_name_numberOfrows;
    $column_formatting = "column-three";
    $counter = 0;

    foreach ($categoriespage_name as $value) {

      if ($index < 3)
        if ($categoriespage_name_numberOfrows % 3 == 2) {
          $column_formatting = "column-two";
        }

      if ($index < 2)
        if ($categoriespage_name_numberOfrows % 3 == 1) {
          $column_formatting = "column-one";
        }

      $display_string = "<div class=\"$column_formatting\">" . "<div class=\"category-text\">$categoriespage_name[$counter]</div>" . "<div class=\"tooltip-text\">Click to browse concerts</div>" . "<a href=\"search-page.php?pageLimit=10&page=1&search=$categoriespage_name[$counter]\" target=\"_blank\">" . "<img src=\"$categoriespage_image[$counter]\" alt=\"$categoriespage_name[$counter]\">" .  "</a>" . "</div>";
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