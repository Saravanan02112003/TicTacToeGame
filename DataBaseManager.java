package db;


import java.sql.*;

public class DataBaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/tictactoe";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void updateLeaderboard(String playerName, int moves, long duration) {
        try (Connection conn = connect()) {
            String query = "INSERT INTO leaderboard (name, wins, moves, time_taken) VALUES (?, 1, ?, ?) " +
                           "ON DUPLICATE KEY UPDATE wins = wins + 1, moves = moves + ?, time_taken = time_taken + ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, playerName);
            stmt.setInt(2, moves);
            stmt.setLong(3, duration);
            stmt.setInt(4, moves);
            stmt.setLong(5, duration);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}

