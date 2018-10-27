/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stores.management;

import java.sql.Date;

/**
 *
 * @author home
 */
public class data {
    private String ordernumber,objectnumber,astore,pfwaa,sts;
    Date sd,ed,dor,pd,mopp,mocc,dateofpay;
 
    public data(String object,String auctionstore,String orderno,String pfwa,Date startdate,Date enddate,Date dateoforder,Date dop,Date dm,Date dc,String status) {
        objectnumber = object;
        astore = auctionstore;
        ordernumber = orderno;
        pfwaa = pfwa;
        sd = startdate;
        ed = enddate;
        dor = dateoforder;
        dateofpay = dop;
        mopp = dm;
        mocc = dc;
        dateofpay = dop;
    }

    
    public String objectno() {
        return objectnumber;
    }
    public String auctionstore() {
        return astore;
    }
    public String orderno() {
        return ordernumber;
    }
    public String pfwa() {
        return pfwaa;
    }
    public Date startdate() {
        return sd;
    }
    public Date enddate() {
        return ed;
    }
    public Date dateoforder() {
        return dor;
    }
    public Date mdate() {
        return mopp;
    }
    public Date cdate() {
        return mocc;
    }
    public Date dateofpayment(){
        return dateofpay;
    }
}
