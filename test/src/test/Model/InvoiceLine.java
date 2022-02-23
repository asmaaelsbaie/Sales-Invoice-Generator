/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test.Model;

/**
 *
 * @author Asmaa Elsbaie
 */
public class InvoiceLine {
    
    private String product;
    private double price;
    private int count;
    private double lineTotal;
    private InvoiceHeader invoice;

    public InvoiceLine(String product, double price, int count, InvoiceHeader invoice) {
        this.product = product;
        this.price = price;
        this.count = count;
        this.invoice = invoice;
        this.setLineTotal(this.count*this.price);
    }

    public InvoiceHeader getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceHeader invoice) {
        this.invoice = invoice;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getLineTotal() {
        return lineTotal;
    }

    public void setLineTotal(double lineTotal) {
        this.lineTotal = lineTotal;
    }
    
   
    
}
