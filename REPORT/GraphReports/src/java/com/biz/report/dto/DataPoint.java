/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biz.report.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 *
 * @author Stelan Briyan
 */
public class DataPoint implements Serializable {

    @JsonProperty(value = "y")
    private double sellingPrice;
    
    @JsonProperty(value = "label")
    private String month;

    public DataPoint() {
    }

    public DataPoint(double sellingPrice, String month) {
        this.sellingPrice = sellingPrice;
        this.month = month;
    }

    /**
     * @return the sellingPrice
     */
    public double getSellingPrice() {
        return sellingPrice;
    }

    /**
     * @param sellingPrice the sellingPrice to set
     */
    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    /**
     * @return the month
     */
    public String getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(String month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return month + " " + sellingPrice;
    }
    
    
    
}
