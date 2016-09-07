/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biz.report.controller;

import com.biz.report.dto.ItemDTO;
import com.biz.report.dto.ReportDataSet;
import com.biz.report.service.ReportService;
import java.util.List;
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
public class ReportController {

    private final Log logger = LogFactory.getLog(ReportController.class);

    @Autowired
    private ReportService reportService;

    public ReportController() {
    }

    @ResponseBody
    @RequestMapping(value = "report/{year}/get", method = RequestMethod.POST, headers = {"Content-type=application/json"})
    public ResponseEntity<ReportDataSet> readFTypes(@PathVariable("year") String year,
            @RequestBody Map data) {
        logger.info(year);
        logger.info(data);
        Assert.notNull(data, "Data is null.");
        Assert.notNull(year, "Year is null.");
        String types = data.get("types").toString();
        String months = data.get("months").toString();
        ReportDataSet reportDataSet = reportService.getReports(types, months, year);
        HttpHeaders headers = new HttpHeaders();
        headers.add("success", "Success");
        return new ResponseEntity<ReportDataSet>(reportDataSet, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/type-name")
    public ResponseEntity<List<String>> selectTag() {
        List<String> list = reportService.getTypes();

        HttpHeaders headers = new HttpHeaders();
        headers.add("success", "Success");
        return new ResponseEntity<List<String>>(list, headers, HttpStatus.OK);
    }
    
    @ResponseBody
    @RequestMapping(value = "items/{year}/read", method = RequestMethod.POST, headers = {"Content-type=application/json"})
    public ResponseEntity<List<ItemDTO>> readItemByType(@RequestBody String type, @PathVariable("year")String year) {
        Assert.notNull(year, "Year is null.");
        Assert.notNull(type, "Type is null.");
        HttpHeaders headers = new HttpHeaders();
        headers.add("success", "Success");
        return new ResponseEntity<List<ItemDTO>>(reportService.readItemByType(type, year), headers, HttpStatus.OK);
    }
}
