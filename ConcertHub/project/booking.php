<?php
include_once 'header.php';
require_once "./db/db-connector.php";
include_once './queries/booking_queries.php';
?>

<link rel="stylesheet" href="./styles/login.css">
<link rel="stylesheet" href="./styles/booking.css">
<script type="text/javascript" src="./scripts/booking.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<section class="container-sm padding-lg border-bottom border-light-grey">
    <div class="title">
        <h1 class="padding-bottom"><b class="logo-style-right">Book your</b></h1>
        <h1><b class="logo-style-left">Ticket</b></h1>
    </div>
</section>

<section class="container-sm padding-lg">
<?php
include_once "./errors/errors-booking.php";
?>
    <div class="container">
        <div class="row">
            <div class="col-sm-6 padding-right-25">
                <form class="booking-details text-medium row g-3">
                    <div class="row">
                        <h2 style="padding-left:0px" class="padding-top-25"><b class="logo-style-right section-title">Order Details</b></h2>
                    </div>
                    <div class="row">
                        <table class="table">
                            <div class="table-responsive">
                                <tbody>
                                    <tr>
                                        <th scope="row">Concert id:</th>
                                        <?php echo "<td>$bookingpage_idconcert</td>"; ?>
                                    </tr>
                                    <tr>
                                        <th scope="row">Concert name:</th>
                                        <?php echo "<td>$bookingpage_titleconcert</td>"; ?>
                                    </tr>
                                    <tr>
                                        <th scope="row">Location:</th>
                                        <?php echo "<td>$bookingpage_locationconcert</td>"; ?>
                                    </tr>
                                    <tr>
                                        <th scope="row">Date:</th>
                                        <?php echo "<td>$bookingpage_datetime_concert</td>"; ?>
                                    </tr>
                                    <tr>
                                        <th scope="row">Remaining seats:</th>
                                        <?php echo "<td>$remaining_seats</td>"; ?>
                                    </tr>
                                    <tr>
                                        <th scope="row">Price per ticket:</th>
                                        <?php echo "<td><a id=\"ticketPrice\">$ticket_price</a> $</td>"; ?>
                                    </tr>
                                </tbody>
                            </div>
                        </table>
                    </div>
                    <div class="row">
                        <div class="col-12 col-lg-4 col-md-4 col-sm-4">
                            <div class="input-group">
                                <label class="form-label" id="tickets-number-label">Tickets</label>
                                <div class="input-group">
                                    <select aria-labelledby="tickets-number-label" class="form-select" id="autoSizingSelect" onchange="updateTotal(this.selectedIndex)">
                                        <option selected value="1">1</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>
                                        <option value="4">4</option>
                                        <option value="5">5</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 col-lg-10 col-md-10 col-sm-10">
                            <h1 style="padding-left:0px" class="padding-top-25"><b class="logo-style-right section-title">TOTAL:</b><b class="logo-style-right green section-title"><?php echo "<td><a id=\"tempPrice\">$ticket_price</a></td>";?> $</b></h1>
                        </div>
                    </div>


                </form>
            </div>

            <div class="col-sm-6 padding-left-25">
                <form class="booking-details text-medium row g-3" action="./db/booking-db.php" method='POST'>
                    
                    <div class="row">
                        <h2 style="padding-left:0px" class="padding-top-25"><b class="logo-style-right section-title">Reservation details</b></h2>
                    </div>

                    <div class="row">
                        <div class="col-sm-6">
                            <label class="form-label" id="first-name-label">First name</label>
                            <div class="input-group">
                                <input aria-labelledby="first-name-label" type="text" class="form-control" name="firstName">
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <label class="form-label" id="last-name-label">Last name</label>
                            <div class="input-group">
                                <input aria-labelledby="last-name-label" type="text" class="form-control" name="lastName">
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <label class="form-label" id="email-label">Email</label>
                        <div class="input-group">
                            <input aria-labelledby="email-label" type="email" name="email-booking" class="form-control" >
                            <span class="input-group-text" id="basic-addon2"><i class="bi bi-envelope"></i></span>
                        </div>
                    </div>
                    <div class="row">
                        <h2 style="padding-left:0px" class="padding-top-25"><b class="logo-style-right section-title">Payment details</b></h2>
                    </div>

                    <div class="row">
                        <label class="form-label" id="full-name-label">Full name (on the card)</label>
                        <div class="input-group">
                            <input aria-labelledby="full-name-label" type="text" name="fullName" class="form-control" aria-label="Full Name">
                            <span class="input-group-text" id="basic-addon2"><i class="bi bi-person"></i></span>
                        </div>
                    </div>
                    <div class="row">
                        <label class="form-label" id="card-number-label">Card number</label>
                        <div class="input-group">
                            <input aria-labelledby="card-number-label" type="text" name="cardNumber" class="form-control" aria-label="Card Number" placeholder="XXXX-XXXX-XXXX-XXXX">
                            <span class="input-group-text" id="basic-addon2"><i class="bi bi-credit-card"></i></span>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 col-sm-8">
                            <div class="input-group">
                                <label class="form-label" id="expiration-date-label">Expiration</label>
                                <div class="input-group">
                                    <select aria-labelledby="expiration-date-label" class="form-select" id="autoSizingSelect" name="expMonth">
                                        <option selected>MM</option>
                                        <?php
                                        for ($i = 1; $i < 13; $i++) {
                                            if ($i < 10)
                                                echo "<option value=\"$i\">0$i</option>";
                                            else
                                                echo "<option value=\"$i\">$i</option>";
                                        }
                                        ?>
                                    </select>
                                    <select aria-labelledby="expiration-date-label" class="form-select" id="autoSizingSelect" name="expYear">
                                        <option selected>YY</option>
                                        <?php
                                        for ($i = 20; $i < 31; $i++) {
                                            echo "<option value=\"$i\">$i</option>";
                                        }
                                        ?>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-12 col-sm-4">
                            <label class="form-label" id="CVV-label">CVV<i class="bi bi-hash"></i></label>
                            <input aria-labelledby="CVV-label" type="text" name="CVV" class="form-control" placeholder="XXX">
                        </div>
                    </div>

                    <div class="row padding-top-25">
                        <div class="input-group padding-left-10">
                            <?php 
                                echo "<input type=\"hidden\" name=\"concertId\" value=\"$bookingpage_idconcert\">";
                                echo "<input type=\"hidden\" name=\"concertTitle\" value=\"$bookingpage_titleconcert\">";
                                echo "<input type=\"hidden\" name=\"concertLocation\" value=\"$bookingpage_locationconcert\">";
                                echo "<input type=\"hidden\" name=\"concertDate\" value=\"$bookingpage_datetime_concert\">";
                                echo "<input type=\"hidden\" id=\"totalAmount\" name=\"totalAmount\" value=\"$ticket_price\">";
                            ?>
                            <input type="hidden" id="numTickets" name="numTickets" value="1">
                            <button type="submit" name="submit" class="sign-up-button buy-button btn btn-dark">Buy ticket</button>
                        </div>
                    </div>
                </form>

            </div>
        </div>
    </div>
</section>

<?php
include_once 'footer.php';
?>