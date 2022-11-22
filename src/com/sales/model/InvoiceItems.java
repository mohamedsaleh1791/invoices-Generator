/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales.model;

/**
 *
 * @author Mohamed.Kotb
 */
public class InvoiceItems {
    private int invoiceNumber;
    private String item;
    private double price;
    private int itemCount;
    private InvoiceData invoice;

    public InvoiceItems() {
    }

    public InvoiceItems(int itemNumber, String item, double price, int itemCount) {
        this.invoiceNumber = itemNumber;
        this.item = item;
        this.price = price;
        this.itemCount = itemCount;
    }

    public InvoiceItems(int invoiceNumber, String item, double price, int itemCount, InvoiceData invoice) {
        this(invoiceNumber,item,price,itemCount);
        this.invoice = invoice;
    }
    

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public double getItemsTotal(){
        return itemCount*price;
    }

    public InvoiceData getInvoice() {
        return invoice;
    }
    public String decorateInvoiceItemsAsCSV(){
        return  invoiceNumber+","+item+","+price+","+itemCount;
    }
}
