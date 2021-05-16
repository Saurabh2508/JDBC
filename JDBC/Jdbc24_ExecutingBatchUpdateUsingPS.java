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
public class Jdbc24_ExecutingBatchUpdateUsingPS {
    public static void main(String[] args) {
        Connection conn=null;
        boolean status=true;
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver loaded successfully");
            conn=DriverManager.getConnection("jdbc:oracle:thin:@//LAPTOP-E8UL19VK:1521/xe","myjava","myscholars");
            System.out.println("Connection opened successfully");
            PreparedStatement ps=conn.prepareStatement("insert into allbooks values(?,?,?,?)");
            conn.setAutoCommit(false);
            Scanner kb=new Scanner(System.in);
            String choice;
            do
            {
                System.out.println("Enter the bookid: ");
                int id=kb.nextInt();
                System.out.println("Enter the name of book: ");
                kb.nextLine();
                String bname=kb.nextLine();
                System.out.println("Enter subject: ");
                String subject=kb.nextLine();
                System.out.println("Enter the price of the book: ");
                double price=kb.nextDouble();
                
                ps.setInt(1, id);
                ps.setString(2, subject);
                ps.setString(3, subject);
                ps.setDouble(4, price);
                
                ps.addBatch();
                
                System.out.println("Do you want to add more:(yes/no)");
                choice=kb.next();
            }while(choice.equalsIgnoreCase("yes"));
            int []results=ps.executeBatch();
            for(int i=0;i<results.length;i++)
            {
                if(results[i]==PreparedStatement.EXECUTE_FAILED)
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
