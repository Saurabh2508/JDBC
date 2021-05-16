/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

/**
 *
 * @author Saurabh Rajput
 */
public class Book {
    private String bname;
    private double oldp;
    private double newp;

    public Book(String bname, double oldp, double newp) {
        this.bname = bname;
        this.oldp = oldp;
        this.newp = newp;
    }

    Book(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "book{"+ "bname=" +bname+ ", oldp=" +oldp+ ", newp=" +newp+ "}";
    } 
}
