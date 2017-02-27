package main.com.sun.h5weixin.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by SUN on 2017/2/16.
 */
public class ConnectionJdbc {
    public static Connection connectionJdbc()
    {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://116.62.37.49:3306/test?useUnicode=true&characterEncodin=utf8";

        Connection connection = null;
        try
        {
            Class.forName(driver);
            try
            {
                connection = DriverManager.getConnection(url, "root", "sun123");
            }catch (SQLException e)
            {
                e.printStackTrace();
            }
        }catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return connection;
    }


}
