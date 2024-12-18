CREATE DATABASE IF NOT EXISTS tictactoe;
USE tictactoe;
CREATE TABLE IF NOT EXISTS leaderboard (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(100) UNIQUE NOT NULL,         
    wins INT DEFAULT 0,moves INT DEFAULT 0,time_taken BIGINT DEFAULT 0                
);
INSERT INTO leaderboard (name, wins, moves, time_taken)VALUES ('Test Player', 1, 10, 5000)
ON DUPLICATE KEY UPDATE
    wins = wins + 1,
    moves = moves + 10,
    time_taken = time_taken + 5000;
SELECT * FROM leaderboard ORDER BY wins DESC;
desc leaderboard;
