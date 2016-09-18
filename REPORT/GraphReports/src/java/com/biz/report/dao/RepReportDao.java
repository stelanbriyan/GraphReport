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
public interface RepReportDao {

    List readReps();

    List read(String reps, String months, String year);

    public List readByMonth(String reps, String months, String year);

    public List readByRepName(String reps, String year, String months);
}
