/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biz.report.controller;

import com.biz.report.dto.ItemDTO;
import com.biz.report.dto.ReportDataSet;
import com.biz.report.service.CustomerService;
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
public class CustomerReportControler {
    
    private Log logger = LogFactory.getLog(CustomerReportControler.class);
    
    @Autowired
    private CustomerService customerService;
    
    @ResponseBody
    @RequestMapping(value = "customerreport/{year}/get", method = RequestMethod.POST, headers = {"Content-type=application/json"})
    public ResponseEntity<ReportDataSet> readFTypes(@PathVariable("year") String year,
            @RequestBody Map data) {
        logger.info(year);
        logger.info(data);
        Assert.notNull(data, "Data is null.");
        Assert.notNull(year, "Year is null.");
        String items = data.get("customers").toString();
        String months = data.get("months").toString();
        ReportDataSet reportDataSet = customerService.getReports(items, months, year);
        HttpHeaders headers = new HttpHeaders();
        headers.add("success", "Success");
        return new ResponseEntity<ReportDataSet>(reportDataSet, headers, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/customers")
    private ResponseEntity<List<String>> readCustomers() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("success", "Success");
        return new ResponseEntity<List<String>>(customerService.readCustomers(), headers, HttpStatus.OK);
    }
    
    @ResponseBody
    @RequestMapping(value = "customers/items/{year}/read", method = RequestMethod.POST, headers = {"Content-type=application/json"})
    public ResponseEntity<List<ItemDTO>> readItemByType(@RequestBody Map map, @PathVariable("year")String year) {
        Assert.notNull(year, "Year is null.");
        Assert.notNull(map, "Type is null.");
        String customers = map.get("customers").toString();
        String month = map.get("month") != null ? map.get("month").toString() : null;
        HttpHeaders headers = new HttpHeaders();
        headers.add("success", "Success");
        return new ResponseEntity<List<ItemDTO>>(customerService.readByCustomerName(customers, year, month), headers, HttpStatus.OK);
    }
}
