/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biz.report.service.impl;

import com.biz.report.dao.ItemDashBoardDao;
import com.biz.report.domain.MappingEngine;
import com.biz.report.domain.Report1;
import com.biz.report.domain.Report2;
import com.biz.report.dto.DataPoint;
import com.biz.report.dto.Report1DataSet;
import com.biz.report.dto.Report2DataSet;
import com.biz.report.dto.Report3DataSet;
import com.biz.report.dto.Report4DataSet;
import com.biz.report.dto.Report5DataSet;
import com.biz.report.dto.ReportDataSet;
import com.biz.report.dto.SalesDTO;
import com.biz.report.service.ItemDashBoardService;
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
public class ItemDashBoardServiceImpl implements ItemDashBoardService {

    @Autowired
    private ItemDashBoardDao itemDashBoardDao;

    private Log logger = LogFactory.getLog(ItemDashBoardServiceImpl.class);

    public List<String> readItems() {
        return itemDashBoardDao.readItems();
    }

    public List<Report1DataSet> readDataForAreaChart(String items, String months, String year) {
        if (!StringUtils.isEmpty(items) && items.contains("[")) {
            items = items.substring(1, items.length() - 1);
        }
        String[] typeAr;
        if (!StringUtils.isEmpty(items) && items.contains(",")) {
            typeAr = items.split("[,]");
        } else {
            typeAr = new String[]{items};
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
        List list = itemDashBoardDao.read(items, months, year);
        List<Report1> reportList = new MappingEngine().getList(list);
        logger.info(reportList.size());
        List<Report1DataSet> dataSets = new ArrayList<Report1DataSet>();
        for (int i = 0; i < typeCount; i++) {
            List<DataPoint> dataPoints = constructDataPoints(reportList, typeAr[i].trim(), monthAr, i);
            dataSets.add(new Report1DataSet("stackedArea", dataPoints, typeAr[i]));
        }
        return dataSets;
    }

    @Override
    public List<Report2DataSet> readDataForPieChart(String items, String year, String months) {
        if (!StringUtils.isEmpty(items) && items.contains("[")) {
            items = items.substring(1, items.length() - 1);
        }
        if (!StringUtils.isEmpty(months) && months.contains("[")) {
            months = months.substring(1, months.length() - 1);
        }
        List list = itemDashBoardDao.readByMonth(items, months, year);
        List<Report2> reportList = new MappingEngine().getPieChartReport(list);
        List<Report2DataSet> dataSets = new ArrayList<Report2DataSet>();
        for (Report2 r : reportList) {
            dataSets.add(new Report2DataSet(r.getTypeName(), r.getAmount()));
        }
        return dataSets;
    }

    public List<Report3DataSet> readDataForBarChart(String items, String year, String months) {
        if (!StringUtils.isEmpty(items) && items.contains("[")) {
            items = items.substring(1, items.length() - 1);
        }
        if (!StringUtils.isEmpty(months) && months.contains("[")) {
            months = months.substring(1, months.length() - 1);
        }
        List list = itemDashBoardDao.readByMonth(items, months, year);
        List<Report2> reportList = new MappingEngine().getPieChartReport(list);
        List<Report3DataSet> dataSets = new ArrayList<Report3DataSet>();
        for (Report2 r : reportList) {
            dataSets.add(new Report3DataSet(r.getTypeName(), r.getAmount()));
        }
        return dataSets;
    }

    public List<Report4DataSet> readDataForColumnChart(String items, String months, String year) {
        if (!StringUtils.isEmpty(items) && items.contains("[")) {
            items = items.substring(1, items.length() - 1);
        }
        String[] typeAr;
        if (!StringUtils.isEmpty(items) && items.contains(",")) {
            typeAr = items.split("[,]");
        } else {
            typeAr = new String[]{items};
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
        List list = itemDashBoardDao.read(items, months, year);
        List<Report1> reportList = new MappingEngine().getList(list);
        logger.info(reportList.size());
        List<Report4DataSet> dataSets = new ArrayList<Report4DataSet>();
        for (int i = 0; i < typeCount; i++) {
            List<DataPoint> dataPoints = constructDataPoints(reportList, typeAr[i].trim(), monthAr, i);
            dataSets.add(new Report4DataSet("column", dataPoints, typeAr[i]));
        }
        return dataSets;
    }

    public List<Report5DataSet> readDataForLineChart(String items, String months, String year) {
        if (!StringUtils.isEmpty(items) && items.contains("[")) {
            items = items.substring(1, items.length() - 1);
        }
        String[] typeAr;
        if (!StringUtils.isEmpty(items) && items.contains(",")) {
            typeAr = items.split("[,]");
        } else {
            typeAr = new String[]{items};
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
        List list = itemDashBoardDao.read(items, months, year);
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
    public List<SalesDTO> readByItemName(String itemName, String year, String month) {
        if (!StringUtils.isEmpty(month) && month.contains("[")) {
            month = month.substring(1, month.length() - 1);
        }
        if (!StringUtils.isEmpty(itemName) && !itemName.contains("'")) {
            itemName = "'" + itemName + "'";
        }
        List list = itemDashBoardDao.readByItemName(itemName, year, month);
        List<SalesDTO> salesDTOs = new ArrayList<SalesDTO>();
        for (Object object : list) {
            SalesDTO salesDTO = constructorSalesDTO(object);
            salesDTOs.add(salesDTO);
        }
        return salesDTOs;
    }

    private SalesDTO constructorSalesDTO(Object object) {
        Object[] ob = (Object[]) object;
        SalesDTO salesDTO = new SalesDTO();
        salesDTO.setRefNo(ob[0].toString());
        salesDTO.setTxtDate(ob[1].toString().substring(0, 10));
        salesDTO.setTypeName(ob[2].toString());
        salesDTO.setQty(ob[3].toString());
        salesDTO.setSellingPrice(ob[4].toString());
        return salesDTO;
    }

    @ResponseBody
    private List<DataPoint> constructDataPoints(List<Report1> reportList, String item, String[] monthAr, int i) {
        List<DataPoint> dataPoints = new ArrayList<DataPoint>();
        List<String> monthList = new ArrayList<String>();
        for (String month : monthAr) {
            monthList.add(month.trim());
        }
        List<Report1> reportList1 = getReportList(item, reportList);

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

    public List<Report1> getReportList(String item, List<Report1> reportList) {
        List<Report1> list = new ArrayList<Report1>();

        for (Report1 report1 : reportList) {
            String typeName = "'" + report1.getTypeName().trim() + "'";
            if (item.equalsIgnoreCase(typeName)) {
                list.add(report1);
            }
        }

        return list;
    }

    @Override
    public ReportDataSet getReports(String items, String months, String year) {
        List<Report1DataSet> report1 = readDataForAreaChart(items, months, year);
        List<Report2DataSet> report2 = readDataForPieChart(items, year, months);
        List<Report3DataSet> report3 = readDataForBarChart(items, year, months);
        List<Report4DataSet> report4 = readDataForColumnChart(items, months, year);
        List<Report5DataSet> report5 = readDataForLineChart(items, months, year);
        return new ReportDataSet(report1, report2, report3, report4, report5);
    }
}
