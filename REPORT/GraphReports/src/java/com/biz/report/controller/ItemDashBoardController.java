/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biz.report.controller;

import com.biz.report.dto.ReportDataSet;
import com.biz.report.service.ItemDashBoardService;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Aux-058
 */
@Controller
@RequestMapping(value = "v1/web/")
public class ItemDashBoardController {
    
    private Log logger = LogFactory.getLog(ItemDashBoardController.class);
    
    @Autowired
    private ItemDashBoardService itemDashBoardService;
    
    @ResponseBody
    @RequestMapping(value = "itemreport/{year}/get", method = RequestMethod.POST, headers = {"Content-type=application/json"})
    public ResponseEntity<ReportDataSet> readFTypes(@PathVariable("year") String year,
            @RequestBody Map data) {
        logger.info(year);
        logger.info(data);
        Assert.notNull(data, "Data is null.");
        Assert.notNull(year, "Year is null.");
        String items = data.get("items").toString();
        String months = data.get("months").toString();
        ReportDataSet reportDataSet = itemDashBoardService.getReports(items, months, year);
        HttpHeaders headers = new HttpHeaders();
        headers.add("success", "Success");
        return new ResponseEntity<ReportDataSet>(reportDataSet, headers, HttpStatus.OK);
    }
}
