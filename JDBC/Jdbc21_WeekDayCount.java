/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
/**
 *
 * @author Saurabh Rajput
 */
public class Jdbc21_WeekDayCount {
    public static void main(String[] args) {
        Connection conn=null;
        try
        {
          Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver successfully loaded!");
            conn=DriverManager.getConnection("jdbc:oracle:thin:@//LAPTOP-E8UL19VK:1521/xe","myjava","myscholars");
            System.out.println("Connection opened successfully");
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery("select hiredate from emp");
            SimpleDateFormat sdf=new SimpleDateFormat("EEEE");
            HashMap <String,Integer>hm=new HashMap<>();
            while(rs.next())
            {
                java.util.Date hd=rs.getDate(1);
                String str=sdf.format(hd);
                if(hm.containsKey(str))
                {
                    int x=hm.get(str);
                    x++;
                    hm.put(str, x);
                }
                else
                {
                    hm.put(str, 1);
                }
            }
            Set <Map.Entry<String,Integer>>data=hm.entrySet();
            Iterator<Map.Entry<String,Integer>>it=data.iterator();
            while(it.hasNext())
                    {
                        Map.Entry<String,Integer>e=it.next();
                        String weekdayname=e.getKey();
                        Integer count=e.getValue();
                        System.out.println(weekdayname+"\t\t"+count);
                        
                    }
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
                catch(SQLException  ex)
                {
                    System.out.println("problem in closing the connection");
                }
            }
        }
    }
}
