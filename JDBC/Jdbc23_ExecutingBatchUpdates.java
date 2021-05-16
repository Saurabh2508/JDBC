/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;
import java.sql.*;
/**
 *
 * @author Saurabh Rajput
 */
public class Jdbc23_ExecutingBatchUpdates {
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
            st.addBatch("insert into allbooks values(211,'Oracle Cert','Oracle',700)");
            st.addBatch("delete from emp where ename='Amit'");
            st.addBatch("update emp set sal=sal+1000 where empno>=108");
            int []results=st.executeBatch();
            for(int i=0;i<results.length;i++)
            {
                if(results[i]==Statement.EXECUTE_FAILED)
                    status=false;
                System.out.println("Query "+(i+1)+" effected "+results[i]+"rows");
            }
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("Problem in loading the driver");
            System.out.println(ex.getMessage());
        }
        catch(BatchUpdateException ex)
        {
            int []arr=ex.getUpdateCounts();
            int count=arr.length+1;
            System.out.println("Query "+count+" gave the exception");
            System.out.println(ex.getMessage());
            status=false;
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
