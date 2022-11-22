/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sales.controller;


import com.sales.UI.MainInvoiceFrame;
import com.sales.UI.NewInvoiceDialog;
import com.sales.UI.NewItemDialog;
import com.sales.model.InvoiceData;
import com.sales.model.InvoiceItems;
import com.sales.model.InvoiceItemsTableModel;
import com.sales.model.InvoicesTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author lapcom store
 */
public class Controller implements ActionListener,ListSelectionListener {
    private MainInvoiceFrame frame;
    private NewInvoiceDialog newInvoiceFrame;
    private NewItemDialog newItemFrame;
    public Controller(MainInvoiceFrame frame){
        this.frame=frame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String action= e.getActionCommand();
        switch(action){
            case "Load FIle":
                loadFile();
                break;
            case "Save File":
                saveFile();
                break;
            case "Create New Invoice":
                createNewInvoice();
                break;
            case "Delete Invoice":
                deleteInvoice();
                break;
            case "Add Item":
                addItem();
                break;
            case "Delete Item":
                deleteItem();
                break;
            case "createInvoice":
                createInvoice();
                break;
            case "cancelInvoice":
                cancelInvoice();
                break;
            case "createItem":
                createItem();
                break;
            case "cancelItem":
                cancelItem();
                break;    
        }
    }
//    For Invoice items display
    @Override
    public void valueChanged(ListSelectionEvent e) {
        int currentIndex=frame.getInvoicesTable().getSelectedRow();
//        System.out.println("You have selected "+frame.getInvoicesTable().getSelectedRow() );
        if(currentIndex!=-1){
            InvoiceData selectedInvoice=frame.getInvoices().get(currentIndex);
            frame.getInvoiceNolbl().setText(""+selectedInvoice.getInvoiceNo());
            frame.getCustomerNamelbl().setText(selectedInvoice.getCustomerName());
            frame.getInvoiceDatelbl().setText(selectedInvoice.getInvoiceDate());
            frame.getInvoiceTotallbl().setText(""+selectedInvoice.getInvoceTotal());
            InvoiceItemsTableModel invoiceItemsTableModel=new InvoiceItemsTableModel(selectedInvoice.getInvoiceItem());
            frame.getInvoiceItemsTable().setModel(invoiceItemsTableModel);
            invoiceItemsTableModel.fireTableDataChanged();
        }
    }
    


    private void loadFile() {
        JFileChooser chooseFile=new JFileChooser();
//        Get data of Invoices
        int checkClickInvoices= chooseFile.showOpenDialog(frame);
        try {
        if(checkClickInvoices==JFileChooser.APPROVE_OPTION){
            File invoicesFile=chooseFile.getSelectedFile();
            Path invoicesPath=Paths.get(invoicesFile.getAbsolutePath());
            List<String> invoicesData=Files.readAllLines(invoicesPath);
            ArrayList<InvoiceData> storeInvoices=new ArrayList<>();
            for(String invoice : invoicesData){
                String[] invoiceParts=invoice.split(",");
                int invoiceNumber=Integer.parseInt(invoiceParts[0]);
                String invoiceDate=invoiceParts[1];
                String customerName=invoiceParts[2];
                InvoiceData invoiceData=new InvoiceData(invoiceNumber, invoiceDate, customerName);
                storeInvoices.add(invoiceData);
            }
              JOptionPane.showMessageDialog(frame, "Invoices loaded please select items file","Info",JOptionPane.INFORMATION_MESSAGE);

//            Get data of invoices Items
            int checkCliclikInvoiceItem=chooseFile.showOpenDialog(frame);
            if(checkCliclikInvoiceItem==JFileChooser.APPROVE_OPTION){
                File invoicesItemFile=chooseFile.getSelectedFile();
                Path invoicesItemPath=Paths.get(invoicesItemFile.getAbsolutePath());
                List<String> invoicesItemData=Files.readAllLines(invoicesItemPath);
                for(String invoicesItems : invoicesItemData){
                    String[] invoicesItemsParts=invoicesItems.split(",");
                    int invoiceNumber=Integer.parseInt(invoicesItemsParts[0]);
                    String itemName=invoicesItemsParts[1];
                    double itemPrice=Double.parseDouble(invoicesItemsParts[2]);
                    int itemCount=Integer.parseInt(invoicesItemsParts[3]);
                    InvoiceData relatedInvoice=null;
                    for (InvoiceData invoice :storeInvoices ){
                        if(invoice.getInvoiceNo()==invoiceNumber){
                            relatedInvoice=invoice;
                            break;
                        }
                    }
                    InvoiceItems invoiceItemsData=new InvoiceItems(invoiceNumber, itemName, itemPrice, itemCount, relatedInvoice);
                    relatedInvoice.getInvoiceItem().add(invoiceItemsData);
                }
                    JOptionPane.showMessageDialog(frame, "Items loaded ","Info",JOptionPane.INFORMATION_MESSAGE);

            }
            frame.setInvoices(storeInvoices);
            InvoicesTableModel invoicesTableModel=new InvoicesTableModel(storeInvoices);
            frame.setInvoicesTableModel(invoicesTableModel);
            frame.getInvoicesTable().setModel(invoicesTableModel);
            frame.getInvoicesTableModel().fireTableDataChanged();
        }
        }catch(IOException e){
             JOptionPane.showMessageDialog(frame, "Unable to read File","Error",JOptionPane.ERROR_MESSAGE);

                }
        }
    

    private void saveFile()   {
        ArrayList<InvoiceData> invoices=frame.getInvoices();
        String invoiceData=null;
        String invoiceItems=null;
        FileWriter invoicesFileWriter=null;
        FileWriter invoicesItemsFileWriter=null;
        
        
        try{
        for(InvoiceData invoice : invoices){
            String invoiceDataCSV=invoice.decorateInvoiceDataAsCSV();
            invoiceData+=invoiceDataCSV;
            invoiceData+="\n";
            for (InvoiceItems items : invoice.getInvoiceItem()){
                String invoiceItemsCSV=items.decorateInvoiceItemsAsCSV();
                invoiceItems+=invoiceItemsCSV;
                invoiceItems+="\n";
            }
        }
        JFileChooser saveDialog=new JFileChooser();
        int result=saveDialog.showSaveDialog(frame);
        if(result== JFileChooser.APPROVE_OPTION){
            File invoicesFile=saveDialog.getSelectedFile();
            
               invoicesFileWriter=new FileWriter(invoicesFile);
                invoicesFileWriter.write(invoiceData);
                invoicesFileWriter.flush();
                invoicesFileWriter.close();
                JOptionPane.showMessageDialog(frame,"Invoices Save Sucssfully");
            
            
            result=saveDialog.showSaveDialog(frame);
            if(result== JFileChooser.APPROVE_OPTION){
                File invoicesItemsFile=saveDialog.getSelectedFile();
                invoicesItemsFileWriter=new FileWriter(invoicesItemsFile);
                invoicesItemsFileWriter.write(invoiceItems);
                invoicesItemsFileWriter.flush();
                invoicesItemsFileWriter.close();
                JOptionPane.showMessageDialog(frame,"Invoices Items Save Sucssfully");
                
            }
        }
        }catch(IOException e){
            e.printStackTrace();
        }
      
        
        
        
        
         }

    private void createNewInvoice() {
        newInvoiceFrame=new NewInvoiceDialog(frame);
        newInvoiceFrame.setVisible(true);
   }

    private void deleteInvoice() {
        int selectedRow=frame.getInvoicesTable().getSelectedRow();
        if(selectedRow!=-1){
            frame.getInvoices().remove(selectedRow);
            frame.getInvoicesTableModel().fireTableDataChanged();
        }
   }

    private void addItem() {
        newItemFrame=new NewItemDialog(frame);
        newItemFrame.setVisible(true);
   }

    private void deleteItem() {
        int selectedItem=frame.getInvoiceItemsTable().getSelectedRow();
        int selectedInvoice=frame.getInvoicesTable().getSelectedRow();
        if(selectedInvoice!=-1&& selectedItem!=-1){
            InvoiceData invoiceData=frame.getInvoices().get(selectedInvoice);
            invoiceData.getInvoiceItem().remove(selectedItem);
            InvoiceItemsTableModel invoiceItemTableModel=new InvoiceItemsTableModel(invoiceData.getInvoiceItem());
            frame.getInvoiceItemsTable().setModel(invoiceItemTableModel);
            invoiceItemTableModel.fireTableDataChanged();
            frame.getInvoicesTableModel().fireTableDataChanged();

      
        }
    }

    private void createInvoice() {
        DateFormat checkDate=new SimpleDateFormat("dd-MM-yyyy");
        String invoiceDate=newInvoiceFrame.getInvoiceDateTxtBox().getText();
        String customerName=newInvoiceFrame.getcustomerNameTxtBox().getText();
        int invoiceNumber=frame.getNextInvoiceNo();
        try{
            checkDate.setLenient(false);
            checkDate.parse(invoiceDate);
            InvoiceData invoice=new InvoiceData(invoiceNumber, invoiceDate, customerName);
            frame.getInvoices().add(invoice);
            frame.getInvoicesTableModel().fireTableDataChanged();
            newInvoiceFrame.setVisible(false);
            newInvoiceFrame.dispose();
            newInvoiceFrame=null;
        }catch(ParseException e){
            JOptionPane.showMessageDialog(frame, "Wrong Date Format","Error",JOptionPane.ERROR_MESSAGE);
        }
  }

    private void cancelInvoice() {
        newInvoiceFrame.setVisible(false);
        newInvoiceFrame.dispose();
        newInvoiceFrame=null;
  }

    private void createItem() {
        try{
        String itemName=newItemFrame.getItemNameTxtBox().getText();
        int itemCOunt=Integer.parseInt(newItemFrame.getItemCountTxtBox().getText());
        double itemPrice=Double.parseDouble(newItemFrame.getItemPriceTxtBox().getText());
        InvoiceData invoice= frame.getInvoices().get(frame.getInvoicesTable().getSelectedRow());
        InvoiceItems invoiceItem=new InvoiceItems(invoice.getInvoiceNo(), itemName, itemPrice, itemCOunt);
        invoice.getInvoiceItem().add(invoiceItem);
        InvoiceItemsTableModel invoiceItemTableModel=(InvoiceItemsTableModel) frame.getInvoiceItemsTable().getModel();
        frame.getInvoicesTableModel().fireTableDataChanged();
//        invoiceItemTableModel.fireTableDataChanged();
        newItemFrame.setVisible(false);
        newItemFrame.dispose();
        newItemFrame=null;
        }catch(NumberFormatException e){
         JOptionPane.showMessageDialog(frame, "Wrong Number Format","Error",JOptionPane.ERROR_MESSAGE);
        }
        
        
   }

    private void cancelItem() {
        newItemFrame.setVisible(false);
        newInvoiceFrame.dispose();
        newItemFrame=null;
   }
    
    
}