package ua.com.poseal.shopping.mall.connection;

import java.sql.Connection;
import java.util.Properties;

public interface ConnectionUtils {
    Connection getConnection(Properties properties);
}
