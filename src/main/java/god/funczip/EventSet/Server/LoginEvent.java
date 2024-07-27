package god.funczip.EventSet.Server;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtAccounter;
import net.minecraft.nbt.NbtIo;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static god.funczip.Funczip.*;


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
            CompoundTag tag = NbtIo.readCompressed(Path.of("config/" + Container.getModId() + "/startkit.dat"), NbtAccounter.unlimitedHeap());
            ListTag listTag = tag.getList("startkit", 10);
            inv.load(listTag);
            String sql2 = "INSERT INTO users (name, IP) VALUES ('" + p.getName().getString() + "','" + p.connection.getRemoteAddress().toString().substring(1) + "\');";
            stmt.execute(sql2);
        }
        stmt.close();
        conn.close();
    }
}
