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
                + "AND a.DebName IN (( "
                + "				SELECT TOP 10 "
                + "					a.DebName "
                + "				FROM "
                + "					CASSIMS.dbo.fDebtor a , "
                + "					CASSIMS.dbo.fInvhed b , "
                + "					CASSIMS.dbo.fInvdet c  "
                + "				WHERE "
                + "					a.DebCode = b.DebCode  "
                + "					AND b.RefNo = c.RefNo  "
                + "					AND YEAR(b.TxnDate) = 2016  "
                + "					AND a.DebName IN ( "
                + customers
                + "					) "
                + "					AND DATENAME(MONTH, b.TxnDate) IN ( "
                + months
                + "					) "
                + "				GROUP BY a.DebName ORDER BY sum(c.SellPrice) DESC "
                + ")) "
                + "AND DATENAME(MONTH, b.TxnDate) IN (" + months + ") "
                + "GROUP BY MONTH(b.TxnDate), a.DebName";
        Query sQLQuery = session.createSQLQuery(sql);
        return sQLQuery.list();
    }

    public List readByMonth(String customers, String months, String year) {
        Session session = getSession();
        String sql = "SELECT TOP 10  a.DebName , sum(c.SellPrice) "
                + "FROM CASSIMS.dbo.fDebtor a , CASSIMS.dbo.fInvhed b , CASSIMS.dbo.fInvdet c "
                + "WHERE a.DebCode = b.DebCode AND b.RefNo = c.RefNo "
                + "AND YEAR(b.TxnDate) = " + year + " "
                + "AND a.DebName IN (" + customers + ") "
                + "AND DATENAME(MONTH, b.TxnDate) IN (" + months + ") "
                + "GROUP BY a.DebName ORDER BY sum(c.SellPrice) DESC";
        Query sQLQuery = session.createSQLQuery(sql);
        return sQLQuery.list();
    }

    @Override
    public List readTableData(String customers, String year, String months) {
        Session session = getSession();
        String sql = "SELECT a.RefNo , b.TxnDate, d.ItemName , b.Qty , b.SellPrice "
                + "FROM CASSIMS.dbo.fInvhed a, CASSIMS.dbo.fInvdet b, CASSIMS.dbo.fDebtor c, CASSIMS.dbo.fitems d "
                + "WHERE a.RefNo = b.RefNo AND a.DebCode = c.DebCode AND b.Itemcode = d.ItemCode "
                + "AND DATENAME(MONTH, b.TxnDate) IN (" + months + ") "
                + "AND YEAR(b.TxnDate) = " + year + " "
                + "AND c.DebName = " + customers;
        Query sQLQuery = session.createSQLQuery(sql);
        return sQLQuery.list();
    }

}
