package ua.com.poseal.shopping.mall.util;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.ibatis.jdbc.ScriptRunner;
import ua.com.poseal.shopping.mall.connection.ConnectionUtils;
import ua.com.poseal.shopping.mall.connection.PostgresConnectionUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static ua.com.poseal.shopping.mall.connection.PostgresConnectionUtils.logger;

public class SQLExecutor {
    private final Properties properties;

    public SQLExecutor(Properties properties) {
        this.properties = properties;
    }

    public void execute(String file) {
        logger.debug("Entered execute() method with parameter {}", file);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        ConnectionUtils utils = new PostgresConnectionUtils();
        Connection connection = utils.getConnection(properties);
        ScriptRunner scriptRunner = new ScriptRunner(connection);
        try (Reader reader = new BufferedReader(new FileReader(file))) {
            scriptRunner.runScript(reader);
        } catch (IOException e) {
            logger.error("Error running sql script");
        }

        stopWatch.stop();
        logger.info("File {} was executed in {} sec", file, stopWatch.getTime(TimeUnit.SECONDS));
        logger.debug("Exited execute() method");
    }
}
