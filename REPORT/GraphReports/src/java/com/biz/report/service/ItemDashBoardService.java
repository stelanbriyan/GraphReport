/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biz.report.service;

import com.biz.report.dto.Report1DataSet;
import java.util.List;

/**
 *
 * @author Aux-058
 */
public interface ItemDashBoardService {
    
    List<String> readItems();
    
    List<Report1DataSet> readDataForAreaChart(String items, String months, String year);
}
