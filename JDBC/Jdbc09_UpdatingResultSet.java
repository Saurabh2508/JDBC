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

/**
 *
 * @author Saurabh Rajput
 */
public class Jdbc09_UpdatingResultSet {
    public static void main(String[] args) {
        Connection conn=null;
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver Loaded successfully");
            conn=DriverManager.getConnection("jdbc:oracle:thin:@//LAPTOP-E8UL19VK:1521/xe","myjava","myscholars");
            System.out.println("Connection opened successfully");
            Statement st=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs=st.executeQuery("select bookname,subject,bookprice from allbooks");
            int count=0;
            while(rs.next())
            {
                String subject=rs.getString(2);
                if(subject.equalsIgnoreCase("JEE"))
                {
                    String bname=rs.getString(1);
                    double price=rs.getDouble(3);
                    price=price+(price*0.1);
                    rs.updateDouble(3, price);
                    rs.updateRow();
                    ++count;
                    System.out.println(bname+ " is updated");
                }
            }
            if(count==0)
                    System.out.println("NO books found");
               else
                    System.out.println(count+" books updated");
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("Can't load the driver");
            System.out.println(ex.getMessage());
        }
        catch(SQLException ex)
        {
            System.out.println("Problem in Database");
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
