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
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Saurabh Rajput
 */
public class Jdbc11_UpdatingResultSetShowingOldAndNewPrice {
    public static void main(String[] args) {
        Connection conn=null;
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver Loaded successfully");
            conn=DriverManager.getConnection("jdbc:oracle:thin:@//LAPTOP-E8UL19VK:1521/xe","myjava","myscholars");
            System.out.println("Connection opened successfully");
            Statement st=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs=st.executeQuery("select subject,bookprice,bookname from allbooks");
            ArrayList<Book>booklist=new ArrayList<>();
            while(rs.next())
            {
                String subject=rs.getString(1);
                if(subject.equalsIgnoreCase("JEE"))
                {
                    double oldamt=rs.getDouble(2);
                    double newamt=oldamt+oldamt*0.1;
                    rs.updateDouble(2,newamt);
                    rs.updateRow();
                    Book obj=new Book(rs.getString(3),oldamt,newamt);
                    booklist.add(obj);
                    
                }
            }
            if(booklist.size()==0)
                System.out.println("NO book found");
            else
            {
                System.out.println(booklist.size()+" books updated");
                System.out.println("The Books details are: ");
                Iterator<Book> it=booklist.iterator();
                while(it.hasNext())
                {
                    Book b=it.next();
                    System.out.println(b);
                }
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
                   System.out.println("Problem in closing connection");
                   System.out.println(ex.getMessage());
               }
           }
        }
    }
}
