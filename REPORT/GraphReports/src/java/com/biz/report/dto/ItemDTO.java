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
public class ItemDTO {
    
    @JsonProperty("invoice_no")
    private String invoiceNo;
    
    @JsonProperty("date")
    private String date;
    
    @JsonProperty("qty")
    private Integer qty;
    
    @JsonProperty("selling_price")
    private String sellingPrice;
    
    @JsonProperty("item_name")
    private String itemName;

    public ItemDTO() {
    }

    public ItemDTO(String invoiceNo, String date, Integer qty, String sellingPrice, String itemName) {
        this.invoiceNo = invoiceNo;
        this.date = date;
        this.qty = qty;
        this.sellingPrice = sellingPrice;
        this.itemName = itemName;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(String sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    
    
}
