/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales.model;

import java.util.ArrayList;

/**
 *
 * @author Mohamed.Kotb
 */
public class InvoiceData {
    private int invoiceNo;
    private String invoiceDate;
    private String customerName;
    private ArrayList<InvoiceItems> invoiceItem;
    
    public InvoiceData() {
    }
    
    public InvoiceData(int invoiceNo, String invoiceDate, String customerName) {
        this.invoiceNo = invoiceNo;
        this.invoiceDate = invoiceDate;
        this.customerName = customerName;
    }

    public double getInvoceTotal(){
        double invoiceTotal=0;
        for (InvoiceItems invoiceItem : getInvoiceItem()){
            invoiceTotal+=invoiceItem.getItemsTotal();
        }
        return invoiceTotal;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(int invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public ArrayList<InvoiceItems> getInvoiceItem() {
        if(invoiceItem==null){
            invoiceItem=new ArrayList<>();
        }
        return invoiceItem;
    }
    
    public String decorateInvoiceDataAsCSV(){
        return invoiceNo+","+invoiceDate+","+customerName;
    }
    
}
