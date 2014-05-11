package org.jboss.newcastle.data;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@Named
@SessionScoped
public class SchemaInfo implements Serializable {

    DataSource ds;
    
    private DataSource getDatasource() throws NamingException {
        if (ds == null) {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:jboss/datasources/PostgreSQLDS");
        }
        return ds;
    }
    
    /** Get the list of tables from the database */
    public List<String> getTables() {
        List<String> tables = new ArrayList<String>();
        try {
            DataSource ds = getDatasource();
            Connection con = ds.getConnection();
            DatabaseMetaData dbmd = con.getMetaData();
            String [] dbObjectTypes = {"TABLE"};
            ResultSet rs = dbmd.getTables("public", null, null, dbObjectTypes);
            while (rs.next()) {
                tables.add(rs.getString("TABLE_NAME"));
            }
        } catch (SQLException e) {
            tables.add("Unable to query to database: " + e);
        } catch (NamingException e) {
            tables.add("Unable to lookup datasource: " + e);
        }
        return tables;
    }
    
    public String getTest() {
        return "Testing";
    }
    public String getSelectedTable() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = 
            fc.getExternalContext().getRequestParameterMap();
     
        return params.get("tableName");
    }

    public List<String[]> getColumns() {
        List<String[]> columns = new ArrayList<String[]>();
        String selectedTable = getSelectedTable();
        if (selectedTable == null)
        {
            return columns;
        }
        try {
            DataSource ds = getDatasource();
            Connection con = ds.getConnection();
            DatabaseMetaData dbmd = con.getMetaData();

            ResultSet rs = dbmd.getColumns("public", null, selectedTable, null);
            while (rs.next()) {
                String [] columnData = new String[2];
                columnData[0] = rs.getString("COLUMN_NAME");
                columnData[1] = rs.getString("TYPE_NAME");
                columns.add(columnData);
            }
        } catch (SQLException e) {
            String [] errorData = {"Unable to query to database: " + e};
            columns.add(errorData);
        } catch (NamingException e) {
            String [] errorData = {"Unable to lookup datasource: " + e};
            columns.add(errorData);
        }
        return columns;
    }
    
}
