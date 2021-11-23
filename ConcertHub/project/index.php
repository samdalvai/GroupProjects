<?php
include_once 'header.php';
require_once "./db/db-connector.php";
include_once './queries/homepage_queries.php';
?>

<link rel="stylesheet" href="./styles/home.css">

<section class="wallpaper">
    <img class="img-responsive" src="./images/home/concert.jpg" alt="Snow" style="width:100%;">
    <div class="centered text-large line-height-220"><b class="logo-style-left wallpaper-logo">The best</b> <b class="logo-style-right wallpaper-logo">live
            events</b></div>
</section>

<section class="our-webcoderskull padding-lg border-bottom border-light-grey padding-left-right-25" id="popular">
    <div class="container">
        <div class="row header header-icon">
            <h2><b class="logo-style-left">Top</b><b class="logo-style-right">music</b></h2>
        </div>
        <ul class="row">

            <?php
                for($i=0;$i<4;$i++){
                    echo "<li class=\"col-12 col-md-6 col-lg-3 padding-bottom-15\">";
                    echo "<div class=\"block-of-content equal-height\">";
                    echo "<div class=\"most-popular-category\">$genres_name[$i]</div>";
                    echo "<div class=\"tooltip-text\">Click to browse concerts</div>";
                    echo "<a href=\"search-page.php?pageLimit=10&page=1&search=$genres_name[$i]\" target=\"_blank\"><img class=\"box-shadow\" src=\"$genres_images[$i]\" alt=\"$genres_name[$i]\"></a>";
                    echo "</div></li>";
                }
            ?>
            
        </ul>
    </div>
</section>

<section class="our-webcoderskull padding-lg border-bottom border-light-grey padding-left-right-25">
    <div class="container">
        <div class="row header header-icon">
            <h2><b class="logo-style-left">Top</b><b class="logo-style-right">artists</b></h2>
        </div>
        <ul class="row">

            <?php
                for($i=0;$i<4;$i++){
                    echo "<li class=\"col-12 col-md-6 col-lg-3 padding-bottom-15\">";
                    echo "<div class=\"block-of-content equal-height\">";
                    echo "<div class=\"most-popular-category\">$performer_name[$i]</div>";
                    echo "<div class=\"tooltip-text\">Click to browse concerts</div>";
                    echo "<a href=\"artist.php?artist=$performer_id[$i]\" target=\"_blank\"><img class=\"box-shadow\" src=\"$performer_images[$i]\" alt=\"$performer_name[$i]\"></a>";
                    echo "</div></li>";
                }
            ?>

        </ul>
    </div>
</section>
<section class="our-webcoderskull padding-lg border-bottom border-light-grey padding-left-right-25">
    <div class="container" id="locations">
        <div class="row header header-icon">
            <h2><b class="logo-style-left">Top</b><b class="logo-style-right">locations</b></h2>
        </div>
        <ul class="row">
            <?php
                for($i=0;$i<4;$i++){
                    echo "<li class=\"col-12 col-md-6 col-lg-3 padding-bottom-15\">";
                    echo "<div class=\"block-of-content equal-height\">";
                    echo "<div class=\"most-popular-category\">$locations_name[$i]</div>";
                    echo "<div class=\"tooltip-text\">Click to browse concerts</div>";
                    echo "<a href=\"search-page.php?pageLimit=10&page=1&search=$locations_name[$i]\" target=\"_blank\"><img class=\"box-shadow\" src=\"./images/locations/$i.jpg\" alt=\"$locations_name[$i]\"></a>";
                    echo "</div></li>";
                }
            ?>
        </ul>
    </div>
</section>
<section class="our-webcoderskull padding-lg padding-left-right-25">
    <div class="container" id="about">
        <div class="row header header-icon">
            <h2><b class="logo-style-left">About</b></h2>
        </div>
        <div class="col-12">
            <p class="text-medium" style="margin-left: -12px;">ConcertHub is an online platform for music events around the world.<br> We provide an "easy-to-use" and secure tool to browse and book the cheapest tickets for your favourite artists.
            </p>
        </div>
    </div>
</section>

<?php
include_once 'footer.php';
?>
