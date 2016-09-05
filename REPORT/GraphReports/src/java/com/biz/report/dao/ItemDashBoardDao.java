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
public interface ItemDashBoardDao {
    
    List readItems();
    
    List readDataForAreaChart(String items, String months, String year);
}
