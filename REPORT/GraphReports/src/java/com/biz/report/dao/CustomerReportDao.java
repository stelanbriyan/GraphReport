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
public interface CustomerReportDao {

    List readCustomers();

    List read(String customers, String months, String year);

//    List read(String customers, String year);

    List readTableData(String customers, String year, String months);

    public List readByMonth(String customers, String months, String year);
}
