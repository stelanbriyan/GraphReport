/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biz.report.service;

import com.biz.report.dto.Report1DataSet;
import com.biz.report.dto.Report2DataSet;
import com.biz.report.dto.Report3DataSet;
import com.biz.report.dto.Report4DataSet;
import com.biz.report.dto.Report5DataSet;
import com.biz.report.dto.ReportDataSet;
import java.util.List;

/**
 *
 * @author Aux-058
 */
public interface RepReportService {
    
    List<String> readItems();
    
    List<Report1DataSet> readDataForAreaChart(String reps, String months, String year);
    
    public List<Report2DataSet> readDataForPieChart(String reps, String year);
    
    public List<Report3DataSet> readDataForBarChart(String reps, String year);
    
    public List<Report4DataSet> readDataForColumnChart(String reps, String months, String year);
    
    public List<Report5DataSet> readDataForLineChart(String reps, String months, String year);
    
    public ReportDataSet getReports(String reps, String months, String year);
}
