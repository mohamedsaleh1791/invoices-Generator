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
public class NewItemDialog extends JDialog{
    private JTextField itemNameTxtBox;
    private JTextField itemCountTxtBox;
    private JTextField itemPriceTxtBox;
    private JLabel itemNameLbl;
    private JLabel itemCountLbl;
    private JLabel itemPriceLbl;
    private JButton okBtn;
    private JButton cancelBtn;
    
    public NewItemDialog(MainInvoiceFrame frame) {
        itemNameTxtBox = new JTextField(20);
        itemNameLbl = new JLabel("Item Name");
        
        itemCountTxtBox = new JTextField(20);
        itemCountLbl = new JLabel("Item Count");
        
        itemPriceTxtBox = new JTextField(20);
        itemPriceLbl = new JLabel("Item Price");
        
        okBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel");
        
        okBtn.setActionCommand("createItem");
        cancelBtn.setActionCommand("cancelItem");
        
        okBtn.addActionListener(frame.getController());
        cancelBtn.addActionListener(frame.getController());
        setLayout(new GridLayout(4, 2));
        
        add(itemNameLbl);
        add(itemNameTxtBox);
        add(itemCountLbl);
        add(itemCountTxtBox);
        add(itemPriceLbl);
        add(itemPriceTxtBox);
        add(okBtn);
        add(cancelBtn);
        
        pack();
    }

  

    public JTextField getItemNameTxtBox() {
        return itemNameTxtBox;
    }

    public JTextField getItemCountTxtBox() {
        return itemCountTxtBox;
    }

    public JTextField getItemPriceTxtBox() {
        return itemPriceTxtBox;
    }
}
