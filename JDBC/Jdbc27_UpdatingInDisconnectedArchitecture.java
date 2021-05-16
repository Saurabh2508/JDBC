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
public class Jdbc27_UpdatingInDisconnectedArchitecture {
    public static void main(String[] args) {
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver Loaded successfully");
            CachedRowSetImpl obj=new CachedRowSetImpl();
            obj.setUrl("jdbc:oracle:thin:@//LAPTOP-E8UL19VK:1521/xe");
            obj.setUsername("myjava");
            obj.setPassword("myscholars");
            obj.setCommand("select subject,bookprice from allbooks");
            obj.execute();
            boolean found=false;
            while(obj.next())
            {
                String subject=obj.getString(1);
                if(subject.equalsIgnoreCase("oracle"))
                {
                    double amt=obj.getDouble(2);
                    amt=amt+amt*0.1;
                    obj.updateDouble(2, amt);
                    obj.updateRow();
                    found=true;
                }
            }
            if(found)
            {
                obj.acceptChanges();
                System.out.println("Record inserted in DB");
            }
            else
            {
                System.out.println("No record found and updated");
            }
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("Problem in laoding the driver");
            System.out.println(ex.getMessage());
        }
        catch(SQLException ex)
        {
            System.out.println("Problem in DB");
            System.out.println(ex.getMessage());
        }
    }
}
