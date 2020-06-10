package com.baeldung.stripe;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ChargeRequest {

    public enum Currency {
        EUR, USD;
    }
    private String description;
    private int amount; // cents
    private Currency currency;
    private String stripeEmail;
    private String stripeToken;
    public String getDescription() {
        return description;
    }
    public int getAmount() {
        return amount;
    }
    public Currency getCurrency() {
        return currency;
    }
    public String getStripeEmail() {
        return stripeEmail;
    }
    public String getStripeToken() {
        return stripeToken;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
    
    
}
