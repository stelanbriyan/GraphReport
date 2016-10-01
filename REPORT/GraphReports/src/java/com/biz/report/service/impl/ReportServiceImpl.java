/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biz.report.service.impl;

import com.biz.report.dao.ReportDao;
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
import com.biz.report.service.ReportService;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Stelan Briyan
 */
@Service
public class ReportServiceImpl implements ReportService {

    private final Log logger = LogFactory.getLog(ReportServiceImpl.class);

    @Autowired
    private ReportDao reportDao;

    @Override
    public List<Report1DataSet> read(String types, String months, String year) {
        if (!StringUtils.isEmpty(types) && types.contains("[")) {
            types = types.substring(1, types.length() - 1);
        }
        String[] typeAr;
        if (!StringUtils.isEmpty(types) && types.contains(",")) {
            typeAr = types.split("[,]");
        } else {
            typeAr = new String[]{types};
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
        List list = reportDao.read(types, year, months);
        List<Report1> reportList = new MappingEngine().getList(list);
        logger.info(reportList.size());
        List<Report1DataSet> dataSets = new ArrayList<Report1DataSet>();
        List<String> customerList = new ArrayList<String>();
        for (int i = 0; i < reportList.size(); i++) {
            if (CollectionUtils.isEmpty(dataSets)) {
                List<DataPoint> dataPoints
                        = constructDataPoints(reportList, reportList.get(i).getTypeName().trim(), monthAr, i);
                dataSets.add(new Report1DataSet("stackedArea", dataPoints, reportList.get(i).getTypeName().trim()));
                customerList.add(reportList.get(i).getTypeName());
            } else if (!customerList.contains(reportList.get(i).getTypeName())) {
                List<DataPoint> dataPoints
                        = constructDataPoints(reportList, reportList.get(i).getTypeName().trim(), monthAr, i);
                dataSets.add(new Report1DataSet("stackedArea", dataPoints, reportList.get(i).getTypeName().trim()));
                customerList.add(reportList.get(i).getTypeName());
            }
        }
        return dataSets;
    }

    @ResponseBody
    private List<DataPoint> constructDataPoints(List<Report1> reportList, String type, String[] monthAr, int i) {
        List<DataPoint> dataPoints = new ArrayList<DataPoint>();
        List<String> monthList = new ArrayList<String>();
        for (String month : monthAr) {
            monthList.add(month.trim());
        }
        List<Report1> reportList1 = getReportList(type, reportList);

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

    public List<Report1> getReportList(String type, List<Report1> reportList) {
        List<Report1> list = new ArrayList<Report1>();

        for (Report1 report1 : reportList) {
            String typeName = report1.getTypeName().trim();
            if (type.equalsIgnoreCase(typeName)) {
                list.add(report1);
            }
        }

        return list;
    }

    @Override
    public List<Report2DataSet> readPieChart(String types, String months, String year) {
        if (!StringUtils.isEmpty(types) && types.contains("[")) {
            types = types.substring(1, types.length() - 1);
        }
        if (!StringUtils.isEmpty(months) && months.contains("[")) {
            months = months.substring(1, months.length() - 1);
        }
        List list = reportDao.readPieChartData(types, year, months);
        List<Report2> reportList = new MappingEngine().getPieChartReport(list);
        List<Report2DataSet> dataSets = new ArrayList<Report2DataSet>();
        for (Report2 r : reportList) {
            dataSets.add(new Report2DataSet(r.getTypeName(), r.getAmount()));
        }
        return dataSets;
    }

    @Override
    public ReportDataSet getReports(String types, String months, String year) {
        List<Report1DataSet> report1 = read(types, months, year);
        List<Report2DataSet> report2 = readPieChart(types, months, year);
        List<Report3DataSet> report3 = readBarChart(types, months, year);
        List<Report4DataSet> report4 = readColumnChart(types, months, year);
        List<Report5DataSet> report5 = readLineChart(types, months, year);
        return new ReportDataSet(report1, report2, report3, report4, report5);
    }

    @Override
    public List<Report3DataSet> readBarChart(String types, String months, String year) {
        if (!StringUtils.isEmpty(types) && types.contains("[")) {
            types = types.substring(1, types.length() - 1);
        }
        if (!StringUtils.isEmpty(months) && months.contains("[")) {
            months = months.substring(1, months.length() - 1);
        }
        List list = reportDao.readPieChartData(types, year, months);
        List<Report2> reportList = new MappingEngine().getPieChartReport(list);
        List<Report3DataSet> dataSets = new ArrayList<Report3DataSet>();
        for (Report2 r : reportList) {
            dataSets.add(new Report3DataSet(r.getTypeName(), r.getAmount()));
        }
        return dataSets;
    }

    @Override
    public List<Report4DataSet> readColumnChart(String types, String months, String year) {
        if (!StringUtils.isEmpty(types) && types.contains("[")) {
            types = types.substring(1, types.length() - 1);
        }
        String[] typeAr;
        if (!StringUtils.isEmpty(types) && types.contains(",")) {
            typeAr = types.split("[,]");
        } else {
            typeAr = new String[]{types};
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
        List list = reportDao.read(types, year, months);
        List<Report1> reportList = new MappingEngine().getList(list);
        logger.info(reportList.size());
        List<Report4DataSet> dataSets = new ArrayList<Report4DataSet>();
        List<String> customerList = new ArrayList<String>();
        for (int i = 0; i < reportList.size(); i++) {
            if (CollectionUtils.isEmpty(dataSets)) {
                List<DataPoint> dataPoints
                        = constructDataPoints(reportList, reportList.get(i).getTypeName().trim(), monthAr, i);
                dataSets.add(new Report4DataSet("column", dataPoints, reportList.get(i).getTypeName().trim()));
                customerList.add(reportList.get(i).getTypeName());
            } else if (!customerList.contains(reportList.get(i).getTypeName())) {
                List<DataPoint> dataPoints
                        = constructDataPoints(reportList, reportList.get(i).getTypeName().trim(), monthAr, i);
                dataSets.add(new Report4DataSet("column", dataPoints, reportList.get(i).getTypeName().trim()));
                customerList.add(reportList.get(i).getTypeName());
            }
        }
        return dataSets;
    }

    @Override
    public List<Report5DataSet> readLineChart(String types, String months, String year) {
        if (!StringUtils.isEmpty(types) && types.contains("[")) {
            types = types.substring(1, types.length() - 1);
        }
        String[] typeAr;
        if (!StringUtils.isEmpty(types) && types.contains(",")) {
            typeAr = types.split("[,]");
        } else {
            typeAr = new String[]{types};
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
        List list = reportDao.read(types, year, months);
        List<Report1> reportList = new MappingEngine().getList(list);
        logger.info(reportList.size());
        List<Report5DataSet> dataSets = new ArrayList<Report5DataSet>();
        List<String> customerList = new ArrayList<String>();
        for (int i = 0; i < reportList.size(); i++) {
            if (CollectionUtils.isEmpty(dataSets)) {
                List<DataPoint> dataPoints
                        = constructDataPoints(reportList, reportList.get(i).getTypeName().trim(), monthAr, i);
                dataSets.add(new Report5DataSet(dataPoints, reportList.get(i).getTypeName().trim()));
                customerList.add(reportList.get(i).getTypeName());
            } else if (!customerList.contains(reportList.get(i).getTypeName())) {
                List<DataPoint> dataPoints
                        = constructDataPoints(reportList, reportList.get(i).getTypeName().trim(), monthAr, i);
                dataSets.add(new Report5DataSet(dataPoints, reportList.get(i).getTypeName().trim()));
                customerList.add(reportList.get(i).getTypeName());
            }
        }
        return dataSets;
    }

    @Override
    public List<String> getTypes() {
//        List<String> list = new ArrayList<String>();

        List list = reportDao.readType();

        return list;
    }

    @Override
    public List<ItemDTO> readItemByType(String type, String year, String month) {
        if (!StringUtils.isEmpty(month) && month.contains("[")) {
            month = month.substring(1, month.length() - 1);
        }
        if (!StringUtils.isEmpty(type) && !type.contains("'")) {
            type = "'" + type + "'";
        }
        List list = reportDao.readItemByType(type, year, month);
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
        itemDTO.setDate(obAr[1].toString().substring(0, 10));
        itemDTO.setItemName(obAr[2].toString());
        itemDTO.setQty(obAr[3].toString());
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMinimumFractionDigits(2);
        itemDTO.setSellingPrice(numberFormat.format(Double.parseDouble(obAr[4].toString())));
        return itemDTO;
    }

    @Override
    public List<String> readYears() {
        return reportDao.readYears();
    }
}
