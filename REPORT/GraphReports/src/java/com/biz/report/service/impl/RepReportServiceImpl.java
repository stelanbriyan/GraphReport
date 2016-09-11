/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biz.report.service.impl;

import com.biz.report.dao.RepReportDao;
import com.biz.report.domain.MappingEngine;
import com.biz.report.domain.Report1;
import com.biz.report.dto.DataPoint;
import com.biz.report.dto.Report1DataSet;
import com.biz.report.dto.Report2DataSet;
import com.biz.report.dto.Report3DataSet;
import com.biz.report.dto.Report4DataSet;
import com.biz.report.dto.Report5DataSet;
import com.biz.report.dto.ReportDataSet;
import com.biz.report.service.RepReportService;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Aux-058
 */
@Service
public class RepReportServiceImpl implements RepReportService {
    
    @Autowired
    private RepReportDao repReportDao;
    
    private final Log logger = LogFactory.getLog(RepReportServiceImpl.class);

    public List<String> readItems() {
        return repReportDao.readReps();
    }

    public List<Report1DataSet> readDataForAreaChart(String reps, String months, String year) {
        if (!StringUtils.isEmpty(reps) && reps.contains("[")) {
            reps = reps.substring(1, reps.length() - 1);
        }
        String[] typeAr;
        if (!StringUtils.isEmpty(reps) && reps.contains(",")) {
            typeAr = reps.split("[,]");
        } else {
            typeAr = new String[]{reps};
        }
        if (!StringUtils.isEmpty(months) && months.contains("[")) {
            months = months.substring(1, months.length() - 1);
        }
        String[] monthAr;
        if (!StringUtils.isEmpty(months) && months.contains(",")) {
            monthAr = months.split("[,]");
        } else {
            monthAr = new String[]{months};
        }
        int typeCount = typeAr.length;
        List list = repReportDao.read(reps, months, year);
        List<Report1> reportList = new MappingEngine().getList(list);
        logger.info(reportList.size());
        List<Report1DataSet> dataSets = new ArrayList<Report1DataSet>();
        for (int i = 0; i < typeCount; i++) {
            List<DataPoint> dataPoints = constructDataPoints(reportList, typeAr[i].trim(), monthAr, i);
            dataSets.add(new Report1DataSet("stackedArea", dataPoints, typeAr[i]));
        }
        return dataSets;
    }

    public List<Report2DataSet> readDataForPieChart(String reps, String year) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Report3DataSet> readDataForBarChart(String reps, String year) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Report4DataSet> readDataForColumnChart(String reps, String months, String year) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Report5DataSet> readDataForLineChart(String reps, String months, String year) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ReportDataSet getReports(String reps, String months, String year) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @ResponseBody
    private List<DataPoint> constructDataPoints(List<Report1> reportList, String rep, String[] monthAr, int i) {
        List<DataPoint> dataPoints = new ArrayList<DataPoint>();
        List<String> monthList = new ArrayList<String>();
        for (String month : monthAr) {
            monthList.add(month.trim());
        }
        List<Report1> reportList1 = getReportList(rep, reportList);

        for (String month : monthList) {
            double sellingPrice = 0;
            for (Report1 report : reportList1) {
                if (month.equalsIgnoreCase("'" + report.getMonthName().trim() + "'")) {
                    sellingPrice = report.getSellingPrice();
                    break;
                }
            }
            DataPoint dataPoint = new DataPoint();
            dataPoint.setMonth(month);
            dataPoint.setSellingPrice(sellingPrice);
            dataPoints.add(dataPoint);
        }
        return dataPoints;
    }
    
    public List<Report1> getReportList(String rep, List<Report1> reportList) {
        List<Report1> list = new ArrayList<Report1>();

        for (Report1 report1 : reportList) {
            String typeName = "'" + report1.getTypeName().trim() + "'";
            if (rep.equalsIgnoreCase(typeName)) {
                list.add(report1);
            }
        }

        return list;
    }
}
