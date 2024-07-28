package god.funczip;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
@EventBusSubscriber(modid = Funczip.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Config {

    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec.ConfigValue<String> INVKITC = BUILDER
            .comment("json format")
            .define("startkit", "[{\"tags\":{\"count\":{\"data\":64},\"Slot\":{\"data\":0},\"id\":{\"data\":\"minecraft:torch\"}}},{\"tags\":{\"count\":{\"data\":1},\"Slot\":{\"data\":1},\"id\":{\"data\":\"minecraft:iron_pickaxe\"}}},{\"tags\":{\"count\":{\"data\":1},\"Slot\":{\"data\":2},\"id\":{\"data\":\"minecraft:iron_axe\"}}},{\"tags\":{\"count\":{\"data\":1},\"Slot\":{\"data\":3},\"id\":{\"data\":\"minecraft:fishing_rod\"}}},{\"tags\":{\"count\":{\"data\":1},\"Slot\":{\"data\":4},\"id\":{\"data\":\"minecraft:iron_hoe\"}}},{\"tags\":{\"count\":{\"data\":1},\"Slot\":{\"data\":5},\"id\":{\"data\":\"minecraft:iron_sword\"}}},{\"tags\":{\"count\":{\"data\":1},\"Slot\":{\"data\":6},\"id\":{\"data\":\"minecraft:iron_shovel\"}}},{\"tags\":{\"count\":{\"data\":1},\"Slot\":{\"data\":100},\"id\":{\"data\":\"minecraft:iron_boots\"}}},{\"tags\":{\"count\":{\"data\":1},\"Slot\":{\"data\":101},\"id\":{\"data\":\"minecraft:iron_leggings\"}}},{\"tags\":{\"count\":{\"data\":1},\"Slot\":{\"data\":102},\"id\":{\"data\":\"minecraft:iron_chestplate\"}}},{\"tags\":{\"count\":{\"data\":1},\"Slot\":{\"data\":103},\"id\":{\"data\":\"minecraft:iron_helmet\"}}},{\"tags\":{\"count\":{\"data\":1},\"Slot\":{\"data\":-106},\"id\":{\"data\":\"minecraft:shield\"}}}]");


    public static final ModConfigSpec SPEC = BUILDER.build();


    public static String invkit;


    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        invkit = INVKITC.get();


        /*logDirtBlock = LOG_DIRT_BLOCK.get();
        magicNumber = MAGIC_NUMBER.get();
        */

        // convert the list of strings into a set of items
        /*items = ITEM_STRINGS.get().stream()
                .map(itemName -> BuiltInRegistries.ITEM.get(ResourceLocation.parse(itemName)))
                .collect(Collectors.toSet());*/
    }
    /*private static final ModConfigSpec.BooleanValue LOG_DIRT_BLOCK = BUILDER
            .comment("Whether to log the dirt block on common setup")
            .define("logDirtBlock", true);

    private static final ModConfigSpec.IntValue MAGIC_NUMBER = BUILDER
            .comment("A magic number")
            .defineInRange("magicNumber", 42, 0, Integer.MAX_VALUE);



    // a list of strings that are treated as resource locations for items
    private static final ModConfigSpec.ConfigValue<List<? extends String>> ITEM_STRINGS = BUILDER
            .comment("A list of items to log on common setup.")
            .defineListAllowEmpty("items", List.of("minecraft:iron_ingot"), Config::validateItemName);*/



    /*public static boolean logDirtBlock;
    public static int magicNumber;
    public static String magicNumberIntroduction;
    public static Set<Item> items;*/

    /*private static boolean validateItemName(final Object obj)
    {
        return obj instanceof String itemName && BuiltInRegistries.ITEM.containsKey(ResourceLocation.parse(itemName));
    }*/


}
