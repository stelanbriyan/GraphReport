/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biz.report.service.impl;

import com.biz.report.dao.RepReportDao;
import com.biz.report.domain.MappingEngine;
import com.biz.report.domain.Report1;
import com.biz.report.domain.Report2;
import com.biz.report.dto.DataPoint;
import com.biz.report.dto.ItemDTO;
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

    public List<String> readReps() {
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
        if (!StringUtils.isEmpty(reps) && reps.contains("[")) {
            reps = reps.substring(1, reps.length() - 1);
        }
        List list = repReportDao.read(reps, year);
        List<Report2> reportList = new MappingEngine().getPieChartReport(list);
        List<Report2DataSet> dataSets = new ArrayList<Report2DataSet>();
        for (Report2 r : reportList) {
            dataSets.add(new Report2DataSet(r.getTypeName(), r.getAmount()));
        }
        return dataSets;
    }

    public List<Report3DataSet> readDataForBarChart(String reps, String year) {
        if (!StringUtils.isEmpty(reps) && reps.contains("[")) {
            reps = reps.substring(1, reps.length() - 1);
        }
        List list = repReportDao.read(reps, year);
        List<Report2> reportList = new MappingEngine().getPieChartReport(list);
        List<Report3DataSet> dataSets = new ArrayList<Report3DataSet>();
        for (Report2 r : reportList) {
            dataSets.add(new Report3DataSet(r.getTypeName(), r.getAmount()));
        }
        return dataSets;
    }

    public List<Report4DataSet> readDataForColumnChart(String reps, String months, String year) {
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
        List<Report4DataSet> dataSets = new ArrayList<Report4DataSet>();
        for (int i = 0; i < typeCount; i++) {
            List<DataPoint> dataPoints = constructDataPoints(reportList, typeAr[i].trim(), monthAr, i);
            dataSets.add(new Report4DataSet("column", dataPoints, typeAr[i]));
        }
        return dataSets;
    }

    public List<Report5DataSet> readDataForLineChart(String reps, String months, String year) {
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
        List<Report5DataSet> dataSets = new ArrayList<Report5DataSet>();
        for (int i = 0; i < typeCount; i++) {
            List<DataPoint> dataPoints = constructDataPoints(reportList, typeAr[i].trim(), monthAr, i);
            dataSets.add(new Report5DataSet(dataPoints, typeAr[i]));
        }
        return dataSets;
    }
    
    @Override
    public List<ItemDTO> readByRepName(String reps, String year, String month) {
        if (!StringUtils.isEmpty(month) && month.contains("[")) {
            month = month.substring(1, month.length() - 1);
        }
        if (!StringUtils.isEmpty(reps) && !reps.contains("'")) {
            reps = "'" + reps + "'";
        }
        List list = repReportDao.readByRepName(reps, year, month);
        List<ItemDTO> itemDTOs = new ArrayList<ItemDTO>();
        for (Object object : list) {
            ItemDTO itemDTO = constructItemDTO(object);
            itemDTOs.add(itemDTO);
        }
        return itemDTOs;
    }

    private ItemDTO constructItemDTO(Object ob) {
        Object[] obAr = (Object[]) ob;
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setInvoiceNo(obAr[0].toString());
        itemDTO.setItemName(obAr[1].toString());
        itemDTO.setDate(obAr[2].toString().substring(0, 10));
        itemDTO.setQty(obAr[3].toString());
        itemDTO.setSellingPrice(obAr[4].toString());
        return itemDTO;
    }

    public ReportDataSet getReports(String reps, String months, String year) {
        List<Report1DataSet> report1 = readDataForAreaChart(reps, months, year);
        List<Report2DataSet> report2 = readDataForPieChart(reps, year);
        List<Report3DataSet> report3 = readDataForBarChart(reps, year);
        List<Report4DataSet> report4 = readDataForColumnChart(reps, months, year);
        List<Report5DataSet> report5 = readDataForLineChart(reps, months, year);
        ReportDataSet reportDataSet = new ReportDataSet();
        reportDataSet.setReport1(report1);
        reportDataSet.setReport2(report2);
        reportDataSet.setReport3(report3);
        reportDataSet.setReport4(report4);
        reportDataSet.setReport5(report5);
        return reportDataSet;
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
