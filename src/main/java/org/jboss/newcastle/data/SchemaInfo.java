package org.jboss.newcastle.data;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SchemaInfo {

    /** Get the list of tables from the database */
    public static List<String> getTables() {
        List<String> result = new ArrayList<String>();
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:jboss/datasources/PostgreSQLDS");
            Connection con = ds.getConnection();
            DatabaseMetaData dbmd = con.getMetaData();
            String [] dbObjectTypes = {"TABLE"};
            ResultSet rs = dbmd.getTables("public", null, null, dbObjectTypes);
            while (rs.next()) {
                result.add(rs.getString("TABLE_NAME"));
            }
        } catch (SQLException e)
        {
            result.add("Unable to connect to database: " + e);
        } catch (NamingException e) {
            result.add("Unable to lookup datasource: " + e);
        }
        return result;
    }
}
