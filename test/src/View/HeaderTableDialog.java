/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Asmaa Elsbaie
 */
public class HeaderTableDialog extends JDialog {

    private final JTextField CustNameDialogTf;
    private final JTextField invDataDialogTf;
    private final JLabel CustNameDialogLb;
    private final JLabel invDataDialogLb;
    private final JButton okBtnDailog;
    private final JButton cancelBtnDialog;

    /**
     *
     * @param f
     */
    public HeaderTableDialog(NewJFrame f) {
        CustNameDialogLb = new JLabel(" Customer Name :");
        CustNameDialogTf = new JTextField(20);
        invDataDialogLb = new JLabel(" Invoice Date :");
        invDataDialogTf = new JTextField(20);
        okBtnDailog = new JButton("Ok");
        cancelBtnDialog = new JButton("Cancel");
        okBtnDailog.addActionListener(f.getListner());
        cancelBtnDialog.addActionListener(f.getListner());
        okBtnDailog.setActionCommand("okBtnDialog");
        cancelBtnDialog.setActionCommand("cancelBtnDialog");

        setTitle("Add New Invoice");
        setLayout(new GridLayout(3, 2));
        add(invDataDialogLb);
        add(invDataDialogTf);
        add(CustNameDialogLb);
        add(CustNameDialogTf);
        add(okBtnDailog);
        add(cancelBtnDialog);
        pack();
        setLocation(200, 200);

    }

   

    public JTextField getCustNameDialogTf() {
        return CustNameDialogTf;
    }

    public JTextField getInvDataDialogTf() {
        return invDataDialogTf;
    }

}
