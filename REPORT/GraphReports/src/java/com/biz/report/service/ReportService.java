package com.biz.report.service;

import com.biz.report.dto.ItemDTO;
import com.biz.report.dto.Report1DataSet;
import com.biz.report.dto.Report2DataSet;
import com.biz.report.dto.Report3DataSet;
import com.biz.report.dto.Report4DataSet;
import com.biz.report.dto.Report5DataSet;
import com.biz.report.dto.ReportDataSet;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Stelan Briyan
 */
public interface ReportService {

    public List<Report1DataSet> read(String types, String months, String year);

    public List<Report2DataSet> readPieChart(String types, String months, String year);

    public List<Report3DataSet> readBarChart(String types, String months, String year);

    public List<Report4DataSet> readColumnChart(String types, String months, String year);

    public List<Report5DataSet> readLineChart(String types, String months, String year);

    public ReportDataSet getReports(String types, String months, String year);

    public List<String> getTypes();
    
    public List<ItemDTO> readItemByType(String type, String year, String month);
}
