<%-- 
    Document   : DoPurchase
    Created on : Nov 11, 2016, 12:21:54 PM
    Author     : sontambharath
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Purchase - Easy Pack Move</title>
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
        <form class="form-horizontal" role="form" method="post" action="/EasyPackMove/doPurchase">
            <div class="form-horizontal">
                
                <h4>Purchase</h4>
                <hr />
                <input type="hidden" value="<%= session.getAttribute("QuoteId") %>" id="QuoteId" name="QuoteId" />

                <div class="form-group">
                    <label class="control-label col-md-2" for="TotalPrice">Total Price</label>
                    <div class="col-md-10">
                        <b><label class="control-label" id="TotalPrice">$ <%= session.getAttribute("QuotePrice") %></label></b>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2" for="CardType">Card Type</label>
                    <div class="col-md-10">
                        <select class="form-control dropdown" name="CardType" id="CardType">
                            <option value="">Select a card type</option>
                            <option value="VISA">VISA</option>
                            <option value="MASTER">MASTER</option>
                            <option value="Discover">Discover</option>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-2" for="CardNumber">Card Number</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" id="CardNumber" name="CardNumber" />
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-2" for="CardExpiration">Card Expiration</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" id="CardExpiration" name="CardExpiration" />
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-2" for="CVV">CVV</label>
                    <div class="col-md-10">
                        <input class="form-control" type="password"  id="CVV" name="CVV" />
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-2" for="BillingAddress">BillingAddress</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" id="BillingAddress" name="BillingAddress" />
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-2" for="IsBillingSameAsStart">IsBillingSameAsStart</label>
                    <div class="col-md-10">
                        <div class="checkbox">
                            <input class="checkbox" type="checkbox" id="IsBillingSameAsStart" name="IsBillingSameAsStart" />
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-offset-2 col-md-10">
                        <input type="submit" value="Do Pruchase" class="btn btn-primary" />
                    </div>
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