/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;

/**
 *
 * @author sontambharath
 */
public class Quote {
    private int Id;
    private int UserId;
    private String StartAddress;
    private String EndAddress;
    private String QuoteDetails;
    private Double QuotePrice;
    private boolean IsPurchased;
    private Double Distance;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public String getStartAddress() {
        return StartAddress;
    }

    public void setStartAddress(String StartAddress) {
        this.StartAddress = StartAddress;
    }

    public String getEndAddress() {
        return EndAddress;
    }

    public void setEndAddress(String EndAddress) {
        this.EndAddress = EndAddress;
    }

    public String getQuoteDetails() {
        return QuoteDetails;
    }

    public void setQuoteDetails(String QuoteDetails) {
        this.QuoteDetails = QuoteDetails;
    }

    public Double getQuotePrice() {
        return QuotePrice;
    }

    public void setQuotePrice(Double QuotePrice) {
        this.QuotePrice = QuotePrice;
    }

    public boolean isIsPurchased() {
        return IsPurchased;
    }

    public void setIsPurchased(boolean IsPurchased) {
        this.IsPurchased = IsPurchased;
    }

    public Double getDistance() {
        return Distance;
    }

    public void setDistance(Double Distance) {
        this.Distance = Distance;
    }
}
