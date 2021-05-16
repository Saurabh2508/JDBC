/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 *
 * @author Saurabh Rajput
 */
public class Jdbc19_StringToDateObject {
     public static void main(String[] args) {
        Connection conn=null;
        try
        {
          Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver loaded successfully");
          conn=DriverManager.getConnection("jdbc:oracle:thin:@//LAPTOP-E8UL19VK:1521/xe","myjava","myscholars");
            System.out.println("Connection opened successfully");
            PreparedStatement ps=conn.prepareStatement("insert into emp values(?,?,?,?)");
            Scanner kb=new Scanner(System.in);
            int count=0;
            String choice;
            do{
            System.out.println("Enter empid: ");
            int eid=kb.nextInt();
            System.out.println("Enter emp name:");
            kb.nextLine();
            String ename=kb.nextLine();
            System.out.println("Enter hireDate(dd/MM/yyyy):");
            String hdatestr=kb.nextLine();
            System.out.println("Enter salary:");
            double sal=kb.nextDouble();
            
            ps.setInt(1,eid);
            ps.setString(2,ename);
            
            SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date d1=sdf.parse(hdatestr);
            long ms=d1.getTime();
            java.sql.Date d2=new java.sql.Date(ms);
            
            ps.setDate(3, d2);
            ps.setDouble(4, sal);
            
            ps.executeUpdate();
            ++count;
                System.out.println("Do you want to add more:(yes/no): ");
                choice=kb.next();
            
            }while(choice.equalsIgnoreCase("yes"));
            System.out.println("Rec inserted: "+count);
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("Can't load the driver");
            System.out.println(ex.getMessage());
        }
        catch(SQLException ex)
        {
            System.out.println("Problem in database");
            System.out.println(ex.getMessage());
        }
        catch(ParseException pe)
        {
            System.out.println("Error in date Conversion");
            System.out.println(pe.getMessage());
        }
        finally
        {
            if(conn!=null)
            {
                try
                {
                    conn.close();
                    System.out.println("Connection closed successfully");
                }
                catch(SQLException ex)
                {
                    System.out.println("Problem in closing the connection");
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
}
