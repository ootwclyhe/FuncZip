package god.funczip.CustomSet;

import god.funczip.Funczip;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtAccounter;
import net.minecraft.nbt.NbtIo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

import static god.funczip.Funczip.MODID;

public class ByteData {
    public static CompoundTag RWTag = new CompoundTag();
    public static HashMap<String, DisCraftData> disrecipes = new HashMap<>();

    public static void initDataFiles() throws IOException {
        new File("config/" + MODID).mkdir();
        initStartKit();
        initDisRecipes();
    }

    public static void initDisRecipes() throws IOException {
        String recipeurl = "assets/funczip/discraftrecipes.dat";
        byte[] discraftrecipe = Funczip.class.getClassLoader().getResourceAsStream(recipeurl).readAllBytes();
        File temp = new File("config/funczip/discraftrecipes.dat");
        if (!temp.exists()) {
            temp.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(temp);
            fileOutputStream.write(discraftrecipe);
            fileOutputStream.close();
        }
        CompoundTag tag = NbtIo.readCompressed(Path.of("config/funczip/discraftrecipes.dat"), NbtAccounter.unlimitedHeap());
        tag.getAllKeys().forEach(key -> {
            disrecipes.put(key, DisCraftData.readFromNBT((CompoundTag) tag.get(key)));
            RWTag.put(key, (CompoundTag) tag.get(key));
        });
    }

    public static ListTag startkit = new ListTag();

    public static void initStartKit() throws IOException {
        String recipeurl = "assets/funczip/startkit.dat";
        byte[] discraftrecipe = Funczip.class.getClassLoader().getResourceAsStream(recipeurl).readAllBytes();
        File temp = new File("config/funczip/startkit.dat");
        if (!temp.exists()) {
            temp.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(temp);
            fileOutputStream.write(discraftrecipe);
            fileOutputStream.close();
        }
        CompoundTag tag = NbtIo.readCompressed(Path.of("config/" + MODID + "/startkit.dat"), NbtAccounter.unlimitedHeap());
        startkit = tag.getList("startkit", 10);
    }
}
