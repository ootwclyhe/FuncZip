package god.funczip;

import com.mojang.logging.LogUtils;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import god.funczip.EventSet.CustomEvent.FishBitEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Funczip.MODID)
public class Funczip {
    public static final String MODID = "funczip";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static ExecutorService ctp = new ThreadPoolExecutor(
            0,
            64,
            120L,
            TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>());

    public static HikariDataSource dataSource = null;
    public static HashMap<Player, BlockPos> backmap = new HashMap<>();
    public static ModContainer Container = null;

    public Funczip(IEventBus modEventBus, ModContainer modContainer) throws SQLException, IOException {
        Container = modContainer;
        if (FMLEnvironment.dist.isDedicatedServer()) {
            new SQLiteCreate().create();
            HikariConfig config = new HikariConfig();
            config.setDriverClassName("org.sqlite.JDBC");
            config.setJdbcUrl("jdbc:sqlite:" + MODID + ".db");
            config.setMaximumPoolSize(64);
            dataSource = new HikariDataSource(config);
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "SELECT COUNT(*) FROM users";
            ctp = new ThreadPoolExecutor(
                    0,
                    Math.max(1, (int) (3 * stmt.executeQuery(sql).getLong(1))),
                    60L,
                    TimeUnit.SECONDS,
                    new SynchronousQueue<Runnable>());
            stmt.close();
            conn.close();
        }
        Path.of("config/" + Container.getModId()).toFile().mkdir();
        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}
