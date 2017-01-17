/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import models.Purchase;
import models.Quote;

/**
 *
 * @author sontambharath
 */
public class PurchaseUtil {
    public static void Insert(Connection con, Purchase purchase) throws SQLException{
        String sql = "Insert into Purchases(QuoteId,Cardnumber,CardType,CVV,Expiration,BillingAddress,IsSuccess) "
                + " values(?,?,?,?,?,?,?)";
        PreparedStatement pstm = con.prepareStatement(sql);
        
        pstm.setInt(1,purchase.getQuoteId());
        pstm.setString(2, purchase.getCardNumber());
        pstm.setString(3, purchase.getCardType());
        pstm.setString(4, purchase.getCVV());
        pstm.setString(5, purchase.getExpiration());
        pstm.setString(6,purchase.getBillingAddress());
        pstm.setBoolean(7, purchase.isIsSuccess());
        
        
        pstm.executeUpdate();
        
        Quote quote = QuoteUtil.GetQuoteById(con, purchase.getQuoteId());
        
        quote.setIsPurchased(true);
        
        QuoteUtil.Update(con, quote);       
        
    }
}
