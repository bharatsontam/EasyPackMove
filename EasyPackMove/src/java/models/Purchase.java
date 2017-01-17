/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author sontambharath
 */
public class Purchase {
    private int Id;
    private int QuoteId;
    private String CardNumber;
    private String CardType;
    private String CVV;
    private String Expiration;
    private String BillingAddress;
    private boolean IsSuccess;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getQuoteId() {
        return QuoteId;
    }

    public void setQuoteId(int QuoteId) {
        this.QuoteId = QuoteId;
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(String CardNumber) {
        this.CardNumber = CardNumber;
    }

    public String getCardType() {
        return CardType;
    }

    public void setCardType(String CardType) {
        this.CardType = CardType;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public String getExpiration() {
        return Expiration;
    }

    public void setExpiration(String Expiration) {
        this.Expiration = Expiration;
    }

    public boolean isIsSuccess() {
        return IsSuccess;
    }

    public void setIsSuccess(boolean IsSuccess) {
        this.IsSuccess = IsSuccess;
    }

    public String getBillingAddress() {
        return BillingAddress;
    }

    public void setBillingAddress(String BillingAddress) {
        this.BillingAddress = BillingAddress;
    }
}
