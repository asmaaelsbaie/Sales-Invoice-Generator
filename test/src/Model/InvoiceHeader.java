/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Asmaa Elsbaie
 */
public class InvoiceHeader {

    private int num;
    private Date date;
    private String custName;
    private double invTotal;
    private ArrayList<InvoiceLine> lines;

    public InvoiceHeader(int num, Date date, String custName) {
        this.num = num;
        this.date = date;
        this.custName = custName;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getInvTotal() {
        return invTotal;
    }

    public void setInvTotal(double invTotal) {
        this.invTotal = invTotal;
    }

    public ArrayList<InvoiceLine> getLines() {
        if (lines == null) {
            lines = new ArrayList<>(); //lazy loading
        }
        return lines;
    }

    public void setLines(ArrayList<InvoiceLine> lines) {
        this.lines = lines;
    }

    public void addLine(InvoiceLine line) {
        getLines().add(line);
        setInvTotal(getInvTotal() + line.getLineTotal());
    }

    public void removeLine(InvoiceLine line) {
        getLines().remove(line);

    }

}
