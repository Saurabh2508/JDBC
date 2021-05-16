/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Saurabh Rajput
 */
public class Jdbc14_MovingCursor {
    public static void main(String[] args) {
        Connection conn=null;
        try
        {
           Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver Loaded Successfully");
           conn=DriverManager.getConnection("jdbc:oracle:thin:@//LAPTOP-E8UL19VK:1521/xe","myjava","myscholars");
            System.out.println("Connection opened Successfully");
            Statement st=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs=st.executeQuery("select bookid,bookname from allbooks");
            rs.next();
            rs.next();
            int b=rs.getRow();
            //rs.relative(3);
            //rs.absolute(3);
            rs.last();
            
            rs.relative(-2);
            
            int a=rs.getRow();
            
            boolean x=rs.previous();
            
            String bname=rs.getString(2);
            System.out.println("BookName is "+bname);
            System.out.println(a+"\t"+b);
            System.out.println( x);
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("Problem in loading the driver");
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
                }
            }
        }
              
    }
}
