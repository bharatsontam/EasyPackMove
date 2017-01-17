<%-- 
    Document   : Register
    Created on : Nov 11, 2016, 11:36:32 AM
    Author     : sontambharath
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register - Easy Pack Move</title>
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
                    <li><a href="Login.jsp" id="loginLink" >Login</a></li>'}
                </ul>
            </div>
        </div>
    </div>
    <div class="container body-content">
        <h2>Register.</h2>

        <form class="form-horizontal" role="form" method="POST" action="/EasyPackMove/DoRegister">
            <h4>Create a new account.</h4>
            <hr />
            <div class="form-group">
                <label for="UserName" class="col-md-2 control-label">UserName</label>
                <div class="col-md-10">
                    <input name="UserName" class="form-control" id="UserName" type="text" />
                </div>
            </div>
            <div class="form-group">
                <label for="Password" class="col-md-2 control-label">Password</label>
                <div class="col-md-10">
                    <input name="Password" class="form-control" id="Password" type="password" />
                </div>
            </div>
            <div class="form-group">
                <label for="ConfirmPassword" class="col-md-2 control-label">Confirm Password</label>
                <div class="col-md-10">
                    <input name="ConfirmPassword" class="form-control" id="ConfirmPassword" type="password" />
                </div>
            </div>
            <div class="form-group">
                <label for="FirstName" class="col-md-2 control-label">First Name</label>
                <div class="col-md-10">
                    <input name="FirstName" class="form-control" id="FirstName" type="text" />
                </div>
            </div>
            <div class="form-group">
                <label for="LastName" class="col-md-2 control-label">Last Name</label>
                <div class="col-md-10">
                    <input name="LastName" class="form-control" id="Last Name" type="text" />
                </div>
            </div>
            <div class="form-group">
                <label for="Email" class="col-md-2 control-label">Email</label>
                <div class="col-md-10">
                    <input name="Email" class="form-control" id="Email" type="email" />
                </div>
            </div>
            <div class="form-group">
                <label for="PhoneNumber" class="col-md-2 control-label">Phone Number</label>
                <div class="col-md-10">
                    <input name="PhoneNumber" class="form-control" id="PhoneNumber" type="text" />
                </div>
            </div>
            <div class="form-group">
                <label for="Address1" class="col-md-2 control-label">Address1</label>
                <div class="col-md-10">
                    <input name="Address1" class="form-control" id="Address1" type="text" />
                </div>
            </div>
            <div class="form-group">
                <label for="Address2" class="col-md-2 control-label">Address2</label>
                <div class="col-md-10">
                    <input name="Address2" class="form-control" id="Address2" type="text" />
                </div>
            </div>
            <div class="form-group">
                <label for="City" class="col-md-2 control-label">City</label>
                <div class="col-md-10">
                    <input name="City" class="form-control" id="City" type="text" />
                </div>
            </div>
            <div class="form-group">
                <label for="State" class="col-md-2 control-label">State</label>
                <div class="col-md-10">
                    <input name="State" class="form-control" id="State" type="text" />
                </div>
            </div>
            <div class="form-group">
                <label for="Zip" class="col-md-2 control-label">Zip</label>
                <div class="col-md-10">
                    <input name="Zip" class="form-control" id="Zip" type="text" />
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-offset-2 col-md-10">
                    <input type="submit" class="btn btn-default" value="Register" />
                </div>
            </div>
        </form>
        <hr />
        <footer>
            <p>&copy; 2016 - Easy Pack Move</p>
        </footer>
    </div>
</body>
</html>
