package connector;

import java.sql.*;
import java.util.List;
import java.util.Map;

public class SnowflakeDestinationConnector implements DestinationConnector {
    private Connection connection;

    @Override
    public void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:snowflake://xyz.snowflakecomputing.com", "user", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disconnect() {
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeRecords(List<Record> records, Schema schema) {
        for (Record record : records) {
            StringBuilder columns = new StringBuilder();
            StringBuilder values = new StringBuilder();

            for (Map.Entry<String, Object> entry : record.getData().entrySet()) {
                columns.append(entry.getKey()).append(",");
                values.append("'" + entry.getValue() + "',");
            }

            // Remove trailing commas
            columns.setLength(columns.length() - 1);
            values.setLength(values.length() - 1);

            String sql = "INSERT INTO users (" + columns + ") VALUES (" + values + ")";
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
