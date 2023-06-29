package ua.com.poseal.shopping.mall.util;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.ibatis.jdbc.ScriptRunner;
import ua.com.poseal.shopping.mall.connection.ConnectionUtils;
import ua.com.poseal.shopping.mall.connection.PostgresConnectionUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.util.Properties;

import static ua.com.poseal.App.logger;

public class SQLExecutor {
    private final Properties properties;
    private final ConnectionUtils connectionUtils;

    public SQLExecutor(Properties properties) {
        this.properties = properties;
        this.connectionUtils = new PostgresConnectionUtils();
    }

    public void execute(String file) {
        logger.debug("Entered execute() method with parameter {}", file);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Connection connection = connectionUtils.getConnection(properties);
        ScriptRunner scriptRunner = new ScriptRunner(connection);
        try (Reader reader = new BufferedReader(new FileReader(file))) {
            scriptRunner.runScript(reader);
            connection.close();
        } catch (Exception e) {
            logger.error("Error running sql script");
        }

        stopWatch.stop();
        logger.info("File {} was executed in {} ms", file, stopWatch.getTime());
        logger.debug("Exited execute() method");
    }
}
