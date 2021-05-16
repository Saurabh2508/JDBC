/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
/**
 *
 * @author Saurabh Rajput
 */
public class Jdbc20_InsertingImage {
    public static void main(String[] args) {
        Connection conn=null;
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver loaded successfully");
            conn=DriverManager.getConnection("jdbc:oracle:thin:@//LAPTOP-E8UL19VK:1521/xe","myjava","myscholars");
            System.out.println("Connection opened successfully");
            PreparedStatement ps=conn.prepareStatement("insert into movies values(?,?)");
            Scanner kb=new Scanner(System.in);
            System.out.println("Enter path to the image:");
            String str=kb.nextLine();
            File f=new File(str);
            FileInputStream fin=new FileInputStream(str);
            String fname=f.getName();
            int dotpos=fname.lastIndexOf(".");
            String movname=fname.substring(0,dotpos);
            
            ps.setString(1, movname);
            ps.setBinaryStream(2, fin, (int)f.length());
            
            int res=ps.executeUpdate();
            System.out.println("Rec inserted: "+res);
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("Can't found the file in your mentioned location");
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("Cat't load the driver");
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
                    System.out.println("Driver closed successfully");
                }
                catch(SQLException ex)
                {
                    System.out.println("Problem in closing the connection ");
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
}
