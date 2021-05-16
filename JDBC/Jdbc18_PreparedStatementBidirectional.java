/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Saurabh Rajput
 */
public class Jdbc18_PreparedStatementBidirectional {
    public static void main(String[] args) {
        Connection conn=null;
        try
        {
          Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver loaded successfully");
          conn=DriverManager.getConnection("jdbc:oracle:thin:@//LAPTOP-E8UL19VK:1521/xe","myjava","myscholars");
            System.out.println("Connection opened successfully");
            PreparedStatement ps=conn.prepareStatement("select bookname,bookprice from allbooks",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
//            Scanner kb=new Scanner(System.in);
//            System.out.println("Enter subject name: ");
//            String s=kb.nextLine();
//            ps.setString(1, s);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                String bname=rs.getString(1);
                double amt=rs.getDouble(2);
                System.out.println(bname+"\t"+amt);
            }
            System.out.println("Forward traversal finished. Now traversing backward....");
            while(rs.previous())
            {
                String bname=rs.getString(1);
                double amt=rs.getDouble(2);
                System.out.println(bname+"\t"+amt);
            }
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
