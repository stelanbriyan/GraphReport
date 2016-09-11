/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biz.report.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 *
 * @author Aux-058
 */
@Controller
public class CommonURLController {
    
    @RequestMapping(value = "item-report", method = RequestMethod.GET)
    public String loadItemReport() {
        return "item_report";
    }
    
    @RequestMapping(value = "rep-report", method = RequestMethod.GET)
    public String loadRepReport() {
        return "rep_report";
    }
}
