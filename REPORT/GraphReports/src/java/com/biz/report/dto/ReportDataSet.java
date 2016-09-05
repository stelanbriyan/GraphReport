/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biz.report.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 *
 * @author Stelan Briyan
 */
public class ReportDataSet {
    
    @JsonProperty(value = "report_1")
    private List<Report1DataSet> report1;
    
    @JsonProperty(value = "report_2")
    private List<Report2DataSet> report2;
    
    @JsonProperty(value = "report_3")
    private List<Report3DataSet> report3;
    
    @JsonProperty(value = "report_4")
    private List<Report4DataSet> report4;
    
    @JsonProperty(value = "report_5")
    private List<Report5DataSet> report5;

    public ReportDataSet() {
    }

    public ReportDataSet(List<Report1DataSet> report1, List<Report2DataSet> report2, List<Report3DataSet> report3, List<Report4DataSet> report4, List<Report5DataSet> report5) {
        this.report1 = report1;
        this.report2 = report2;
        this.report3 = report3;
        this.report4 = report4;
        this.report5 = report5;
    }
    
    /**
     * @return the report1
     */
    public List<Report1DataSet> getReport1() {
        return report1;
    }

    /**
     * @param report1 the report1 to set
     */
    public void setReport1(List<Report1DataSet> report1) {
        this.report1 = report1;
    }

    /**
     * @return the report2
     */
    public List<Report2DataSet> getReport2() {
        return report2;
    }

    /**
     * @param report2 the report2 to set
     */
    public void setReport2(List<Report2DataSet> report2) {
        this.report2 = report2;
    }

    /**
     * @return the report3
     */
    public List<Report3DataSet> getReport3() {
        return report3;
    }

    /**
     * @param report3 the report3 to set
     */
    public void setReport3(List<Report3DataSet> report3) {
        this.report3 = report3;
    }

    /**
     * @return the report4
     */
    public List<Report4DataSet> getReport4() {
        return report4;
    }

    /**
     * @param report4 the report4 to set
     */
    public void setReport4(List<Report4DataSet> report4) {
        this.report4 = report4;
    }

    /**
     * @return the report5
     */
    public List<Report5DataSet> getReport5() {
        return report5;
    }

    /**
     * @param report5 the report5 to set
     */
    public void setReport5(List<Report5DataSet> report5) {
        this.report5 = report5;
    }
    
    
}
