/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DbUtils.QuoteUtil;
import DbUtils.UserUtil;
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
import models.Quote;
import models.QuoteDetail;
import models.User;
import utils.MyUtils;

/**
 *
 * @author sontambharath
 */
@WebServlet(urlPatterns = "/editquote")
public class EditQuoteServlet extends HttpServlet {

    public EditQuoteServlet(){
        super();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException , IOException{
        int QuoteId = Integer.parseInt(request.getParameter("QuoteId"));
        
        Connection con = null;
        con = MyUtils.getStoredConnection(request);
        Quote quote = null;
        try {
            quote = QuoteUtil.GetQuoteById(con, QuoteId);
        } catch (SQLException ex) {
            Logger.getLogger(EditQuoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        User user = null;
        
        try {
            user = UserUtil.GetUserById(con, quote.getUserId());
        } catch (SQLException ex) {
            Logger.getLogger(EditQuoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.getSession().setAttribute("Distance",quote.getDistance());
        request.getSession().setAttribute("Email",user.getEmail());
        request.getSession().setAttribute("EndAddress",quote.getEndAddress());
        request.getSession().setAttribute("Name",user.getFirstName() + " " + user.getLastName());
        request.getSession().setAttribute("PhoneNumber",user.getPhoneNumber());
        request.getSession().setAttribute("Id",quote.getId());
        request.getSession().setAttribute("QuotePrice",quote.getQuotePrice());
        request.getSession().setAttribute("StartAddress",quote.getStartAddress());
        request.getSession().setAttribute("UserId",quote.getUserId());
        
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/EditQuote.jsp");
        dispatcher.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException , IOException{
        doGet(request, response);
    }

}
