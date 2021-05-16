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
public class Jdbc13_DeletingRows {
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
            Scanner kb=new Scanner(System.in);
            int count=0;
            while(rs.next())
            {
                int bid=rs.getInt(1);
                String bname=rs.getString(2);
                System.out.println(bid+"\t"+bname);
                System.out.println("Do you want to delete it (yes/no): ");
                String choice=kb.next();
                if(choice.equalsIgnoreCase("yes"))
                {
                    rs.deleteRow();
                    ++count;
                }
            }
            if(count==0)
            {
                System.out.println("Sorry! No books of JEE found");
            }
            else
            {
                System.out.println(count+" books updated");
            }
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
