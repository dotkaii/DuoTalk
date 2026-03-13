package database;

import java.sql.*;

public class DatabaseManager {

    private static final String DB_URL = "jdbc:sqlite:duotalk.db";

    public static Connection connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new SQLException("SQLite JDBC driver not found on the classpath.", e);
        }

        return DriverManager.getConnection(DB_URL);
    }

    public static void initializeDatabase() {

        String sql = """
                CREATE TABLE IF NOT EXISTS messages (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    sender TEXT,
                    receiver TEXT,
                    message TEXT,
                    timestamp DATETIME DEFAULT CURRENT_TIMESTAMP
                );
                """;

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void saveMessage(String sender, String receiver, String message) {

        String sql = "INSERT INTO messages(sender, receiver, message) VALUES(?,?,?)";

        try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, sender);
            pstmt.setString(2, receiver);
            pstmt.setString(3, message);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static String getChatHistory() {

        StringBuilder history = new StringBuilder();

        String sql = "SELECT sender, receiver, message, timestamp FROM messages";

        try(Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            while(rs.next()){

                String sender = rs.getString("sender");
                String message = rs.getString("message");
                String time = rs.getString("timestamp");

                history.append("[").append(time).append("] ")
                    .append(sender).append(": ")
                    .append(message).append("\n");

            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return history.toString();
    }
}
