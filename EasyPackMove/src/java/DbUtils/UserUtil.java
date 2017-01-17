/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.User;

/**
 *
 * @author sontambharath
 */
public class UserUtil {
    public static void InsertUser(Connection con ,User user) throws SQLException{
        String sql = "Insert into Users(UserName,Password,FirstName,LastName,PhoneNumber,Email,Address1,Address2,City,State,Zip)"
                        + " values(?,?,?,?,?,?,?,?,?,?,?)";
        
        PreparedStatement pstm = con.prepareStatement(sql);
        
        pstm.setString(1, user.getUserName());
        pstm.setString(2, user.getPassword());
        pstm.setString(3, user.getFirstName());
        pstm.setString(4, user.getLastName());
        pstm.setString(5, user.getPhoneNumber());
        pstm.setString(6, user.getEmail());
        pstm.setString(7, user.getAddress1());
        pstm.setString(8, user.getAddress2());
        pstm.setString(9, user.getCity());
        pstm.setString(10, user.getState());
        pstm.setString(11, user.getZip());
        
        pstm.executeUpdate();
        
    }
    
    public static User FindUser(Connection con, String userName, String password) throws SQLException{
        String sql = "select * from users where username=? and password=?";
        
        PreparedStatement pstm = con.prepareStatement(sql);
        
        pstm.setString(1, userName);
        pstm.setString(2, password);
        
        ResultSet rs = pstm.executeQuery();
        
        if (rs.next()) {
          User user = new User();
          user.setId(rs.getInt("Id"));
          user.setUserName(userName);
          user.setPassword(password);
          user.setFirstName(rs.getString("FirstName"));
          user.setLastName(rs.getString("LastName"));
          user.setPhoneNumber(rs.getString("PhoneNumber"));
          user.setEmail(rs.getString("Email"));
          user.setAddress1(rs.getString("Address1"));
          user.setAddress2(rs.getString("Address2"));
          user.setCity(rs.getString("City"));
          user.setState(rs.getString("State"));
          user.setZip(rs.getString("Zip"));
          return user;
      }
      return null;
    }
    
    public static User FindUser(Connection con, String userName) throws SQLException{
        String sql = "select * from users where username=?";
        
        PreparedStatement pstm = con.prepareStatement(sql);
        
        pstm.setString(1, userName);
        
        ResultSet rs = pstm.executeQuery();
        
        if (rs.next()) {
          User user = new User();
          user.setId(rs.getInt("Id"));
          user.setUserName(userName);
          user.setPassword(rs.getString("Password"));
          user.setFirstName(rs.getString("FirstName"));
          user.setLastName(rs.getString("LastName"));
          user.setPhoneNumber(rs.getString("PhoneNumber"));
          user.setEmail(rs.getString("Email"));
          user.setAddress1(rs.getString("Address1"));
          user.setAddress2(rs.getString("Address2"));
          user.setCity(rs.getString("City"));
          user.setState(rs.getString("State"));
          user.setZip(rs.getString("Zip"));
          return user;
      }
      return null;
    }
    
    public static User GetUserById(Connection con, int id) throws SQLException{
        String sql = "select * from users where id=?";
        
        PreparedStatement pstm = con.prepareStatement(sql);
        
        pstm.setInt(1, id);
        
        ResultSet rs = pstm.executeQuery();
        
        if (rs.next()) {
          User user = new User();
          user.setId(rs.getInt("Id"));
          user.setUserName(rs.getString("UserName"));
          user.setFirstName(rs.getString("FirstName"));
          user.setLastName(rs.getString("LastName"));
          user.setPhoneNumber(rs.getString("PhoneNumber"));
          user.setEmail(rs.getString("Email"));
          user.setAddress1(rs.getString("Address1"));
          user.setAddress2(rs.getString("Address2"));
          user.setCity(rs.getString("City"));
          user.setState(rs.getString("State"));
          user.setZip(rs.getString("Zip"));
          return user;
      }
      return null;
    }
    
    public static boolean IsUserExists(Connection con, String userName, String password) throws SQLException{
        String sql = "select * from users where username=? and password=?";
        
        PreparedStatement pstm = con.prepareStatement(sql);
        
        pstm.setString(1, userName);
        pstm.setString(2, password);
        
        ResultSet rs = pstm.executeQuery();
        
        if (rs.next()) {
          return true;
      }
      return false;
    }
}
