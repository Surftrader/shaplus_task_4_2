package ua.com.poseal.shopping.mall.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ua.com.poseal.shopping.mall.connection.PostgresConnectionUtils.DRIVER;
import static ua.com.poseal.shopping.mall.connection.PostgresConnectionUtils.USERNAME;

class LoaderTest {

    Loader loader;

    @BeforeEach
    void init() {
        loader = new Loader();
    }

    @Test
    void getExternalFileProperties() {
        Properties properties = loader.getFileProperties();

        assertNotNull(properties);
        assertEquals("org.postgresql.Driver", properties.getProperty(DRIVER));
        assertEquals("postgres", properties.getProperty(USERNAME));
    }

    @Test
    void getInternalFileProperties() {
        Properties properties = new Properties();
        loader.downloadInternalProperties(properties);

        assertNotNull(properties);
        assertEquals("org.postgresql.Driver", properties.getProperty(DRIVER));
        assertEquals("postgres", properties.getProperty(USERNAME));
    }
}