package god.funczip;

import com.mojang.logging.LogUtils;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import god.funczip.CustomSet.DisCraftData;
import god.funczip.EventSet.Server.ChatTestEvent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtAccounter;
import net.minecraft.nbt.NbtIo;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLEnvironment;
import org.slf4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
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

import static god.funczip.BlockRegister.BLOCKS;
import static god.funczip.EntityRegister.ENTITIES;
import static god.funczip.FluidRegister.FLUIDS;
import static god.funczip.FluidTypeRegister.FLUIDTYPES;
import static god.funczip.ItemRegister.ITEMS;
import static god.funczip.SoundEventRegister.SOUNDEVENTS;

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
    public static HashMap<String, DisCraftData> disrecipes = new HashMap<>();

    public Funczip(IEventBus modEventBus, ModContainer modContainer) throws SQLException, IOException {
        if (FMLEnvironment.dist.isDedicatedServer()) {
            new File("config/" + MODID).mkdir();
            File tempf1 = new File("config/funczip/startkit.dat");
            if (!tempf1.exists()) {
                tempf1.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(tempf1);
                fileOutputStream.write(ByteData.startkit);
                fileOutputStream.close();
            }
            loaddisrecipe();
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
                    Math.max(1, (int) (5 * stmt.executeQuery(sql).getLong(1))),
                    60L,
                    TimeUnit.SECONDS,
                    new SynchronousQueue<Runnable>());
            stmt.close();
            conn.close();
        }
        SOUNDEVENTS.register(modEventBus);
        FLUIDTYPES.register(modEventBus);
        FLUIDS.register(modEventBus);
        BLOCKS.register(modEventBus);
        ENTITIES.register(modEventBus);
        ITEMS.register(modEventBus);
        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

    }

    public void loaddisrecipe() throws IOException {
        File tempf2 = new File("config/funczip/discraftrecipes.dat");
        if (!tempf2.exists()) {
            tempf2.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(tempf2);
            fileOutputStream.write(ByteData.discraftrecipes);
            fileOutputStream.close();
        }
        CompoundTag cpdt = NbtIo.readCompressed(Path.of("config/funczip/discraftrecipes.dat"), NbtAccounter.unlimitedHeap());
        cpdt.getAllKeys().forEach(key -> {
            disrecipes.put(key, DisCraftData.readFromNBT((CompoundTag) cpdt.get(key)));
            ChatTestEvent.mainn.put(key, (CompoundTag) cpdt.get(key));
        });
    }
}
