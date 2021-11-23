<?php
include_once 'header.php';
?>

<link rel="stylesheet" href="./styles/search.css">
<script type="text/javascript" src="./scripts/search.js"></script>

<body onload="searchFunctionReload(), removeSearchBar();">
  <section class="container-sm padding-lg border-bottom border-light-grey">
    <div class="centered">
      <form class="d-flex search-bar" method='GET' action="search-page.php" method='GET'>
        <input type="hidden" name="pageLimit" value="10">
        <input type="hidden" name="page" value="1">

        <?php
        $current = $_GET['search'];
        if ($current == '')
          echo "<input id=\"search-input\" class=\"text-medium form-control me-2\" type=\"search\" placeholder=\"Search for a concert...\" aria-label=\"Search\" name=\"search\" value=\"\">";
        else
          echo "<input id=\"search-input\" class=\"text-medium form-control me-2\" type=\"search\" placeholder=\"Search for a concert...\" aria-label=\"Search\" name=\"search\" value=\"$current\">";
        ?>
        
        <button class="text-medium btn btn-dark" type="submit">Search</button>
      </form>
    </div>
  </section>
  <section class="container-sm padding-lg">
    <div class="padding-bottom">
      <h2><b class="logo-style-left">Results:</b></h2>
    </div>
    <table class="table">
      <div class="table-responsive">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Artist</th>
            <th scope="col">Genre</th>
            <th scope="col">Venue</th>
            <th scope="col">City</th>
            <th scope="col">Date</th>
            <th scope="col">Book</th>
          </tr>
        </thead>
        <tbody id='results'>
          <tr>
            <th scope="row">...</th>
            <td>...</td>
            <td>...</td>
            <td>...</td>
            <td>...</td>
            <td>...</td>
          </tr>
        </tbody>
      </div>
    </table>

    <ul class="container-md pagination text-medium">
      <?php
      $search = $_GET['search'];
      $pageNum = $_GET['page'];
      $pageLim = $_GET['pageLimit'];

      if ($pageNum == 1) {
        echo "<li class=\"page-item\"><a class=\"page-link black\" href=\"search-page.php?pageLimit=$pageLim&page=1&search=$search\">Previous</a></li>";
      } else {
        echo "<li class=\"page-item\"><a class=\"page-link black\" href=\"search-page.php?pageLimit=$pageLim&page=" . ($pageNum - 1) . "&search=$search\">Previous</a></li>";
      }

      $i = 1;
      $max = 6;

      if ($pageNum > 5) {
        $i = $pageNum - 4;
        $max = $pageNum + 1;
      }

      for (; $i < $max; $i++) {
        if ($i == $pageNum) {
          echo "<li class=\"page-item\"><a class=\"page-link black active-black\" href=\"search-page.php?pageLimit=$pageLim&page=$i&search=$search\">$i</a></li>";
        } else {
          echo "<li class=\"page-item\"><a class=\"page-link black\" href=\"search-page.php?pageLimit=$pageLim&page=$i&search=$search\">$i</a></li>";
        }
      }

      echo "<li class=\"page-item\"><a class=\"page-link black\" href=\"search-page.php?pageLimit=$pageLim&page=" . ($pageNum + 1) . "&search=$search\">Next</a></li>";
      ?>
    </ul>
  </section>

  <?php
  include_once 'footer.php';
  ?>