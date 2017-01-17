<%--
    Document   : Home
    Created on : Nov 10, 2016, 6:11:16 PM
    Author     : sontambharath
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home - Easy Pack Move</title>
    <script src="js/jquery-1.10.2.js" type="text/javascript"></script>
    <script src="js/bootstrap.js" type="text/javascript"></script>
    <script src="js/bootstrap-alert.js" type="text/javascript"></script>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="css/Site.css" rel="stylesheet" type="text/css" />
    <link href="css/thumbnail-slider.css" rel="stylesheet" type="text/css" />
    <script src="js/thumbnail-slider.js" type="text/javascript"></script>
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
                    <li><a href="/EasyPackMove/login" id="loginLink" >Login</a></li>'}
                </ul>
            </div>
        </div>
    </div>
    <div class="container body-content">
        <div class="jumbotron">
            <h1>Easy Pack Move</h1>
            <p><a href="CalculateDistance.jsp" class="btn btn-primary btn-lg">Calculate Distance</a></p>
        </div>

        <div class="row">
            <div class="col-md-8">
                <h2>Getting started</h2>
                <p>
                    Shifting one’s household has never been an easy task. There are many packers and movers which are not registered and have no real office spaces.
                    The whole process of moving begins with making numerous calls to various packers and movers,
                    asking them to visit the house and struggling to decide the mover that suits one’s requirements.
                </p>
            </div>
        </div>
        <br />
        <div class="row">
            <div style="">
                <div id="thumbnail-slider">
                    <div class="inner">
                        <ul>
                            <li>
                                <a class="thumb" href="1.jpg"></a>
                            </li>
                            <li>
                                <a class="thumb" href="2.jpg"></a>
                            </li>
                            <li>
                                <a class="thumb" href="3.jpg"></a>
                            </li>
                            <li>
                                <a class="thumb" href="4.jpg"></a>
                            </li>
                            <li>
                                <a class="thumb" href="5.jpg"></a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <hr />
        <footer>
            <p>&copy; 2016 - Easy Pack Move</p>
        </footer>
    </div>
</body>
</html>

