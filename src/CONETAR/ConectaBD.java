package CONETAR;

import java.sql.*;
import javax.swing.JOptionPane;

public class ConectaBD {

    public static Connection conectabd() throws ClassNotFoundException{
        
        try{
            Class.forName ("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Suplementos", "postgres", "jota");
            return con;
        }
    
        catch(SQLException error){
            
            JOptionPane.showMessageDialog(null, error);
            
            return null;
        }
 
    }
    
}
    

