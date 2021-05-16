/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Saurabh Rajput
 */
public class Jdbc25_Transaction {
    public static void main(String[] args) {
        Connection conn=null;
        boolean status=true;
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver loaded successfully");
            conn=DriverManager.getConnection("jdbc:oracle:thin:@//LAPTOP-E8UL19VK:1521/xe","myjava","myscholars");
            System.out.println("Connection opened successfully");
            Statement st=conn.createStatement();
            conn.setAutoCommit(false);
            
            String SQL="insert into allbooks values(116,'Maths','Math',300)";
            st.executeUpdate(SQL);
            
            SQL="insert into allbooks values(115,'EVS','EVS',200)";
            st.executeUpdate(SQL);
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("Problem in loading the driver");
            System.out.println(ex.getMessage());
        }
        catch(SQLException ex)
        {
            System.out.println("Sorry! problem in DB");
            System.out.println(ex.getMessage());
            status=false;
        }
        finally
        {
            if(conn!=null)
            {
                try
                {
                    if(status)
                    {
                        System.out.println("Everything executed successfully. Committing changes...");
                        conn.commit();
                    }
                    else
                    {
                        System.out.println("Something went wrong Rollbacking changes..");
                        conn.rollback();
                    }
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
