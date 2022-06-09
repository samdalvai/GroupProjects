function updateTotal(index) {
    var ticketPrice = document.getElementById("ticketPrice").innerText;

    var newPrice = (index + 1) * ticketPrice;
    newPrice = newPrice.toFixed(2);

    document.getElementById("tempPrice").innerText = newPrice;
    document.getElementById("numTickets").value = (index + 1);
    document.getElementById("totalAmount").value = newPrice;
}