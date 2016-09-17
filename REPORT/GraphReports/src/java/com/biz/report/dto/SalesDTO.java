/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biz.report.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Aux-058
 */
public class SalesDTO {
    
    @JsonProperty("ref_no")
    private String refNo;
    
    @JsonProperty("txn_date")
    private String txtDate;
    
    @JsonProperty("type_name")
    private String typeName;
    
    @JsonProperty("qty")
    private String qty;
    
    @JsonProperty("selling_price")
    private String sellingPrice;

    public SalesDTO() {
    }

    public SalesDTO(String refNo, String txtDate, String typeName, String qty, String sellingPrice) {
        this.refNo = refNo;
        this.txtDate = txtDate;
        this.typeName = typeName;
        this.qty = qty;
        this.sellingPrice = sellingPrice;
    }

    /**
     * @return the refNo
     */
    public String getRefNo() {
        return refNo;
    }

    /**
     * @param refNo the refNo to set
     */
    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    /**
     * @return the txtDate
     */
    public String getTxtDate() {
        return txtDate;
    }

    /**
     * @param txtDate the txtDate to set
     */
    public void setTxtDate(String txtDate) {
        this.txtDate = txtDate;
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
     * @return the qty
     */
    public String getQty() {
        return qty;
    }

    /**
     * @param qty the qty to set
     */
    public void setQty(String qty) {
        this.qty = qty;
    }

    /**
     * @return the sellingPrice
     */
    public String getSellingPrice() {
        return sellingPrice;
    }

    /**
     * @param sellingPrice the sellingPrice to set
     */
    public void setSellingPrice(String sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
    
    
}
