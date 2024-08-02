package god.funczip.CommandSet;

import com.mojang.brigadier.CommandDispatcher;
import god.funczip.CustomSet.ByteData;
import god.funczip.CustomSet.DisCraftData;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.item.ItemArgument;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtIo;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.io.IOException;
import java.nio.file.Path;

import static god.funczip.Funczip.ctp;

public class Discraftcmd {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext context) {
        dispatcher.register(
                Commands.literal("dc").
                        requires(source -> source.hasPermission(4)).
                        then(
                                Commands.literal("set").
                                        executes(context1 -> {
                                            if (context1.getSource().getPlayer() instanceof ServerPlayer player) {
                                                ctp.execute(() -> {
                                                    setdiscraftrecipe(player);
                                                });
                                                player.sendSystemMessage(Component.nullToEmpty("Add:" + player.getItemInHand(InteractionHand.MAIN_HAND).getItem()));
                                                return 1;
                                            }
                                            return 0;
                                        })
                        ).then(
                                Commands.literal("del").
                                        then(Commands.argument("type", ItemArgument.item(context)).
                                                executes(context2 -> {
                                                    ctp.execute(() -> {
                                                        Item i = ItemArgument.getItem(context2, "type").getItem();
                                                        deldiscraftrecipe(i);
                                                        context2.getSource().sendSystemMessage(Component.nullToEmpty("Del:" + i));
                                                    });
                                                    return 1;
                                                }))
                        ).then(
                                Commands.literal("save").
                                        executes(context3 -> {
                                            try {
                                                savediscraftrecipe();
                                            } catch (IOException e) {
                                                throw new RuntimeException(e);
                                            }
                                            return 1;
                                        })
                        )
                //para dc
        );
    }

    private static void savediscraftrecipe() throws IOException {
        NbtIo.writeCompressed(ByteData.RWTag, Path.of("config/funczip/discraftrecipes.dat"));
    }

    private static void deldiscraftrecipe(Item type) {
        ByteData.disrecipes.remove(type.toString());
        ByteData.RWTag.remove(type.toString());
    }

    private static void setdiscraftrecipe(ServerPlayer player) {
        if (Items.AIR.equals(player.getItemInHand(InteractionHand.MAIN_HAND).getItem())) {
            player.sendSystemMessage(Component.nullToEmpty("You are an idiot."));
            return;
        }
        CompoundTag tag = DisCraftData.BuildByPlayer(player);
        DisCraftData dcd = DisCraftData.readFromNBT(tag);
        ByteData.disrecipes.put(tag.getString("type"), dcd);
        ByteData.RWTag.put(tag.getString("type"), tag);
    }
}
