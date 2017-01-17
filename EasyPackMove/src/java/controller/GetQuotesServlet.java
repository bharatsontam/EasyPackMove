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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.MyUtils;

/**
 *
 * @author sontambharath
 */
@WebServlet("/quotes")
public class GetQuotesServlet extends HttpServlet {

    public GetQuotesServlet(){
        super();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        HttpSession session = request.getSession();
        
        Connection con = null;
        con = MyUtils.getStoredConnection(request);
        
        int userId = MyUtils.getLoginedUser(session).getId();
        
        try {
            request.getSession().setAttribute("Quotes", QuoteUtil.GetUserQuotes(con, userId) );
        } catch (SQLException ex) {
            Logger.getLogger(GetQuotesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/Quotes.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        doGet(request, response);
    }
}
