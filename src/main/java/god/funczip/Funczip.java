package god.funczip;

import com.mojang.logging.LogUtils;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtIo;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLEnvironment;
import org.slf4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
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

    public Funczip(IEventBus modEventBus, ModContainer modContainer) throws SQLException, IOException {
        if (FMLEnvironment.dist.isDedicatedServer()) {
            new File("config/" + MODID).mkdir();
            File tempf = new File("config/funczip/startkit.dat");
            if(!tempf.exists()){
                tempf.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(tempf);
                fileOutputStream.write(ByteData.startkit);
                fileOutputStream.close();
            }
            new SQLiteCreate().create();
            HikariConfig config = new HikariConfig();
            config.setDriverClassName("org.sqlite.JDBC");
            config.setJdbcUrl("jdbc:sqlite:config/" + MODID + "/playerdata.db");
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
        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

    }
}
