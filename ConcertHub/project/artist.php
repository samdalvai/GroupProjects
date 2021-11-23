<?php
include_once 'header.php';
require_once "./db/db-connector.php";
include_once './queries/artist_queries.php';
?>

<link rel="stylesheet" href="./styles/artist.css">
<link rel="stylesheet" href="./styles/login.css">

<section class="container-sm padding-lg border-bottom border-light-grey">
  <div class="title">
    <h1><b class="logo-style-left line-height-220">
        <?php
        echo "$artistpage_name[0]";
        ?>
      </b>
    </h1>
  </div>
</section>

<section class="container-sm padding-lg border-bottom border-light-grey">
  <div class="container">
    <div class="row">
      <div class="col-12 col-lg-6 col-md-12 col-sm-12  padding-right-25 artist-image-container">
        <?php
        echo "<div class=\"artist-image\"><div><img class=\"img-responsive\" src=\"$artistpage_image[0]\" alt=\"$artistpage_name[0]\"></div>";
        echo "<div class=\"padding-top-25 padding-bottom-25 center\">
          <h1><b><a class=\"btn btn-dark\"href=\"https://en.wikipedia.org/wiki/$artistpage_name[0]\" target=\"_blank\">Artist Bio</a></b></h1>
          </div></div>"
        ?>
      </div>
      <div class="col-12 col-lg-6 col-md-0 col-sm-0">
        <div class="center padding-bottom-25">
          <h1><b class="logo-style-right grey">Available tickets</b></h1>
        </div>
        <table class="table">
          <div class="table-responsive">
            <thead>
              <tr>
                <th scope="col">Artist</th>
                <th scope="col">Event name</th>
                <th scope="col">Date</th>
                <th scope="col">Book</th>
              </tr>
            </thead>
            <tbody>

              <?php
              $counter = 0;
              foreach ($artistpage_name as $value) {
                echo "<tr>";
                echo "<th scope=\"row\">$artistpage_name[$counter]</th>";
                echo "<td>$artistpage_titleconcert[$counter]</td>";
                echo "<td>$artistpage_datetime_concert[$counter]</td>";
                echo "<td><a href='booking.php?concertId=$artistpage_idconcert[$counter]'><button type='button' class='btn btn-dark'>Book</button></a></td>";
                echo "</tr>";
                $counter++;
              }
              ?>

            </tbody>
          </div>
        </table>
      </div>
    </div>
  </div>
</section>

<?php
include_once 'footer.php';
?>