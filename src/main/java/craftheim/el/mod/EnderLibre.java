package craftheim.el.mod;

import craftheim.el.mod.common.capabilities.Capabilities;
import craftheim.el.mod.proxy.ServerProxy;
import craftheim.el.mod.server.commands.CommandTest;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = EnderLibre.ID, name = EnderLibre.Name,
    version = EnderLibre.Version, useMetadata = true)
public class EnderLibre {
    public static final String ID = "enderlibre";
    public static final String Name = "EnderLibre";
    public static final String Version = "0.1.0";

    @Mod.Instance
    public static EnderLibre instance;

    @SidedProxy(clientSide = "craftheim.el.mod.proxy.ClientProxy", serverSide = "craftheim.el.mod.proxy.ServerProxy")
    public static ServerProxy PROXY;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Capabilities.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandTest());
    }
}
