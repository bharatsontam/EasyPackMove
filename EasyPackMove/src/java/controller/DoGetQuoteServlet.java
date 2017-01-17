/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DbUtils.QuoteUtil;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Quote;
import models.QuoteDetail;
import utils.MyUtils;

/**
 *
 * @author sontambharath
 */
@WebServlet(urlPatterns = "/getquote")
public class DoGetQuoteServlet extends HttpServlet {

    public DoGetQuoteServlet(){
        super();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        Quote quote = new Quote();
        
        quote.setUserId(MyUtils.getLoginedUser(request.getSession()).getId());
        quote.setStartAddress(request.getParameter("StartAddress"));
        quote.setEndAddress(request.getParameter("EndAddress"));
        quote.setDistance(Double.parseDouble(request.getParameter("Distance")));
        
        QuoteDetail _quote = null;
        
        boolean hasError = false;
        String errorString;
        errorString = null;
        
        if(quote.getStartAddress() == null || quote.getEndAddress() == null ||
                quote.getDistance() == null ||
                quote.getStartAddress().length() == 0 || quote.getEndAddress().length() == 0){
            hasError = true;
            errorString = "Required parameters are missing";
        }
        else{
            Connection con = null;
            con = MyUtils.getStoredConnection(request);
            try{
                _quote = QuoteUtil.PrepareQuote(con, quote);
            }
            catch(SQLException e){
                hasError = true;
                errorString = e.getMessage();
            }            
        }
        if(hasError){
            request.setAttribute("StartAddress", quote.getStartAddress());
            request.setAttribute("EndAddress", quote.getEndAddress());
            request.setAttribute("Distance", quote.getDistance());
            
            RequestDispatcher dispatcher //
            = this.getServletContext().getRequestDispatcher("/calculatedistance");
            dispatcher.forward(request, response);
        }
        else{
            request.setAttribute("QuoteId", _quote.getQuoteId());
            RequestDispatcher dispatcher //
            = this.getServletContext().getRequestDispatcher("/getquotedetail");
            dispatcher.forward(request, response);
        }
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        doGet(request, response);
    }

}
