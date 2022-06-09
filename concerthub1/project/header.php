<?php
session_start();
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>ConcertHub</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Your meeting point to buy tickets for your favourite concerts">
    <meta name="author" content="Samuel Dalvai">
    <meta name="author" content="Andrea Perri">
    <meta name="author" content="Tedi Ibershimi">

    <meta name="keywords" content="Unibz, Tedi Ibershimi, Samuel Dalvai, Andrea Perri, GitLab, project, university, web site, CSMontali6">

    <link rel="stylesheet" href="./styles/shared.css">
    <link rel="icon" href="./images/icons/main-icon.png">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>

</head>

<body>
    <nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid text-medium">
            <a class="navbar-brand" href="index.php"><b class="logo-style-left main-logo">Concert</b><b class="logo-style-right main-logo">Hub</b></a>  
            <button id= "button-togg" class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" href="index.php#popular">Popular</a> <!--  -->
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Browse
                        </a>
                        <ul class="dropdown-menu text-medium" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="categories.php">Categories</a></li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><a class="dropdown-item" href="locations.php">Locations</a></li>
                        </ul>
                    </li>
                    <li class="nav-item">
                        <a id="contact" class="nav-link active" href="team-page.php">Contact</a>
                    </li>
                    <?php
                    if (isset($_SESSION["usersUid"])){
                        echo "<li class='nav-item'> <a class='nav-link' href='userprofile.php'>My Profile</a></li>";
                        echo "<li class='nav-item'> <a class='nav-link' href='db/logout.php'>Logout</a></li>";
                    } else {
                        echo "<li class='nav-item'> <a id=\"sign-up\" class='nav-link active' href='signup.php'>Sign-up</a></li>";
                        echo "<li class='nav-item'><a id=\"log-in\" class='nav-link active' href='login.php'>Login</a></li>";
                    }
                    ?>
                </ul>
                <form id="search-bar" class="d-flex" action="search-page.php" method='GET' target="_blank">
                    <input type="hidden" name="pageLimit" value="10">
                    <input type="hidden" name="page" value="1">
                    <input class="text-medium form-control me-2" type="search" placeholder="Search for a concert..." aria-label="Search" name="search" value="">
                    <button class="text-medium btn btn-dark" type="submit">Search</button>
                </form>
            </div>
        </div>
    </nav>
