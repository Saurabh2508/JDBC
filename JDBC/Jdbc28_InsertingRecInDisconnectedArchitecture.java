/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Saurabh Rajput
 */
public class Jdbc28_InsertingRecInDisconnectedArchitecture {
    public static void main(String[] args) {
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver Loaded successfully");
            CachedRowSetImpl obj=new CachedRowSetImpl();
            obj.setUrl("jdbc:oracle:thin:@//LAPTOP-E8UL19VK:1521/xe");
            obj.setUsername("myjava");
            obj.setPassword("myscholars");
            obj.setCommand("select * from allbooks");
            obj.execute();
            Scanner kb=new Scanner(System.in);
            System.out.println("Enter book id");
            int bid=kb.nextInt();
            System.out.println("Enter book name");
            kb.nextLine();
            String bname=kb.nextLine();
            System.out.println("Emter subject");
            String subject=kb.nextLine();
            System.out.println("Enter the price of book");
            double price=kb.nextDouble();
            
            obj.moveToInsertRow();
            
            obj.updateInt(1, bid);
            obj.updateString(2, bname);
            obj.updateString(3, subject);
            obj.updateDouble(4, price);
            
            obj.insertRow();
            obj.moveToCurrentRow();
            obj.acceptChanges();
            System.out.println("Saving the info...");
            
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("Problem in loading the driver");
            System.out.println(ex.getMessage());
        }
        catch(SQLException ex)
        {
            System.out.println("Problem in DB");
            System.out.println(ex.getMessage());
        }
    }
}
