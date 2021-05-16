/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.SQLException;

/**
 *
 * @author Saurabh Rajput
 */
public class Jdbc26_DisconnectedArchitecture {
    public static void main(String[] args) {
//        CachedRowSetImpl obj=null;
        try
        {
        Class.forName("oracle.jdbc.OracleDriver");
        System.out.println("Driver Loaded successfully ");
        CachedRowSetImpl obj=new com.sun.rowset.CachedRowSetImpl();
        obj.setUrl("jdbc:oracle:thin:@//LAPTOP-E8UL19VK:1521/xe");
        obj.setUsername("myjava");
        obj.setPassword("myscholars");
        obj.setCommand("select bookname,bookprice from allbooks");
        obj.execute();
        while(obj.next())
        {
            String bname=obj.getString(1);
            double amt=obj.getDouble(2);
            System.out.println(bname+"\t"+amt);
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
//    finally
//    {
//        if(obj!=null)
//        {
//            try
//            {
//                obj.close();
//                System.out.println("Connection closed successfully");
//            }
//            catch(SQLException ex)
//            {
//                System.out.println("Problem in closing the connection ");
//                System.out.println(ex.getMessage());
//            }
//        }
//    }This is not mendatory because in disconnected achitecture connection closed automatically.
   }      
}

