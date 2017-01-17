/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DbUtils.UserUtil;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.User;
import utils.MyUtils;

/**
 *
 * @author sontambharath
 */
@WebServlet(name = "DoRegisterServlet", urlPatterns = {"/DoRegister"})
public class DoRegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    public DoRegisterServlet(){
        super();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = new User();
        
        user.setUserName(request.getParameter("UserName"));
        user.setPassword(request.getParameter("Password"));
        user.setFirstName(request.getParameter("FirstName"));
        user.setLastName(request.getParameter("LastName"));
        user.setEmail(request.getParameter("Email"));
        String confirmPassword = request.getParameter("ConfirmPassword");
        user.setPhoneNumber(request.getParameter("PhoneNumber"));
        user.setAddress1(request.getParameter("Address1"));
        user.setAddress2(request.getParameter("Address2"));
        user.setCity(request.getParameter("City"));
        user.setState(request.getParameter("State"));
        user.setZip(request.getParameter("Zip"));
        boolean hasError = false;
        String errorString;
        errorString = null;
        if(user.getUserName() == null || user.getPassword() == null ||
                user.getFirstName() == null || user.getLastName() == null ||
                user.getAddress1() == null || user.getCity() == null ||
                user.getState() == null || user.getZip() == null ||
                user.getEmail() == null || user.getPhoneNumber() == null ||
                user.getUserName().length() == 0 || user.getPassword().length() == 0||
                user.getFirstName().length() == 0 || user.getLastName().length() == 0 ||
                user.getAddress1().length() == 0 || user.getCity().length() == 0||
                user.getState().length() == 0 || user.getZip().length() == 0 ||
                user.getEmail().length() == 0 || user.getPhoneNumber().length() == 0){
            hasError = true;
            errorString = "Required parameters are missing";
        }
        else{
            if(user.getPassword().equals(confirmPassword)){
                Connection con = null;
                con = MyUtils.getStoredConnection(request);
                try{
                    UserUtil.InsertUser(con, user);
                    
                }
                catch(SQLException e){
                    hasError = true;
                    errorString = e.getMessage();
                }
            }
            else{
                hasError = true;
                errorString = "Confirm password should match with password";
            }
        }
        
        if (hasError) {
            request.setAttribute("UserName", user.getUserName());
            request.setAttribute("Password", user.getPassword());
            request.setAttribute("ConfirmPassword", confirmPassword);
            request.setAttribute("FirstName", user.getFirstName());
            request.setAttribute("LastName", user.getLastName());
            request.setAttribute("Email", user.getEmail());
            request.setAttribute("PhoneNumber", user.getPhoneNumber());
            request.setAttribute("Address1", user.getAddress1());
            request.setAttribute("Address2", user.getAddress2());
            request.setAttribute("City", user.getCity());
            request.setAttribute("State", user.getState());
            request.setAttribute("Zip", user.getZip());
            
            RequestDispatcher dispatcher //
            = this.getServletContext().getRequestDispatcher("/Register");
            dispatcher.forward(request, response);
            
        }else{
            response.sendRedirect(request.getContextPath() + "/home");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        doGet(request, response);
    }

}
