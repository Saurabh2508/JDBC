/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
/**
 *
 * @author Saurabh Rajput
 */
public class Jdbc22_RetrivingImage {
    public static void main(String[] args) {
    Connection conn=null;
    try
    {
        Class.forName("oracle.jdbc.OracleDriver");
        System.out.println("Driver loaded successfully");
        conn=DriverManager.getConnection("jdbc:oracle:thin:@//LAPTOP-E8UL19VK:1521/xe","myjava","myscholars");
        System.out.println("Connection opened successfully");
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select * from movies");
        File mydir=new File("d:/onlineadvjava/mydbpics");
        if(!mydir.mkdirs())
        {
           FolderNotCreatedException  obj=new FolderNotCreatedException("Sorry can't create the folder app will terminated");
           throw obj;
        }
        while(rs.next())
        {
            String mname=rs.getString(1);
            Blob obj=rs.getBlob(2);
            byte[] arr=obj.getBytes(1, (int)obj.length());
            FileOutputStream fout=new FileOutputStream("d:/onlineadvjava/mydbpics/"+mname+".jpg");
            fout.write(arr);
            fout.close();
            System.out.println("Saving "+mname+ ".jpg...");
        }
    }
    catch(FolderNotCreatedException ex)
    {
        System.out.println(ex.getMessage());
    }
    catch(ClassNotFoundException ex)
    {
        System.out.println("Can't find the driver");
        System.out.println(ex.getMessage());
    }
    catch(FileNotFoundException fnf)
    {
        System.out.println("Can,t found the file");
        System.out.println(fnf.getMessage());
    }
    catch(IOException ex)
    {
        System.out.println(ex.getMessage());
    }
    catch(SQLException ex)
    {
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
                System.out.println("Problem in closing the connection ");
                System.out.println(ex.getMessage());
            }
        }
    }
}
}

