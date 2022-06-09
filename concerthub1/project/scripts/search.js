// Remove search bar from navbar when loaded
function removeSearchBar() {
    var elem = document.getElementById("search-bar");
    elem.parentElement.removeChild(elem);
}

// Search in the database of concerts
//Browser Support Code
function searchFunctionReload() {
    var ajaxRequest;

    ajaxRequest = new XMLHttpRequest();

    // Create a function that will receive data
    // sent from the server and will update
    // div section in the same page.
    ajaxRequest.onreadystatechange = function() {
        if (ajaxRequest.readyState == 4) {
            var ajaxDisplay = document.getElementById("results");
            ajaxDisplay.innerHTML = ajaxRequest.responseText;
        }
    };

    // Now get the value from user and pass it to
    // server script.
    var search = getUrlVars()["search"];
    var pageLimit = getUrlVars()["pageLimit"];
    var page = getUrlVars()["page"];
    var queryString = "?pageLimit=" + pageLimit + "&page=" + page + "&search=" + search;

    ajaxRequest.open("GET", "db/search-db.php" + queryString, true);
    ajaxRequest.send(null);
    
}

function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(
        /[?&]+([^=&]+)=([^&]*)/gi,
        function(m, key, value) {
            vars[key] = value;
        }
    );
    return vars;
}
