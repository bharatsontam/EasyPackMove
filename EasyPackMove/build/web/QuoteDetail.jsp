<%-- 
    Document   : QuoteDetail
    Created on : Nov 11, 2016, 11:53:21 AM
    Author     : sontambharath
--%>

<%@page import="models.QuoteInfo"%>
<%@page import="models.QuoteDetail"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detail - Easy Pack Move</title>
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
        <h2>Quote</h2>

        <div class="row">
            <% 
              Object detail = session.getAttribute("QuoteDetail");
              QuoteDetail _detail = QuoteDetail.class.cast(detail);
            %>
            <input type="hidden" name="QuoteId" value="<%= _detail.getQuoteId() %>"/>
            <input type="hidden" name="QuotePrice" value="<%= _detail.getQuotePrice() %>"/>
            <form class="form-horizontal" role="form">
                <div class="form-group">
                    <label style="font-weight: normal" class="col-md-2 control-label" for="start">Name:</label>
                    <div class="col-md-10">
                        <label class="col-md-7 control-label" for="start"><%= _detail.getName() %></label>
                    </div>
                </div>
                <div class="form-group">
                    <label style="font-weight: normal" class="col-md-2 control-label" for="start">Email:</label>
                    <div class="col-md-10">
                        <label class="col-md-7 control-label" for="start"><%= _detail.getEmail()%></label>
                    </div>
                </div>
                <div class="form-group">
                    <label style="font-weight: normal" class="col-md-2 control-label" for="start">Address:</label>
                    <div class="col-md-10">
                        <label class="col-md-7 control-label" for="start"><%= _detail.getStartAddress()%></label>
                    </div>
                </div>
                <hr />
                <div class="form-group">
                    <label style="font-weight: normal" class="col-md-2 control-label" for="start">Start:</label>
                    <div class="col-md-10">
                        <label class="col-md-7 control-label" for="start"><%= _detail.getStartAddress()%></label>
                    </div>
                </div>
                <div class="form-group">
                    <label style="font-weight: normal" class="col-md-2 control-label" for="start">End:</label>
                    <div class="col-md-10">
                        <label class="col-md-7 control-label" for="start"><%= _detail.getEndAddress()%></label>
                    </div>
                </div>
                <div class="form-group">
                    <label style="font-weight: normal" class="col-md-2 control-label" for="start">Distance:</label>
                    <div class="col-md-10">
                        <label class="col-md-7 control-label" for="start"><%= _detail.getDistance() %> (Miles)</label>
                    </div>
                </div>
                
                <% for(QuoteInfo quoteInfo : _detail.getQuoteDetails()){ %>
                <div class="form-group">
                    <label style="font-weight: normal" class="col-md-2 control-label" for="start"><%= quoteInfo.getKey() %> :</label>
                    <div class="col-md-10">
                        <label class="col-md-7 control-label" for="start">$ <%= quoteInfo.getValue()%></label>
                    </div>
                </div>
                <% }%>
                <hr />
                <div class="form-group">
                    <b><label class="col-md-2 control-label" for="start">QuotePrice:</label></b>
                    <div class="col-md-10">
                        <b><label class="col-md-7 control-label" for="start">$ <%= _detail.getQuotePrice()%></label></b>
                    </div>
                </div>
                <hr />
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-7">
                        <% if(_detail.isIsPurchased()){ %>
                        <a href="/EasyPackMove/quotes" class="btn btn-primary">Back To List</a>
                        <%}
                        else{ %>
                        <a href="/EasyPackMove/purchase?QuoteId=<%= _detail.getQuoteId() %>&QuotePrice=<%= _detail.getQuotePrice()%>" class="btn btn-primary">Purchase</a>
                        |
                        <a href="/EasyPackMove/editquote?QuoteId=<%= _detail.getQuoteId() %>" class="btn btn-primary">Modify Quote</a>
                        |
                        <a href="/EasyPackMove/quotes" class="btn btn-primary">Back To List</a>
                        <%}%>
                        
                    </div>
                </div>

            </form>
        </div>
        <hr />
        <footer>
            <p>&copy; 2016 - Easy Pack Move</p>
        </footer>
    </div>
</body>
</html>