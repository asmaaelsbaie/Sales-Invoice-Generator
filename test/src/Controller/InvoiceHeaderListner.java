/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change frame license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit frame template
 */
package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Proxy;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import Model.HeaderTableModel;
import Model.InvoiceHeader;
import Model.InvoiceLine;
import Model.LineTableModel;
import View.HeaderTableDialog;
import View.LineTableDialog;
import View.NewJFrame;

/**
 *
 * @author Asmaa Elsbaie
 */
public class InvoiceHeaderListner implements ActionListener, ListSelectionListener{

 
    private final NewJFrame frame;
    private final SimpleDateFormat df = new SimpleDateFormat("dd-mm-yyyy");
    

    public InvoiceHeaderListner(NewJFrame frame ) {
        this.frame=frame;
    }
 
    
        public void createInv() {
        frame.setHeaderDialog ( new HeaderTableDialog(frame));
        frame.getHeaderDialog().setVisible(true);

    }

     public void LoadFiles() throws Exception {
        frame.getInvoices().clear();

        JOptionPane.showMessageDialog(frame, "please select invoice header file",
                "Invoice Header", JOptionPane.WARNING_MESSAGE);
        JFileChooser fc = new JFileChooser();
        if (fc.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {

            frame.setSelectFile(  fc.getSelectedFile());
            FileReader fr = new FileReader(frame.getSelectFile());
            BufferedReader br = new BufferedReader(fr);

            String line = null;
            while ((line = br.readLine()) != null) {

                String[] headerSegments = line.split(",");
                String invNumStr = headerSegments[0];
                String invDateStr = headerSegments[1];
                String custname = headerSegments[2];
                int invNum = Integer.parseInt(invNumStr);
                Date invDate = df.parse(invDateStr);
                InvoiceHeader header = new InvoiceHeader(invNum, invDate, custname);
                frame.getInvoices().add(header);

            }
            br.close();
            fr.close();
            System.out.println("check");
            JOptionPane.showMessageDialog(frame,
                    "please select invoice Lines file",
                    "Invoice Lines", JOptionPane.WARNING_MESSAGE);

            if (fc.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {

                frame.setSelectFile(  fc.getSelectedFile());
                fr = new FileReader(frame.getSelectFile());
                br = new BufferedReader(fr);

                line = null;
                while ((line = br.readLine()) != null) {

                    String[] lineSegments = line.split(",");
                    String invNumStr = lineSegments[0];
                    String item = lineSegments[1];
                    String priceStr = lineSegments[2];
                    String countStr = lineSegments[3];
                    int invNum = Integer.parseInt(invNumStr);
                    double price = Double.parseDouble(priceStr);
                    int count = Integer.parseInt(countStr);

                    InvoiceHeader header = findByNum(invNum);
                    InvoiceLine invLine = new InvoiceLine(item, price, count, header);
                    header.addLine(invLine);

                }
                br.close();
                fr.close();
                frame.setHeaderModel ( new HeaderTableModel(frame.getInvoices()));
                frame.getHeaderTable().setModel(frame.getHeaderModel());
                frame.getHeaderTable().validate();
            }

        }
    }

     public InvoiceHeader findByNum(int num) {
        for (InvoiceHeader header : frame.getInvoices()) {
            if (header.getNum() == num) {
                return header;
            }
        }
        return null;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        HeaderTableRowSelected();
    }

     public void HeaderTableRowSelected() {

        int selectedRowIndex = frame.getHeaderTable().getSelectedRow();
        if (selectedRowIndex >= 0) {
            InvoiceHeader row = frame.getHeaderModel().getInvoices().get(selectedRowIndex);
            frame.getCustNameTF().setText(row.getCustName());
            frame.getInvDateTF().setText(row.getDate().toString());
            frame.getInvNumLb().setText("" + row.getNum());
            frame.getTotalLb().setText("" + row.getInvTotal());
            ArrayList<InvoiceLine> lines = row.getLines();
            frame.setLineModel ( new LineTableModel(lines));
            frame.getLineTable().setModel(frame.getLineModel());
            frame.getLineModel().fireTableDataChanged();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        switch (e.getActionCommand()) {
            case "okBtnDialog" -> createInvOKDialog();
            case "cancelBtnDialog" -> createInvCancelDialog();
            case "okBtnDialogL" -> createInvOKDialogLine();
            case "cancelBtnDialogL" -> createInvCancelDialogLine();
          
            default -> {
            }
        }
    }

     public void createInvOKDialog() {
        String custName = frame.getHeaderDialog().getCustNameDialogTf().getText();
        String invDateStr = frame.getHeaderDialog().getInvDataDialogTf().getText();
        Date invDate = new Date();
        try {
            invDate = df.parse(invDateStr);
        } catch (ParseException ex) {
        }
       frame.getHeaderDialog().setVisible(false);
        int num = getMaxInvNum() + 1;
        frame.setNewInvHader( new InvoiceHeader(num, invDate, custName));
        frame.getInvoices().add(frame.getNewInvHader());
        frame.getHeaderModel().fireTableDataChanged();

        System.out.println("check");
    }

     public void createInvCancelDialog() {
        frame.getHeaderDialog().setVisible(false);
    }

     public int getMaxInvNum() {
        int num = 0;
        for (InvoiceHeader header : frame.getInvoices()) {
            if (header.getNum() > num) {
                num = header.getNum();
            }

        }
        return num;
    }

     public void createInvOKDialogLine() {
        String itemName = frame.getLineDialog().getItemNameDialogTf().getText();
        String itemCountStr = frame.getLineDialog().getCountDialogTf().getText();
        String itemPriceStr = frame.getLineDialog().getPriceDialogTf().getText();
        frame.getLineDialog().setVisible(false);

        int itmeCount = Integer.parseInt(itemCountStr);
        double itemPrice = Double.parseDouble(itemPriceStr);
        frame.setInvHeader1 ( frame.getInvoices().get(frame.getHeaderTable().getSelectedRow()));
        InvoiceLine line1 = new InvoiceLine(itemName, itemPrice, itmeCount, frame.getInvHeader1());
        frame.getInvHeader1().addLine(line1);
        frame.getLineModel().fireTableDataChanged();
        frame.getHeaderModel().fireTableDataChanged();

    }

     public void createInvCancelDialogLine() {
        frame.getLineDialog().setVisible(false);
    }

     public void createInvItem() {
        frame.setLineDialog( new LineTableDialog(frame));
        frame.getLineDialog().setVisible(true);
    }

     public void deleteInv() {
        frame.getInvoices().remove(frame.getNewInvHader());
        frame.getInvHeader1().removeLine(frame.getLine1());
        frame.getHeaderModel().fireTableDataChanged();
        frame.getLineModel().fireTableDataChanged();

    }

     public void cancelChange() {
        System.exit(0);
        System.out.println("close");

    }

     public void saveChange() {
        saveHeaderChange();
        saveLineChange();
    }

     public void saveHeaderChange() {
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("File Save");
        if (fc.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
            File savedFile = fc.getSelectedFile();

            try {
                FileWriter fw = new FileWriter(savedFile);
                BufferedWriter bw = new BufferedWriter(fw);
                for (int i = 0; i < frame.getHeaderTable().getRowCount(); i++) {
                    for (int j = 0; j < frame.getHeaderTable().getColumnCount(); j++) {
                        bw.write(frame.getHeaderTable().getValueAt(i, j).toString() + ",");
                    }
                    bw.newLine();
                }
                JOptionPane.showMessageDialog(frame, "Save Header Table Done",
                        "Save Info", JOptionPane.INFORMATION_MESSAGE);
                bw.close();
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(frame, "Error",
                        "Error Info", JOptionPane.ERROR_MESSAGE);
            }

        }

    }

     public void saveLineChange() {
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("File Save");
        if (fc.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
            File savedFile = fc.getSelectedFile();

            try {
                FileWriter fw = new FileWriter(savedFile );
                BufferedWriter bw = new BufferedWriter(fw);
                for (InvoiceHeader inv : frame.getInvoices()) {
                    for (InvoiceLine item : inv.getLines()) {
                        bw.write(inv.getNum() + "," + item.getProduct() + "," + item.getPrice() + "," + item.getCount());

                    }
                    bw.newLine();
                }

                JOptionPane.showMessageDialog(frame, "Save Line Table Done", "Save Info", JOptionPane.INFORMATION_MESSAGE);
                bw.close();
                fw.close();

            } catch (IOException ex) {
                Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(frame, "Error",
                        "Error Info", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

     public void saveFile() {

        saveChange();
    }

}
