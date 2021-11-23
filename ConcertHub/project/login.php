<?php
include_once "header.php";

?>

<link rel="stylesheet" href="./styles/login.css">
<script type="text/javascript" src="./scripts/search.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<section class="container-sm padding-lg border-bottom border-light-grey">
    <div class="title">
        <h1 class="padding-bottom"><b class="logo-style-right">Log in to</b></h1>
        <h1><b class="logo-style-right">Concert</b><b class="logo-style-left">Hub</b></h1>
    </div>
</section>

<section class="container-sm padding-lg">
<?php
include_once "./errors/errors-login.php";
?>
    <div class="login">
        <form class="form-input text-medium row g-3" action="db/login-db.php" method="post">
            <div class="mb-3">
                <label class="form-label" id="username-email-login">Username / Email address</label>
                <input aria-labelledby="username-email-login" type="text" name="name" class="form-control">

            </div>
            <div class="mb-3">
                <label class="form-label" id="password-login" >Password</label>
                <input aria-labelledby="password-login" type="password" name="password" class="form-control">

                <div id="password-error"></div>
            </div>
            <div class="mb-3">
                <button type="submit" name="submit" class="btn btn-dark login-button">Login</button>
            </div>
            <div class="mb-3">
                <p>Don't have an account? <a class="text-danger " href="signup.php">Register</a></p>
            </div>
        </form>
    </div>
</section>

<script>
  var elem = document.getElementById("log-in");
  elem.classList.add("navbar-set-selected");
  document.getElementById("log-in").style.color = "white"; 
</script>

<?php
include_once 'footer.php';
?>