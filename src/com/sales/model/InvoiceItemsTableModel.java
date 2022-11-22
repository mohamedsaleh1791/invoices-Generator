/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sales.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author lapcom store
 */
public class InvoiceItemsTableModel extends AbstractTableModel {
    private ArrayList<InvoiceItems>invoicesItems;
    private String[] invoiceItemsColumns={"NO","Item Name","Item Price","Item Count","Item Total"};

    public InvoiceItemsTableModel(ArrayList<InvoiceItems> invoicesItems) {
        this.invoicesItems = invoicesItems;
    }
    


    @Override
    public int getRowCount() {
        return invoicesItems.size();
    }

    @Override
    public int getColumnCount() {
        return invoiceItemsColumns.length;
    }

    @Override
    public String getColumnName(int column) {
        return invoiceItemsColumns[column]; 
    }
    
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceItems invoiceItems=invoicesItems.get(rowIndex);
        switch(columnIndex){
            case 0: return invoiceItems.getInvoiceNumber();
            case 1: return invoiceItems.getItem();
            case 2: return invoiceItems.getPrice();
            case 3: return invoiceItems.getItemCount();
            case 4: return invoiceItems.getItemsTotal();
            default: return "";
        }
    }

    public ArrayList<InvoiceItems> getInvoicesItems() {
        return invoicesItems;
    }
    
}
