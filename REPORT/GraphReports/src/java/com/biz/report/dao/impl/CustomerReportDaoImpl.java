/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biz.report.dao.impl;

import com.biz.report.dao.CustomerReportDao;
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
public class CustomerReportDaoImpl implements CustomerReportDao {

    @Autowired
    private SessionFactory sessionFactory;

    private final Log logger = LogFactory.getLog(RepReportDaoImpl.class);

    private Session getSession() {
        return sessionFactory.openSession();
    }

    public List readCustomers() {
        Session session = getSession();
        String sql = "SELECT  a.DebName FROM CASSIMS.dbo.fDebtor a  ORDER BY 1 ASC";
        Query sQLQuery = session.createSQLQuery(sql);
        return sQLQuery.list();
    }

    public List read(String customers, String months, String year) {
        Session session = getSession();
        String sql = "SELECT MONTH(b.TxnDate) AS A, a.DebName , sum(c.SellPrice) "
                + "FROM CASSIMS.dbo.fDebtor a , CASSIMS.dbo.fInvhed b , CASSIMS.dbo.fInvdet c "
                + "WHERE a.DebCode = b.DebCode AND b.RefNo = c.RefNo "
                + "AND YEAR(b.TxnDate) = " + year + " "
                + "AND a.DebName IN (" + customers + ") "
                + "AND DATENAME(MONTH, b.TxnDate) IN (" + months + ") "
                + "GROUP BY MONTH(b.TxnDate), a.DebName ";
        Query sQLQuery = session.createSQLQuery(sql);
        return sQLQuery.list();
    }

    public List read(String customers, String year) {
        Session session = getSession();
        String sql = "SELECT  a.DebName , sum(c.SellPrice) "
                + "FROM CASSIMS.dbo.fDebtor a , CASSIMS.dbo.fInvhed b , CASSIMS.dbo.fInvdet c "
                + "WHERE a.DebCode = b.DebCode AND b.RefNo = c.RefNo "
                + "AND YEAR(b.TxnDate) = " + year + " "
                + "AND a.DebName IN (" + customers + ") "
                + "GROUP BY a.DebName";
        Query sQLQuery = session.createSQLQuery(sql);
        return sQLQuery.list();
    }

}
