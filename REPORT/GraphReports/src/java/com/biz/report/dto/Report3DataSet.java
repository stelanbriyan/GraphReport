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
public class Report3DataSet implements Serializable{

    @JsonProperty(value = "label")
    private String typeName;

    @JsonProperty(value = "y")
    private double sellingPrice;

    public Report3DataSet(String typeName, double sellingPrice) {
        this.typeName = typeName;
        this.sellingPrice = sellingPrice;
    }

    
    /**
     * @return the typeName
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * @param typeName the typeName to set
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
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
    
    
    
}
