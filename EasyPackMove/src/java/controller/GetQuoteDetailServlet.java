/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DbUtils.QuoteUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.QuoteDetail;
import utils.MyUtils;

/**
 *
 * @author sontambharath
 */
@WebServlet(urlPatterns = "/getquotedetail")
public class GetQuoteDetailServlet extends HttpServlet {

    public GetQuoteDetailServlet(){
        super();
    }

    @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException{
       
       int quoteId = 0;
       
       if(request.getParameter("QuoteId") != null){
           quoteId = Integer.parseInt(request.getParameter("QuoteId"));
       }
       else if(request.getAttribute("QuoteId")!=null){
           quoteId = Integer.parseInt(request.getAttribute("QuoteId").toString());
       }
       
       Connection con = MyUtils.getStoredConnection(request);
       
       QuoteDetail quoteDetail = null;
       try{
          quoteDetail = QuoteUtil.GetQuoteDetailById(con, quoteId);
       }   
       catch(SQLException e){
           
       }
       
       request.getSession().setAttribute("QuoteDetail", quoteDetail);
       
       RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/QuoteDetail.jsp");
       
       dispatcher.forward(request, response);
       
   }
   
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException , IOException{
       doGet(request, response);
   }
}
