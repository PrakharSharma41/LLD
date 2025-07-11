package connector;

import java.sql.*;
import java.time.Instant;
import java.util.*;

public class MySQLSourceConnector implements SourceConnector {
    private Connection connection;

    @Override
    public void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "user", "password");
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
    public List<Record> fetchRecords(SyncContext context) {
        List<Record> records = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(
                "SELECT * FROM users WHERE updated_at > ?"
            );
            stmt.setTimestamp(1, Timestamp.from(context.getLastSyncedAt()));

            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(meta.getColumnName(i), rs.getObject(i));
                }
                Instant ts = rs.getTimestamp("updated_at").toInstant();
                records.add(new Record(row, ts));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    @Override
    public Schema getSchema() {
        Map<String, String> fields = new HashMap<>();
        try {
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet columns = meta.getColumns(null, null, "users", null);
            while (columns.next()) {
                fields.put(columns.getString("COLUMN_NAME"), columns.getString("TYPE_NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Schema(fields);
    }
}