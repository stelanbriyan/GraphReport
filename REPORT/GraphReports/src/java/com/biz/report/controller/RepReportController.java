/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biz.report.controller;

import com.biz.report.service.RepReportService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Aux-058
 */
@Controller
public class RepReportController {
    
    @Autowired
    private RepReportService repReportService;
    
    private final Log logger = LogFactory.getLog(RepReportController.class);
}
