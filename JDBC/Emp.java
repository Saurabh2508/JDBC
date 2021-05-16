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
public class Emp {
    public Emp()
    {
        System.out.println("constructor is called..");
    }
    static
    {
        System.out.println("static block is called.");
    }
}
class Test {
    public static void main(String[] args) {
        try
        {
            Class.forName("Emp");
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("Sorry class not found..");
        }
    }
}