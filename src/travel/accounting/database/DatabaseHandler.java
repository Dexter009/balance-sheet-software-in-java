/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travel.accounting.database;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public final class DatabaseHandler {

    private static DatabaseHandler handler = null;

    private static final String DB_URL = "jdbc:derby:database;create=true";
    private static Connection conn = null;
    private static Statement stmt = null;

    private DatabaseHandler() {
        createConnection();
        addBalance();
        //shutdown();
    }

    public static DatabaseHandler getInstance() {
        if (handler == null) {
            handler = new DatabaseHandler();
        }
        return handler;
    }

    void createConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            conn = DriverManager.getConnection(DB_URL);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cant load database", "Database Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    void addBalance() {
        String TABLE_NAME1 = "BALANCE";
        String TABLE_NAME2 = "BALANCE1";
        try {
            stmt = conn.createStatement();

            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME1.toUpperCase(), null);

            if (tables.next()) {
                System.out.println("Table " + TABLE_NAME1 + "already exists. Ready for go!");
            } else {
                stmt.execute("CREATE TABLE " + TABLE_NAME1 + "("
                        + "	transid int NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1,INCREMENT BY 1) ,\n"
                        + "	pname varchar(200),\n"
                        + "	ppay varchar(200),\n"
                        + "	ba varchar(100),\n"
                        + "	br varchar(100),\n"
                        + "	pr varchar(100),\n"
                        + "	rem varchar(255),\n"
                        + "	pnh varchar(100),\n"
                        + "	source varchar(100),\n"
                        + "	destiny varchar(100),\n"
                        + "	di varchar(100),\n"
                        + "	dp varchar(100),\n"
                        + "	bag varchar(100),\n"
                        + "	refu varchar(100)"
                + " )");
            }
            ResultSet tables1 = dbm.getTables(null, null, TABLE_NAME2.toUpperCase(), null);

            if (tables1.next()) {
                System.out.println("Table " + TABLE_NAME2 + "already exists. Ready for go!");
            } else {
                stmt.execute("CREATE TABLE " + TABLE_NAME2 + "("
                        + "	transidd varchar(200),\n"
                        + "	pnamee varchar(200),\n"
                        + "	ppayy varchar(200),\n"
                        + "	baa varchar(100),\n"
                        + "	brr varchar(100),\n"
                        + "	prr varchar(100),\n"
                        + "	remm varchar(255),\n"
                        + "	pnhh varchar(100),\n"
                        + "	sourcee varchar(100),\n"
                        + "	destinyy varchar(100),\n"
                        + "	dii varchar(100),\n"
                        + "	dpp varchar(100),\n"
                        + "	bagg varchar(100),\n"
                        + "	refuu varchar(100)"
                + " )");
            }
            
        } catch (SQLException e) {
            System.err.println(e.getMessage() + " --- setupDatabase");
        } finally {       
        }
    }
public ResultSet execQuery(String query) {
        ResultSet result;
        try {
            stmt = conn.createStatement();
            result = stmt.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return null;
        } finally {
        }
        return result;
    }
   

    public boolean execAction(String qu) {
        try {
            stmt = conn.createStatement();
            stmt.execute(qu);
            return true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error:" + ex.getMessage(), "Error Occured", JOptionPane.ERROR_MESSAGE);
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return false;
        } finally {
        }
    }
 public static void shutdown()
    {
        try
        {
            if (stmt != null)
            {
                stmt.close();
            }
            if (conn != null)
            {
                DriverManager.getConnection("jdbc:derby:database" + ";shutdown=true");
                conn.close();
            }           
        }
        catch (SQLException sqlExcept)
        {
            
        }

    }


}


