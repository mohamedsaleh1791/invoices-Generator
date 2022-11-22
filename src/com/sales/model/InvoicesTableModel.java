
package com.sales.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public class InvoicesTableModel extends AbstractTableModel {
    private ArrayList<InvoiceData> invoices;
    private String[] invoiceColumns={"NO","Date","Customer","Total"};

    public InvoicesTableModel(ArrayList<InvoiceData> invoices) {
        this.invoices = invoices;
    }
    

    @Override
    public int getRowCount() {
        return invoices.size();
    }

    @Override
    public int getColumnCount() {
        return invoiceColumns.length;
    }

    @Override
    public String getColumnName(int column) {
        return invoiceColumns[column];
    }
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceData invoice=invoices.get(rowIndex);
        switch(columnIndex){
            case 0: return invoice.getInvoiceNo();
            case 1: return invoice.getInvoiceDate();
            case 2: return invoice.getCustomerName();
            case 3: return invoice.getInvoceTotal();
            default:return "";
        }
    }
    
}
