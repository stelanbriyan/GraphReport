/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biz.report.controller;

import com.biz.report.service.RepReportService;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Aux-058
 */
@Controller(value = "v1/web")
public class RepReportController {
    
    @Autowired
    private RepReportService repReportService;
    
    private final Log logger = LogFactory.getLog(RepReportController.class);
    
    @RequestMapping(value = "/reps")
    private ResponseEntity<List<String>> readReps() {
        List<String> list = repReportService.readReps();

        HttpHeaders headers = new HttpHeaders();
        headers.add("success", "Success");
        return new ResponseEntity<List<String>>(list, headers, HttpStatus.OK);
    }
}
