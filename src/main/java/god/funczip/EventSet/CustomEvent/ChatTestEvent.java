package god.funczip.EventSet.CustomEvent;

import god.funczip.Funczip;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.ServerChatEvent;
import net.neoforged.neoforge.event.entity.player.ItemEntityPickupEvent;

import javax.management.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@EventBusSubscriber(modid = Funczip.MODID, value = Dist.DEDICATED_SERVER)
public class ChatTestEvent {
    @SubscribeEvent
    public static void onTest(ServerChatEvent event) throws InvocationTargetException, IllegalAccessException, IOException, ReflectionException, AttributeNotFoundException, InstanceNotFoundException, MBeanException, MalformedObjectNameException {
        ServerPlayer player = event.getPlayer();
        ServerLevel level = (ServerLevel) player.level();
        /*if (event.getRawText().contains("#")) {
            if (Items.AIR.equals(player.getItemInHand(InteractionHand.MAIN_HAND).getItem())) {
                event.setMessage(Component.nullToEmpty("I'm an idiot."));
                return;
            }
            CompoundTag ct = DisCraftData.BuildByPlayer(player);
            DisCraftData dcd = DisCraftData.readFromNBT(ct);
            ByteData.disrecipes.put(ct.getString("type"), dcd);
            ByteData.RWTag.put(ct.getString("type"), ct);
            NbtIo.writeCompressed(ByteData.RWTag, Path.of("config/funczip/discraftrecipes.dat"));
            event.setMessage(Component.nullToEmpty("Add: " + player.getItemInHand(InteractionHand.MAIN_HAND).getItem().toString()));
        } else if (event.getRawText().contains("$")) {
            ByteData.disrecipes.remove(player.getItemInHand(InteractionHand.MAIN_HAND).getItem().toString());
            ByteData.RWTag.remove(player.getItemInHand(InteractionHand.MAIN_HAND).getItem().toString());
            event.setMessage(Component.nullToEmpty("Delete: " + player.getItemInHand(InteractionHand.MAIN_HAND).getItem().toString()));
            NbtIo.writeCompressed(ByteData.RWTag, Path.of("config/funczip/discraftrecipes.dat"));
        }*//*if(event.getRawText().contains("666qaqa")) {
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
            ObjectName operatingSystemObjectName = new ObjectName("java.lang:type=OperatingSystem");
            int cpuCount = (int) mbs.getAttribute(operatingSystemObjectName, "AvailableProcessors");
            double systemLoadAverage = (double) mbs.getAttribute(operatingSystemObjectName, "SystemLoadAverage");
            try {
                ProcessBuilder pb = new ProcessBuilder("lscpu");
                Process p = pb.start();
                BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Model name:")) {
                        System.out.println(line.split(":")[1].trim());
                    }
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                ProcessBuilder processBuilder = new ProcessBuilder("wmic", "cpu", "get", "CurrentClockSpeed");
                Process process = processBuilder.start();

                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while((line = reader.readLine()) != null) {
                    // 解析输出结果，获取CPU频率
                    if (line.matches("\\d+")) {
                        int clockSpeed = Integer.parseInt(line.trim());
                        System.out.println("Current CPU Clock Speed: " + clockSpeed + " MHz");
                    }
                }

                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("CPUcore" + cpuCount);
            System.out.println("load" + systemLoadAverage);
        }*/
    }
}
