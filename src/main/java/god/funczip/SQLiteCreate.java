package god.funczip;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static god.funczip.Funczip.MODID;

@OnlyIn(Dist.DEDICATED_SERVER)
public class SQLiteCreate {
    public void create() {
        String url = ("jdbc:sqlite:" + MODID + ".db");
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                String sql = "CREATE TABLE IF NOT EXISTS users (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "name TEXT NOT NULL, " +
                        "IP TEXT NOT NULL )";
                try (var statement = conn.createStatement()) {
                    statement.executeUpdate(sql);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}