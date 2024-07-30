package god.funczip.CommandSet;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtAccounter;
import net.minecraft.nbt.NbtIo;
import net.minecraft.server.level.ServerPlayer;

import java.io.IOException;
import java.nio.file.Path;

import static god.funczip.Funczip.MODID;
import static god.funczip.Funczip.ctp;

public class Startkitcmd {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("sk").
                requires((commandSource) ->
                        commandSource.hasPermission(4)).
                then(Commands.literal("set").executes((commandContext) -> {
                            ctp.execute(() -> {
                                try {
                                    setkit(commandContext);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                            return 1;
                        })
                ).
                then(Commands.literal("do").
                        then(Commands.argument("player", EntityArgument.player()).
                                executes((commandContext) -> {
                                    ctp.execute(() -> {
                                        try {
                                            dokit(commandContext);
                                        } catch (IOException | CommandSyntaxException e) {
                                            throw new RuntimeException(e);
                                        }
                                    });
                                    return 1;
                                })
                        )
                )
        );
    }

    public static void setkit(CommandContext<CommandSourceStack> ctx) throws IOException {
        if (ctx.getSource().getEntity() instanceof ServerPlayer p) {
            CompoundTag tag = new CompoundTag();
            tag.put("startkit", p.getInventory().save(new ListTag()));
            NbtIo.writeCompressed(tag, Path.of("config/" + MODID + "/startkit.dat"));
        }
    }

    public static void dokit(CommandContext<CommandSourceStack> ctx) throws IOException, CommandSyntaxException {
        if (EntityArgument.getEntity(ctx, "player") instanceof ServerPlayer p) {
            CompoundTag tag = NbtIo.readCompressed(Path.of("config/" + MODID + "/startkit.dat"), NbtAccounter.unlimitedHeap());
            ListTag listTag = tag.getList("startkit", 10);
            p.getInventory().load(listTag);
        }
    }
}
