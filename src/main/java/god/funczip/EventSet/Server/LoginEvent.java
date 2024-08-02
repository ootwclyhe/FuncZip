package god.funczip.EventSet.Server;

import god.funczip.CustomSet.ByteData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static god.funczip.Funczip.MODID;
import static god.funczip.Funczip.ctp;


@EventBusSubscriber(modid = MODID, value = Dist.DEDICATED_SERVER)
public class LoginEvent {
    @SubscribeEvent
    public static void onLogin(PlayerEvent.PlayerLoggedInEvent event) throws SQLException, IOException {
        ServerPlayer p = (ServerPlayer) event.getEntity();
        ctp.execute(() -> {
            try {
                startkit(p);
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

    public static void startkit(ServerPlayer p) throws SQLException, IOException {
        Connection conn = god.funczip.Funczip.dataSource.getConnection();
        Statement stmt = conn.createStatement();
        String sql1 = "SELECT name FROM users WHERE name = '" + p.getName().getString() + "';";
        ResultSet rs = stmt.executeQuery(sql1);
        if (!rs.next()) {
            Inventory inv = p.getInventory();
            inv.load(ByteData.startkit);
            String sql2 = "INSERT INTO users (name, IP) VALUES ('" + p.getName().getString() + "','" + p.connection.getRemoteAddress().toString().substring(1) + "\');";
            stmt.execute(sql2);
        }
        stmt.close();
        conn.close();
    }
}
