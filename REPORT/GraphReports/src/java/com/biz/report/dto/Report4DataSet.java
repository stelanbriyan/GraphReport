/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biz.report.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Stelan Briyan
 */
public class Report4DataSet implements Serializable {
    
    @JsonProperty(value = "type")
    private String typeName;
    
    @JsonProperty(value = "dataPoints")
    private List<DataPoint> dataPoints;
    
    @JsonProperty(value = "showInLegend")
    private String showInLegend = "true";
    
    @JsonProperty(value = "name")
    private String name;

    public Report4DataSet() {
    }    

    public Report4DataSet(String typeName, List<DataPoint> dataPoints, String name) {
        this.typeName = typeName;
        this.dataPoints = dataPoints;
        this.name = name;
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
     * @return the dataPoints
     */
    public List<DataPoint> getDataPoints() {
        return dataPoints;
    }

    /**
     * @param dataPoints the dataPoints to set
     */
    public void setDataPoints(List<DataPoint> dataPoints) {
        this.dataPoints = dataPoints;
    }

    /**
     * @return the showInLegend
     */
    public String getShowInLegend() {
        return showInLegend;
    }

    /**
     * @param showInLegend the showInLegend to set
     */
    public void setShowInLegend(String showInLegend) {
        this.showInLegend = showInLegend;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
