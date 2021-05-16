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
public class Jdbc29_DeletingRec {
    public static void main(String[] args) {
        try
        {
        Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver Loaded successfully");
            CachedRowSetImpl obj=new CachedRowSetImpl();
            obj.setUrl("jdbc:oracle:thin:@//LAPTOP-E8UL19VK:1521/xe");
            obj.setUsername("myjava");
            obj.setPassword("myscholars");
            obj.setCommand("select bookname,bookprice from allbooks");
            obj.execute();
            Scanner kb=new Scanner(System.in);
            boolean status=false;
            while(obj.next())
            {
                String bname=obj.getString(1);
                double amt=obj.getDouble(2);
                System.out.println(bname+"\t"+amt);
                String choice;
                System.out.println("Do you want to delete it (yes/no)?");
                choice=kb.next();
                if(choice.equalsIgnoreCase("yes"))
                {
                    obj.deleteRow();
                    status=true;
                }
                if(status)
                {
                    obj.acceptChanges();
                    System.out.println("Record deleted from the DB");
                }
            }
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("Can't load the driver class");
            System.out.println(ex.getMessage());
        }
        catch(SQLException ex)
        {
            System.out.println("Problem in DB");
            System.out.println(ex.getMessage());
        }
    }
}
