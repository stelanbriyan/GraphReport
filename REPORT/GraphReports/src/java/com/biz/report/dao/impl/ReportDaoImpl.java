/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biz.report.dao.impl;

import com.biz.report.dao.ReportDao;
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
 * @author Stelan Briyan
 */
@Repository
public class ReportDaoImpl implements ReportDao {

    @Autowired
    private SessionFactory sessionFactory;

    Log logger = LogFactory.getLog(ReportDaoImpl.class);

    private Session getSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List read(String types, String year, String months) {
        Session session = getSession();
        Query sQLQuery = session.createSQLQuery(
                "SELECT MONTH(c.TxnDate) AS A , b.typeName , sum(c.sellPrice) "
                + "FROM FItems a , FType b , FInvdet c "
                + "WHERE a.typeCode = b.typeCode AND c.itemCode = a.itemCode "
                + "AND YEAR(c.txnDate) = " + year
                + " AND b.TypeName IN (" + types + ") "
                + "AND DATENAME(MONTH, c.txnDate) IN (" + months + ") "
                + "GROUP BY b.typeName  ,  MONTH(c.TxnDate) "
        );

        return sQLQuery.list();
    }

    public List readPieChartData(String types, String year, String months) {
        Session session = getSession();
        Query sQLQuery = session.createSQLQuery("SELECT  b.typeName , sum(c.sellPrice) "
                + "FROM fitems a ,  FType b ,  FInvdet c "
                + "WHERE a.typeCode = b.typeCode AND c.itemCode = a.itemCode "
                + "AND YEAR(c.txnDate) = " + year
                + "AND b.TypeName IN (" + types + ") "
                + "AND DATENAME(MONTH, c.txnDate) IN (" + months + ") "
                + "GROUP BY b.typeName ");

        return sQLQuery.list();
    }

    public List readItemByType(String type, String year, String month) {
        Session session = getSession();
//        if (month == null) {
//            Query query = session.createSQLQuery("SELECT c.RefNo, c.txnDate , a.ItemName , c.Qty , c.sellPrice "
//                    + "FROM fitems a , FType b , FInvdet c  "
//                    + "WHERE a.typeCode = b.typeCode AND c.itemCode = a.itemCode "
//                    + "AND YEAR(c.txnDate) =  " + year + " "
//                    + "AND DATENAME(MONTH, c.txnDate) IN (" + month + ") "
//                    + "AND b.TypeName = '" + type + "'");
//            return query.list();
//        } else {
//            Query query = session.createSQLQuery("SELECT c.RefNo, c.txnDate , a.ItemName , c.Qty , c.sellPrice "
//                    + "FROM fitems a , FType b , FInvdet c  "
//                    + "WHERE a.typeCode = b.typeCode AND c.itemCode = a.itemCode "
//                    + "AND YEAR(c.txnDate) =  " + year + " "
//                    + "AND DATENAME(MONTH, c.txnDate) = " + month + " "
//                    + "AND b.TypeName = " + type);
//            return query.list();
//        }
            Query query = session.createSQLQuery("SELECT c.RefNo, c.txnDate , a.ItemName , c.Qty , c.sellPrice "
                    + "FROM fitems a , FType b , FInvdet c  "
                    + "WHERE a.typeCode = b.typeCode AND c.itemCode = a.itemCode "
                    + "AND YEAR(c.txnDate) =  " + year + " "
                    + "AND DATENAME(MONTH, c.txnDate) IN (" + month + ") "
                    + "AND b.TypeName = " + type);
            return query.list();
    }
 
    public List readType() {
        Session session = getSession();
        Query sQLQuery = session.createSQLQuery("SELECT a.typeName FROM FType a GROUP BY  a.typeName");
        return sQLQuery.list();
    }
    
    public List readYears() {
        Session session = getSession();
        Query sQLQuery = session.createSQLQuery("SELECT YEAR(c.txnDate) "
                + "FROM FInvdet c "
                + "GROUP BY YEAR(c.TxnDate)");
        return sQLQuery.list();
    }
}
