/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biz.report.dao;

import java.util.List;

/**
 *
 * @author Aux-058
 */
public interface ReportDao {

    List read(String types, String year, String months);

    List readPieChartData(String types, String year, String months);

    public List readType();
    
    public List readItemByType(String type, String year);
}
