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
import org.springframework.stereotype.Repository;

/**
 *
 * @author Aux-058
 */
@Repository
public class RepReportDaoImpl implements RepReportDao {

    @Autowired
    private SessionFactory sessionFactory;

    private final Log logger = LogFactory.getLog(RepReportDaoImpl.class);

    private Session getSession() {
        return sessionFactory.openSession();
    }

    public List readReps() {
        Session session = getSession();
        String sql = "SELECT a.RepName FROM fSalRep a";
        Query sQLQuery = session.createSQLQuery(sql);
        return sQLQuery.list();
    }

    public List read(String reps, String months, String year) {
        Session session = getSession();
        Query sQLQuery = session.createSQLQuery("SELECT MONTH(c.TxnDate) AS A, a.RepName , sum(c.SellPrice) "
                + "FROM fSalRep a , fInvhed b , fInvdet c "
                + "WHERE a.RepCode = b.RepCode AND b.RefNo = c.RefNo "
                + "AND YEAR(b.TxnDate) = " + year + " "
                + "AND a.RepName IN (" + reps + ") "
                + "AND DATENAME(MONTH, c.TxnDate) IN (" + months + ") "
                + "GROUP BY MONTH(c.TxnDate)  , a.RepName");
        return sQLQuery.list();
    }
                
    public List read(String reps, String year) {
        Session session = getSession();
        Query sQLQuery = session.createSQLQuery("SELECT a.RepName , sum(c.SellPrice) "
                + "FROM CASSIMS.dbo.fSalRep a , CASSIMS.dbo.fInvhed b , CASSIMS.dbo.fInvdet c "
                + "WHERE a.RepCode = b.RepCode AND b.RefNo = c.RefNo "
                + "AND YEAR(b.TxnDate) = " + year + " "
                + "AND a.RepName IN (" + reps + ") "
                + "GROUP BY a.RepName");
        return sQLQuery.list();
    }
    
    
    public void readByRepName(String reps , String year, String months){
        String sql = "";
    }

}
