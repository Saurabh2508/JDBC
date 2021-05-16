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
public class Jdbc01_ExecuteQuery {
    public static void main(String[] args) {
        Connection conn=null;
        try{
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver loaded Successfully loaded");
            conn=DriverManager.getConnection("jdbc:oracle:thin:@//LAPTOP-E8UL19VK:1521/xe","myjava","myscholars");
            System.out.println("Connection opened successfully ");
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery("select bookname,bookprice from allbooks");
            while(rs.next())
            {
                String bname=rs.getString(1);
                double amt=rs.getDouble(2);
                System.out.println(bname+"\t"+amt);
            }
        }
        catch(ClassNotFoundException cnf)
        {
            System.out.println("Sorry! cannot load the driver");
            System.out.println(cnf.getMessage());
        }
        catch(SQLException ex)
        {
            System.out.println("Sorry! problem with DB");
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
                    System.out.println("Sorry! problem in closing the conn");
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
}
