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
public class Jdbc06_PreparedStatement {
    public static void main(String[] args) {
        Connection conn=null;
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver loaded successfully");
            conn=DriverManager.getConnection("jdbc:oracle:thin:@//LAPTOP-E8UL19VK:1521/xe","myjava","myscholars");
            System.out.println("Connection opened successfully");
            PreparedStatement ps=conn.prepareStatement("Update allbooks set bookprice=bookprice+? where subject=?");
            Scanner sc=new Scanner(System.in);
            System.out.println("Enter Subject");
            String subject=sc.nextLine();
            System.out.println("Enter amount you want to increase");
            double amt=sc.nextDouble();
            ps.setString(2, subject);
            ps.setDouble(1, amt);
            int ans=ps.executeUpdate();
            if(ans!=0)
            {
                System.out.println(ans+" books updated");
            }
            else
            {
                System.out.println("No Books Updated");
            }
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("Sorry Problem in DataBase");
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
                    System.out.println("Connection closed Successfully");
                }
                catch(SQLException ex)
                {
                    System.out.println("Problem in closing the connection");
                }
            }
        }
    }
}
