/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Saurabh Rajput
 */
public class Jdbc04_StringConcatenation {
     public static void main(String[] args) {
        Connection conn=null;
        try{
           Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver loaded successfully");
            conn=DriverManager.getConnection("jdbc:oracle:thin:@//LAPTOP-E8UL19VK:1521/xe","myjava","myscholars");
            System.out.println("Connection opened successfully");
            Statement st=conn.createStatement();
            Scanner kb=new Scanner(System.in);
            System.out.println("Enter Book ID:");
            int bid=kb.nextInt();
            System.out.println("Enter book name: ");
            kb.nextLine();
            String bname=kb.nextLine();
            System.out.println("Enter Subject Name: ");
            String sname=kb.nextLine();
            System.out.println("Enter the Price:");
            double price=kb.nextDouble();
            String str="Insert into allbooks values("+bid+",'"+bname+"','"+sname+"',"+price+")";//String Concatenation
            int ans=st.executeUpdate(str);
            System.out.println("Record Inserted "+ans);
        }
        catch(ClassNotFoundException cnf)
        {
            System.out.println("Sorry can't load the driver!");
            System.out.println(cnf.getMessage());
        }
        catch(SQLException ex)
        {
            System.out.println("Sorry problem with Database!");
            System.out.println(ex.getMessage());
        }
        finally{
            if(conn!=null)
            {
                try
                {
                conn.close();
                System.out.println("Connection closed Successfully");
                }
                catch(SQLException ex)
                {
                    System.out.println("Sorry problem in closing the connection");
                    System.out.println(ex.getMessage());
                }
            }
        }
     }
}
