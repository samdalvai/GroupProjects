<?php
include_once 'header.php';
?>

<link rel="stylesheet" href="./styles/login.css">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>


<section class="container-sm padding-lg border-bottom border-light-grey">
    <div class="title">
        <h1 class="padding-bottom"><b class="logo-style-right">Sign up to</b></h1>
        <h1><b class="logo-style-right">Concert</b><b class="logo-style-left">Hub</b></h1>
    </div>
</section>

<section class="container-sm padding-lg clearfix">

<?php
include_once "./errors/errors-signup.php";
?>
        <form class="form-input text-medium row g-3" action="db/signup-db.php" method="post">
            <div class="mb-3">
                <label class="form-label" id="name-label-signup">Full name</label>
                <input  aria-labelledby="name-label-signup" type="text" name="name" class="form-control">
            </div>
            <div class="mb-3">
                <label class="form-label" id="username-label-signup"  >Username</label>
                <input aria-labelledby="username-label-signup" minlength="8" title="Password must  " type="text" name="uid" class="form-control">
            </div>
            <div class="mb-3">
                <label class="form-label" id="email-label-signup" >E-mail</label>
                <input aria-labelledby="email-label-signup" type="text" name="email" class="form-control">
            </div>
            <div class="mb-3">
                <label class="form-label" id="password-label-signup">Password</label>
                <input aria-labelledby="password-label-signup" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Password must be 8 characters including 1 uppercase letter, 1 lowercase letter and numeric characters"  type="password" name="passWord" class="form-control">
            </div>
            <div class="mb-3">
                <label class="form-label" id="repeat-password-label-signup">Repeat Password</label>
                <input aria-labelledby="repeat-password-label-signup" type="password" name="passWordRepeat" class="form-control">
            </div>
            <div class="mb-3">
                <button type="submit" name="submit" class="sign-up-button btn btn-dark">Sign-up</button>
            </div>
        </form>
</section>

<script>
    var elem = document.getElementById("sign-up");
    elem.classList.add("navbar-set-selected");
</script>

<?php
include_once 'footer.php';
?>