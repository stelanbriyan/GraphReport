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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Aux-058
 */
@Repository
public class ItemDashBoardDaoImpl implements ItemDashBoardDao {

    @Autowired
    private SessionFactory sessionFactory;

    Log logger = LogFactory.getLog(ReportDaoImpl.class);

    private Session getSession() {
        return sessionFactory.openSession();
    }

    public List readItems() {
        Session session = getSession();
        Query sQLQuery = session.createSQLQuery("SELECT a.ItemCode, a.ItemName FROM CASSIMS.dbo.fitems a "
                + "GROUP BY a.ItemName, a.ItemCode");

        return sQLQuery.list();
    }

    public List read(String items, String months, String year) {
        Session session = getSession();
        Query sQLQuery = session.createSQLQuery("SELECT MONTH(b.TxnDate) AS A  , a.ItemName , sum(b.SellPrice), a.ItemCode "
                + "FROM CASSIMS.dbo.fItems a , CASSIMS.dbo.fInvdet b "
                + "WHERE a.ItemCode = b.Itemcode "
                + "AND YEAR(b.TxnDate) = " + year + " "
                + "AND a.ItemCode IN (" + items + ") "
                + "AND DATENAME(MONTH, b.TxnDate) IN (" + months + ") "
                + "GROUP BY a.ItemName , MONTH(b.TxnDate), a.ItemCode ");
        return sQLQuery.list();
    }

    public List readByMonth(String items, String months, String year) {
        Session session = getSession();
        Query sQLQuery = session.createSQLQuery("SELECT a.ItemName , sum(b.SellPrice) "
                + "FROM CASSIMS.dbo.fItems a , CASSIMS.dbo.fInvdet b "
                + "WHERE a.ItemCode = b.Itemcode "
                + "AND YEAR(b.TxnDate) = " + year + " "
                + "AND a.ItemCode IN (" + items + ") "
                + "AND DATENAME(MONTH, b.TxnDate) IN (" + months + ") "
                + "GROUP BY a.ItemName ");
        return sQLQuery.list();
    }

    public List readByItemName(String itemName, String year, String month) {
        Session session = getSession();
        String sql = "SELECT c.RefNo, c.txnDate , b.TypeName , c.Qty , c.sellPrice "
                + "FROM fitems a , FType b , FInvdet c  "
                + "WHERE a.typeCode = b.typeCode AND c.itemCode = a.itemCode "
                + "AND YEAR(c.txnDate) =  " + year + " "
                + "AND DATENAME(MONTH, c.txnDate) IN (" + month + ") "
                + "AND a.ItemName = " + itemName;
        Query query = session.createSQLQuery(sql);
        return query.list();
    }

}
