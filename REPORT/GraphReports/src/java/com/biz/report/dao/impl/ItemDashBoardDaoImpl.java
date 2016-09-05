/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biz.report.dao.impl;

import com.biz.report.dao.ItemDashBoardDao;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import static org.joda.time.Period.months;
import static org.joda.time.format.ISODateTimeFormat.year;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Aux-058
 */
@Repository
public class ItemDashBoardDaoImpl implements ItemDashBoardDao{
    
    @Autowired
    private SessionFactory sessionFactory;

    Log logger = LogFactory.getLog(ReportDaoImpl.class);

    private Session getSession() {
        return sessionFactory.openSession();
    }

    public List readItems() {
        Session session = getSession();
        Query sQLQuery = session.createSQLQuery("");

        return sQLQuery.list();
    }

    public List readDataForAreaChart(String items, String months, String year) {
        Session session = getSession();
        Query sQLQuery = session.createSQLQuery("");

        return sQLQuery.list();
    }
    
}
