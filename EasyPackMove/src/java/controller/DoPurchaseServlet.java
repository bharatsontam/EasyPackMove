/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DbUtils.PurchaseUtil;
import DbUtils.QuoteUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Purchase;
import models.Quote;
import utils.MyUtils;

/**
 *
 * @author sontambharath
 */
@WebServlet(urlPatterns = "/doPurchase")
public class DoPurchaseServlet extends HttpServlet {
    
    public DoPurchaseServlet(){
        super();
    }
    
    @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException{
       Purchase purchase = new Purchase();
       
       purchase.setQuoteId(Integer.parseInt(request.getParameter("QuoteId")));
       purchase.setCVV(request.getParameter("CVV"));
       purchase.setCardNumber(request.getParameter("CardNumber"));
       purchase.setCardType(request.getParameter("CardType"));
       purchase.setExpiration(request.getParameter("CardExpiration"));
       purchase.setIsSuccess(true);
       Quote quote = null;
        try {
            quote = QuoteUtil.GetQuoteById(MyUtils.getStoredConnection(request), purchase.getQuoteId());
        } catch (SQLException ex) {
            Logger.getLogger(DoPurchaseServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       if("on".equals(request.getParameter("IsBillingSameAsStart"))){
           purchase.setBillingAddress(quote.getStartAddress());
       }
       else{
           purchase.setBillingAddress(request.getParameter("BillingAddress"));
       }
       boolean hasError = false;
       String errorString = null;
       if(purchase.getCVV() == null || purchase.getCardNumber() == null ||
               purchase.getCardNumber() == null || purchase.getCardType() == null ||
               purchase.getExpiration() == null || purchase.getQuoteId() == 0 ||
               purchase.getCVV().length() == 0 || purchase.getCardNumber().length() == 0||
               purchase.getCardNumber().length() == 0 || purchase.getCardType().length() == 0 ||
               purchase.getExpiration().length() == 0){
           
           hasError = true;
           errorString = "Required parameters are missing";
           
       }
       else{
           try {
               Connection con = null;
               con = MyUtils.getStoredConnection(request);               
               PurchaseUtil.Insert(con, purchase);
           } catch (SQLException ex) {
               Logger.getLogger(DoPurchaseServlet.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
       
       if(hasError){
           RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/PurchaseFailure.jsp");
           dispatcher.forward(request, response);
       }
       else{
           RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/PurchaseSuccess.jsp");
           dispatcher.forward(request, response);
       }
       
   }
   
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException , IOException{
       doGet(request, response);
   }
}
