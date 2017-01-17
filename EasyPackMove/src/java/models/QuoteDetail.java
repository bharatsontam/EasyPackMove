/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;

/**
 *
 * @author sontambharath
 */
public class QuoteDetail {
    private int QuoteId;
    private String Name;
    private String Email;
    private String PhoneNumber;
    private String StartAddress;
    private String EndAddress;
    private Double Distance;
    private Double QuotePrice;
    private ArrayList<QuoteInfo> QuoteDetails;
    private boolean IsPurchased;
    private int UserId;

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
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

    public Double getDistance() {
        return Distance;
    }

    public void setDistance(Double Distance) {
        this.Distance = Distance;
    }

    public Double getQuotePrice() {
        return QuotePrice;
    }

    public void setQuotePrice(Double QuotePrice) {
        this.QuotePrice = QuotePrice;
    }

    public ArrayList<QuoteInfo> getQuoteDetails() {
        return QuoteDetails;
    }

    public void setQuoteDetails(ArrayList<QuoteInfo> QuoteDetails) {
        this.QuoteDetails = QuoteDetails;
    }

    public boolean isIsPurchased() {
        return IsPurchased;
    }

    public void setIsPurchased(boolean IsPurchased) {
        this.IsPurchased = IsPurchased;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public int getQuoteId() {
        return QuoteId;
    }

    public void setQuoteId(int QuoteId) {
        this.QuoteId = QuoteId;
    }
    
    
}


