<%-- 
    Document   : CalculateDistance
    Created on : Nov 11, 2016, 11:48:16 AM
    Author     : sontambharath
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
 <html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Calculate - Easy Pack Move</title>
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
        <div class="panel-body">
            <form class="form-horizontal" role="form" method="post" action="/EasyPackMove/getquote">
                <div class="form-group">
                    <label class="col-md-2 control-label" for="start">From Address: </label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="StartAddress" id="start" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-2 control-label" for="end">To Address: </label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="EndAddress" id="end" />
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
                        <input class="form-control" type="text" name="Distance" id="distance" readonly="true" />
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-2 col-md-10">
                        <input type="submit" class="btn btn-primary" value="Get Quote" />
                    </div>
                </div>
            </form>
        </div>
        <div class="row">
            <div id="map_canvas" style="height:500px;"></div>
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
            var start = document.getElementById("start").value;
            var end = document.getElementById("end").value;
            var distanceInput = document.getElementById("distance");

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
