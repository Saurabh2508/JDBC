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
import java.util.Scanner;
/**
 *
 * @author Saurabh Rajput
 */
public class Jdbc05_PreparedStatement {
    public static void main(String[] args) {
        Connection conn=null;
        try
        {
           Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver Loaded successfully");
            conn=DriverManager.getConnection("jdbc:oracle:thin:@//LAPTOP-E8UL19VK:1521/xe","myjava","myscholars");
            System.out.println("Connection opened successfully");
            PreparedStatement ps=conn.prepareStatement("Insert into allbooks values(?,?,?,?)");
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
            ps.setInt(1,bid);
            ps.setString(2, bname);
            ps.setString(3,sname);
            ps.setDouble(4, price);
            int ans=ps.executeUpdate();
            System.out.println("Rec Inserted: "+ans);
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
                    System.out.println("Connection closed successfully");
                }
                catch(SQLException ex)
                {
                    System.out.println("Problem in closing the Connection");
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
}
