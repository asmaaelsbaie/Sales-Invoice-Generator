/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Asmaa Elsbaie
 */
public class LineTableDialog extends JDialog {

    private final JTextField ItemNameDialogTf;
    private final JTextField countDialogTf;
    private final JTextField priceDialogTf;

    private final JLabel ItemNameDialogLb;
    private final JLabel countDialogLb;
    private final JLabel priceDialogLb;
    private final JButton okBtnDailog;
    private final JButton cancelBtnDialog;

    public LineTableDialog(NewJFrame f) {
        ItemNameDialogLb = new JLabel(" Item Name :");
        ItemNameDialogTf = new JTextField(20);
        countDialogLb = new JLabel(" Item Count :");
        countDialogTf = new JTextField(20);
        priceDialogLb = new JLabel(" Item Price");
        priceDialogTf = new JTextField(20);
        okBtnDailog = new JButton("Ok");
        cancelBtnDialog = new JButton("Cancel");
        okBtnDailog.addActionListener(f.getListner());
        cancelBtnDialog.addActionListener(f.getListner());
        okBtnDailog.setActionCommand("okBtnDialogL");
        cancelBtnDialog.setActionCommand("cancelBtnDialogL");

        setTitle("Add New Invoice");
        setLayout(new GridLayout(4, 2));
        add(ItemNameDialogLb);
        add(ItemNameDialogTf);
        add(countDialogLb);
        add(countDialogTf);
        add(priceDialogLb);
        add(priceDialogTf);
        add(okBtnDailog);
        add(cancelBtnDialog);
        pack();
        setLocation(200, 200);

    }

    public JTextField getItemNameDialogTf() {
        return ItemNameDialogTf;
    }

    public JTextField getCountDialogTf() {
        return countDialogTf;
    }

    public JTextField getPriceDialogTf() {
        return priceDialogTf;
    }

}
