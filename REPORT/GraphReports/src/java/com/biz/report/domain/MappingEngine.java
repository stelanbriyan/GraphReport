/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biz.report.domain;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Stelan Briyan
 */
public class MappingEngine {

    private final List<Report1> report;
    private final List<Report2> report2;
    Log logger = LogFactory.getLog(MappingEngine.class);

    public MappingEngine() {
        this.report = new ArrayList<Report1>();
        this.report2 = new ArrayList<Report2>();
    }

    public List<Report1> getList(List list) {
        if (list != null) {
            for (Object o : list) {
                Object[] obj = (Object[]) o;
                Report1 r = new Report1();
                if (obj[0] != null && !obj[0].equals("")) {
                    r.setMonthName(getMonthName(obj[0].toString().trim()));
                }
                if (obj[1] != null) {
                    r.setTypeName(obj[1].toString().trim());
                }
                if (obj[2] != null) {
                    r.setSellingPrice(Double.parseDouble(obj[2].toString().trim()));
                }
                if (obj[3] != null) {
                    r.setItemCode(obj[3].toString().trim());
                }
                logger.info(obj[1]);
                logger.info(obj[3]);
                report.add(r);
            }
        }
        return report;
    }

    public List<Report2> getPieChartReport(List list) {
        for (Object o : list) {
            Object[] obj = (Object[]) o;
            Report2 r = new Report2();
            if (obj[0] != null) {
                r.setTypeName(obj[0].toString().trim());
            }
            if (obj[1] != null) {
                logger.info(obj[0]);
                r.setAmount(Double.parseDouble(obj[1].toString().trim()));
            }
            report2.add(r);
        }
        return report2;
    }

    private String getMonthName(String date) {
//        String[] split = date.split("[-]");
//        if (split.length > 0) {
        int m = Integer.parseInt(date);
        switch (m) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
        }
//        }
        return "";
    }
}
