/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;
import java.sql.*;
import java.util.Scanner;
/**
 *
 * @author Saurabh Rajput
 */
public class Jdbc12_InsertingRecord {
    public static void main(String[] args) {
        Connection conn=null;
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver Loaded successfully");
            conn=DriverManager.getConnection("jdbc:oracle:thin:@//LAPTOP-E8UL19VK:1521/xe","myjava","myscholars");
            System.out.println("Connection opened successfully");
            Statement st=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            //ResultSet rs=st.executeQuery("select * from allbooks"); Wrong!!!!
            //ResultSet rs=st.executeQuery("select bookid,bookname,subject,bookprice from allbooks"); Right
            ResultSet rs=st.executeQuery("select a.* from allbooks a");
            Scanner kb=new Scanner(System.in);
            System.out.println("Enter book id: ");
            int bid=kb.nextInt();
            System.out.println("Enter Bookname: ");
            kb.nextLine();
            String bname=kb.nextLine();
            System.out.println("Enter Subject: ");
            String subject=kb.nextLine();
            System.out.println("Enter the price of the book: ");
            double price=kb.nextDouble();
            rs.moveToInsertRow();
            rs.updateInt(1, bid);
            rs.updateString(2, bname);
            rs.updateString(2, subject);
            rs.updateDouble(4, price);
            rs.insertRow();
            rs.moveToCurrentRow();
            System.out.println("Rec Inserted: ");
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("Problem in loading the driver");
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
                }
                catch(SQLException ex)
                {
                    System.out.println("Problem in closing the connection");
                }
            }
        }
    }
}
