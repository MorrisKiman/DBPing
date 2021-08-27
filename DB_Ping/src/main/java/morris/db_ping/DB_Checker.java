/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morris.db_ping;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 *
 * @author Kimani
 */
public class DB_Checker {
    public String dump_dir;
    public String mashida;
    
    //getters

    public String getDump_dir() {
        return dump_dir;
    }

    public String getMashida() {
        return mashida;
    }
    

    public void setDump_dir(String dump_dir) {
        this.dump_dir = dump_dir;
    }

    public void setMashida(String mashida) {
        this.mashida = mashida;
    }
     
    
    public int db_check (String databaseName, String dbHost, String username, String password, String portNumber){
        int db_Status = 0;
        String db_dumpdir = "null"; 
        try{  
            //step1 load the driver class  
            Class.forName("oracle.jdbc.driver.OracleDriver");  

            //step2 create  the connection object  
            Connection con=DriverManager.getConnection( 
            //jdbc:oracle:thin:@machinename:1521:databasename
                    //C:\Program Files\Java\jdk1.8.0_211\jre\lib\ext
            "jdbc:oracle:thin:@"+dbHost+":"+portNumber+"/"+databaseName,username,password);  

            //step3 create the statement object  
            Statement stmt=con.createStatement();  

            //step4 execute query  
            ResultSet rs=stmt.executeQuery("select DIRECTORY_PATH from dba_directories where directory_name = 'DUMP_DIR'");  
            while(rs.next()) 
                db_dumpdir = rs.getString(1);
                System.out.println(db_dumpdir);
                setDump_dir(db_dumpdir);
                

            //step5 close the connection object  
            con.close();
            db_Status = 1;

                }catch(Exception e){ 
                    System.out.println(e);
                    setMashida(e.toString());
            }//end try
        
        
        return db_Status;
    }//end of db_checker
    
}
