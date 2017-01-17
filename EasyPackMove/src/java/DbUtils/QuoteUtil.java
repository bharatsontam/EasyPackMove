/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DbUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Quote;
import models.QuoteDetail;
import models.QuoteInfo;
import models.User;

/**
 *
 * @author sontambharath
 */
public class QuoteUtil {
    public static int Insert(Connection con, Quote quote) throws SQLException{
        String sql = "insert into Quotes(UserId,StartAddress,EndAddress,QuoteDetails,QuotePrice,IsPurchased,Distance)"
                + " values(?,?,?,?,?,?,?); ";
        PreparedStatement pstm = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        
        pstm.setInt(1, quote.getUserId());
        pstm.setString(2, quote.getStartAddress());
        pstm.setString(3, quote.getEndAddress());
        pstm.setString(4, quote.getQuoteDetails());
        pstm.setDouble(5, quote.getQuotePrice());
        pstm.setBoolean(6, quote.isIsPurchased());
        pstm.setDouble(7, quote.getDistance());
        
        pstm.executeUpdate();
        
        ResultSet rs = pstm.getGeneratedKeys();
        if(rs.next()){
            return rs.getInt(1);
        }
        
        return -1;
        
    }
    
    public static void Update(Connection con, Quote quote) throws SQLException{
        String sql = "update Quotes set StartAddress=?,EndAddress=?, QuoteDetails=?, QuotePrice=?,IsPurchased=?,Distance=? "
                + " where Id=? and UserId=?";
        PreparedStatement pstm = con.prepareStatement(sql);
        
        pstm.setInt(7, quote.getId());
        pstm.setInt(8, quote.getUserId());
        pstm.setString(1, quote.getStartAddress());
        pstm.setString(2, quote.getEndAddress());
        
        pstm.setString(3, quote.getQuoteDetails());
        pstm.setDouble(4, quote.getQuotePrice());
        pstm.setBoolean(5, quote.isIsPurchased());
        pstm.setDouble(6, quote.getDistance());
        
        pstm.executeUpdate();
    }
    
    public static Quote GetQuoteById(Connection con, int id) throws SQLException{
        String sql = "select * from Quotes where id = ?";
        
        PreparedStatement pstm = con.prepareStatement(sql);
        
        pstm.setInt(1, id);
        
        ResultSet rs = pstm.executeQuery();
        
        if(rs.next()){
            Quote quote = new Quote();
            
            quote.setId(rs.getInt("Id"));
            quote.setUserId(rs.getInt("UserId"));
            quote.setStartAddress(rs.getString("StartAddress"));
            quote.setEndAddress(rs.getString("EndAddress"));
            quote.setQuoteDetails(rs.getString("QuoteDetails"));
            quote.setQuotePrice(rs.getDouble("QuotePrice"));
            quote.setIsPurchased(rs.getBoolean("IsPurchased"));
            quote.setDistance(rs.getDouble("Distance"));
            return quote;
        }
        
        return null;
    }
    
    public static ArrayList<Quote> GetUserQuotes(Connection con, int userId) throws SQLException{
        String sql = "select * from Quotes where userid = ?";
        
        PreparedStatement pstm = con.prepareStatement(sql);
        
        pstm.setInt(1, userId);
        
        ResultSet rs = pstm.executeQuery();
        ArrayList<Quote> quoteList;
        quoteList = new ArrayList<>();
        while(rs.next()){
            Quote quote = new Quote();
            
            quote.setId(rs.getInt("Id"));
            quote.setUserId(rs.getInt("UserId"));
            quote.setStartAddress(rs.getString("StartAddress"));
            quote.setEndAddress(rs.getString("EndAddress"));
            quote.setQuoteDetails(rs.getString("QuoteDetails"));
            quote.setQuotePrice(rs.getDouble("QuotePrice"));
            quote.setIsPurchased(rs.getBoolean("IsPurchased"));
            quote.setDistance(rs.getDouble("Distance"));
            quoteList.add(quote);
        }
        
        return quoteList;
    }
    
    public static QuoteDetail PrepareQuote(Connection con, Quote quote) throws SQLException{
        QuoteDetail quoteDetail = new QuoteDetail();
        User user = UserUtil.GetUserById(con, quote.getUserId());
        
        quoteDetail.setUserId(quote.getUserId());
        quoteDetail.setName(user.getFirstName() + " " + user.getLastName());
        quoteDetail.setEmail(user.getEmail());
        quoteDetail.setPhoneNumber(user.getPhoneNumber());
        quoteDetail.setStartAddress(quote.getStartAddress());
        quoteDetail.setEndAddress(quote.getEndAddress());
        quoteDetail.setDistance(quote.getDistance());
        quoteDetail.setIsPurchased(false);
        
        ArrayList<QuoteInfo> quoteInfoList = new ArrayList<>();
        String quoteDetailStr = "";
        QuoteInfo quoteInfo = new QuoteInfo();
        Double totalPrice = 0d;
        quoteInfo.setKey("LifitingCharges");
        quoteInfo.setValue(278d);        
        quoteInfoList.add(quoteInfo);
        
        totalPrice += 278;        
        quoteDetailStr += "LifitingCharges=278";
        
        quoteInfo = new QuoteInfo();
        quoteInfo.setKey("PackingCharges");
        quoteInfo.setValue(150d);
        quoteInfoList.add(quoteInfo);
        
        totalPrice += 150;
        quoteDetailStr += "|PackingCharges=150";
        
        quoteInfo = new QuoteInfo();
        quoteInfo.setKey("TravellingCharges");
        Double travellingCharges = round(8.99 * quote.getDistance(),2);
        quoteInfo.setValue(travellingCharges);
        quoteInfoList.add(quoteInfo);
        
        totalPrice += travellingCharges;
        quoteDetailStr += "|TravellingCharges="+Double.toString(travellingCharges);
        
        quoteInfo = new QuoteInfo();
        quoteInfo.setKey("GasCharges");
        Double gasCharges = round((totalPrice*5)/100,2);
        quoteInfo.setValue(gasCharges);
        quoteInfoList.add(quoteInfo);
        
        totalPrice+=gasCharges;
        quoteDetailStr += "|GasCharges="+Double.toString(gasCharges);
        
        quoteInfo = new QuoteInfo();
        quoteInfo.setKey("ServiceCharges");
        Double serviceCharges = round((totalPrice*13)/100,2);
        quoteInfo.setValue(serviceCharges);
        quoteInfoList.add(quoteInfo);

        totalPrice += serviceCharges;
        quoteDetailStr += "|ServiceCharges=" + Double.toString(serviceCharges);
        
        quoteInfo = new QuoteInfo();
        quoteInfo.setKey("Taxes");
        Double taxes = round((totalPrice*15)/100,2);
        quoteInfo.setValue(taxes);
        quoteInfoList.add(quoteInfo);
        
        totalPrice += taxes;
        quoteDetailStr += "|Taxes="+Double.toString(taxes);
        
        quote.setIsPurchased(false);
        
        quoteDetail.setQuotePrice(totalPrice);
        
        quote.setQuotePrice(totalPrice);
        
        quote.setQuoteDetails(quoteDetailStr);
        if(quote.getId() == 0){
            int quoteId = Insert(con, quote);
            quoteDetail.setQuoteId(quoteId);
        }
        else{
            Update(con, quote);
            quoteDetail.setQuoteId(quote.getId());
        }
        
        
        return quoteDetail;
    }
    
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    
    public static QuoteDetail GetQuoteDetailById(Connection con, int id) throws SQLException{
        
        Quote quote = GetQuoteById(con, id);
        
        String quoteDetailStr = quote.getQuoteDetails();
        String re1="(\\|)";
        String[] quoteDetailList = quoteDetailStr.split(re1);
        ArrayList<QuoteInfo> quoteInfoList = new ArrayList<>();
        String re2 = "(=)";
        for (String quoteDetailList1 : quoteDetailList) {
            QuoteInfo quoteInfo =new QuoteInfo();
            quoteInfo.setKey(quoteDetailList1.split(re2)[0]);
            quoteInfo.setValue(Double.parseDouble(quoteDetailList1.split(re2)[1]));
            quoteInfoList.add(quoteInfo);
        }
        
        QuoteDetail quoteDetail = new QuoteDetail();
        
        User user = UserUtil.GetUserById(con, quote.getUserId());
        
        quoteDetail.setName(user.getFirstName() + " " + user.getLastName());
        quoteDetail.setEmail(user.getEmail());
        quoteDetail.setPhoneNumber(user.getPhoneNumber());
        quoteDetail.setStartAddress(quote.getStartAddress());
        quoteDetail.setEndAddress(quote.getEndAddress());
        quoteDetail.setDistance(quote.getDistance());
        quoteDetail.setUserId(quote.getUserId());
        quoteDetail.setQuoteDetails(quoteInfoList);
        quoteDetail.setIsPurchased(quote.isIsPurchased());
        quoteDetail.setQuotePrice(quote.getQuotePrice());
        quoteDetail.setQuoteId(quote.getId());
        
        return quoteDetail;
    }
}
