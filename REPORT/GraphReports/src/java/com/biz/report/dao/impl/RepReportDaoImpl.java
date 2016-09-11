/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biz.report.dao.impl;

import com.biz.report.dao.RepReportDao;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Aux-058
 */
public class RepReportDaoImpl implements RepReportDao {
    
    @Autowired
    private SessionFactory sessionFactory;

    Log logger = LogFactory.getLog(RepReportDaoImpl.class);

    private Session getSession() {
        return sessionFactory.openSession();
    }

    public List readReps() {
        Session session = getSession();
        Query sQLQuery = session.createSQLQuery("");
        return sQLQuery.list();
    }

    public List read(String reps, String months, String year) {
        Session session = getSession();
        Query sQLQuery = session.createSQLQuery("");
        return sQLQuery.list();
    }

    public List read(String reps, String year) {
        Session session = getSession();
        Query sQLQuery = session.createSQLQuery("");
        return sQLQuery.list();
    }
    
}
