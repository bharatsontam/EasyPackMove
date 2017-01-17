<%-- 
    Document   : EditQuote
    Created on : Nov 11, 2016, 12:33:43 PM
    Author     : sontambharath
--%>

<%@page import="models.QuoteDetail"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit - Easy Pack Move</title>
    <script src="js/jquery-1.10.2.js" type="text/javascript"></script>
    <script src="js/bootstrap.js" type="text/javascript"></script>
    <script src="js/bootstrap-alert.js" type="text/javascript"></script>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="css/Site.css" rel="stylesheet" type="text/css" />
    <link href="css/thumbnail-slider.css" rel="stylesheet" type="text/css" />
    <script src="js/thumbnail-slider.js" type="text/javascript"></script>
    <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCRnkB0vy0wtecwLglj1eCIBFMrENrrG48&callback=initialize"
            type="text/javascript"></script>
    <style type="text/css">
        #map_canvas {
            height: 100%;
        }
    </style>
</head>
<body>
    <div class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a href="/EasyPackMove" class="navbar-brand">Easy Pack Move</a>
            </div>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a href="/EasyPackMove">Home</a></li>
                    ${pageContext.session.getAttribute("IsUserLoggedIn") ? '
                    <li><a href="/EasyPackMove/quotes">Quotes</a></li>' : ''}
                    <li><a href="Contact.jsp">Contact</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">                    
                    ${pageContext.session.getAttribute("IsUserLoggedIn") ? '
                    <li><a id="loginLink" href="/EasyPackMove/doLogout">Logout</a></li>' : ' 
                    <li><a id="registerLink" href="Register.jsp">Register</a></li>
                    <li><a href="Login.jsp" id="loginLink" >Login</a></li>'}
                </ul>
            </div>
        </div>
    </div>
    <div class="container body-content">
        <h2>Edit Quote</h2>
        <form class="form-horizontal" role="form" method="post" action="/EasyPackMove/modifyquote">
            <div class="form-horizontal">
                <hr />
                <input type="hidden" value="<%= session.getAttribute("Id") %>" id="QuoteId" name="QuoteId" />
                <input type="hidden" value="<%= session.getAttribute("UserId")%>" id="UserId" name="UserId" />
                <input type="hidden" value="<%= session.getAttribute("QuotePrice")%>" id="QuotePrice" name="QuotePrice" />
                <input type="hidden" value="<%= session.getAttribute("Name") %>" id="Name" name="Name" />

                <div class="form-group">
                    <label class="control-label col-md-2" for="Name">Name</label>
                    <div class="col-md-10">
                        <label class="form-control" for="start"><%= session.getAttribute("Name") %></label>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-2" for="Email">Email</label>
                    <div class="col-md-10">
                        <input class="form-control" name="Email" id="Email" type="email" value="<%= session.getAttribute("Email")%>" />
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-2" for="QuotePrice">Quote Price</label>
                    <div class="col-md-10">
                        <label class="form-control" for="start">$ <%= session.getAttribute("QuotePrice")%></label>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-2 control-label" for="start">Start Address: </label>
                    <div class="col-md-10">
                        <input class="form-control" name="StartAddress" id="StartAddress" type="text" value="<%= session.getAttribute("StartAddress")%>" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-2 control-label" for="end">End Address: </label>
                    <div class="col-md-10">
                        <input class="form-control" name="EndAddress" id="EndAddress" type="text" value="<%= session.getAttribute("EndAddress")%>" />
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-2 col-md-10">
                        <input type="button" class="btn btn-info" value="Calculate Distance" onclick="calcRoute()" />
                    </div>
                </div>
                <div class="form-group">

                    <label class="col-md-2 control-label" for="distance">Distance (Miles): </label>
                    <div class="col-md-10">
                        <input class="form-control" name="Distance" id="Distance" type="text" readonly value="<%= session.getAttribute("Distance")%>" />
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-offset-2 col-md-10">
                        <input type="submit" value="Update" class="btn btn-primary" />
                    </div>
                </div>
            </div>
        </form>
        <div class="row">
            <div id="map_canvas" style="height:400px;"></div>
        </div>
        <hr />
        <footer>
            <p>&copy; 2016 - Easy Pack Move</p>
        </footer>
    </div>
    <script type="text/javascript">
        var directionDisplay;
        var directionsService;
        var map;

        function initialize() {
            directionsService = new google.maps.DirectionsService();
            directionsDisplay = new google.maps.DirectionsRenderer();
            var melbourne = new google.maps.LatLng(-37.813187, 144.96298);
            var myOptions = {
                zoom: 12,
                mapTypeId: google.maps.MapTypeId.ROADMAP,
                center: melbourne
            }

            map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
            directionsDisplay.setMap(map);

            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(function (position) {
                    initialLocation = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
                    map.setCenter(initialLocation);
                });
            }
        }

        function calcRoute() {
            var start = document.getElementById("StartAddress").value;
            var end = document.getElementById("EndAddress").value;
            var distanceInput = document.getElementById("Distance");

            var request = {
                origin: start,
                destination: end,
                travelMode: google.maps.DirectionsTravelMode.WALKING
            };

            directionsService.route(request, function (response, status) {
                if (status == google.maps.DirectionsStatus.OK) {
                    directionsDisplay.setDirections(response);
                    distanceInput.value = Math.round((response.routes[0].legs[0].distance.value / 1000) * 0.621371 * 100, -2) / 100;
                }
            });
        }
    </script>
    <script type="text/javascript">
        initialize();
    </script>
</body>
</html>
