/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sontambharath
 */
@WebServlet(urlPatterns = "/purchase")
public class PurchaseServlet extends HttpServlet {

    public PurchaseServlet(){
        super();
    }
    
    @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException{
       
       request.getSession().setAttribute("QuoteId", Integer.parseInt(request.getParameter("QuoteId")));
       request.getSession().setAttribute("QuotePrice", Double.parseDouble(request.getParameter("QuotePrice")));
       RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/DoPurchase.jsp");
       
       dispatcher.forward(request, response);
   }
   
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException , IOException{
       doGet(request, response);
   }

}
