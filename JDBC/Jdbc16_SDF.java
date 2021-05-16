/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

/**
 *
 * @author Saurabh Rajput
 */
public class Jdbc16_SDF {
    public static void main(String[] args) {
        Connection conn=null;
        try
        {
          Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver loaded successfully");
          conn=DriverManager.getConnection("jdbc:oracle:thin:@//LAPTOP-E8UL19VK:1521/xe","myjava","myscholars");
            System.out.println("Connection opened successfully");
            Statement st=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs=st.executeQuery("select ename,hiredate from emp");
            while(rs.next())
            {
                String bname=rs.getString(1);
                Date hd=rs.getDate(2);
                SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
                String str=sdf.format(hd);
                System.out.println(bname+"\t"+str);
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
