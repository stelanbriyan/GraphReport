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
public interface ItemDashBoardService {
    
    List<String> readItems();
    
    List<Report1DataSet> readDataForAreaChart(String items, String months, String year);
    
    public List<Report2DataSet> readDataForPieChart(String items, String year, String month);
    
    public List<Report3DataSet> readDataForBarChart(String items, String year, String month);
    
    public List<Report4DataSet> readDataForColumnChart(String items, String months, String year);
    
    public List<Report5DataSet> readDataForLineChart(String items, String months, String year);
    
    List readByItemName(String itemName, String year, String month);
    
    public ReportDataSet getReports(String items, String months, String year);
}
