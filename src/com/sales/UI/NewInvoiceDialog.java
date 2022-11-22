/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales.UI;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;


/**
 *
 * @author DELL
 */
public class NewInvoiceDialog extends JDialog {
    
    private JTextField customerNameTxtBox;
    private JTextField invoiceDateTxtBox;
    private JLabel customerNameLbl;
    private JLabel invoiceDateLbl;
    private JButton okBtn;
    private JButton cancelBtn;

    public NewInvoiceDialog(MainInvoiceFrame frame) {
        
        customerNameLbl = new JLabel("Customer Name:");
        customerNameTxtBox = new JTextField(20);
        invoiceDateLbl = new JLabel("Invoice Date:");
        invoiceDateTxtBox = new JTextField(20);
        okBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel");
        
        okBtn.setActionCommand("createInvoice");
        cancelBtn.setActionCommand("cancelInvoice");
        
        okBtn.addActionListener(frame.getController());
        cancelBtn.addActionListener(frame.getController());
        setLayout(new GridLayout(3, 3));
        
        add(invoiceDateLbl);
        add(invoiceDateTxtBox);
        add(customerNameLbl);
        add(customerNameTxtBox);
        add(okBtn);
        add(cancelBtn);
        
        pack();
        
    }

    public JTextField getcustomerNameTxtBox() {
        return customerNameTxtBox;
    }

    public JTextField getInvoiceDateTxtBox() {
        return invoiceDateTxtBox;
    }
    
}
