package craftheim.el.mod;

import craftheim.el.mod.proxy.ServerProxy;
import craftheim.el.mod.server.commands.CommandEnderLibre;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = EnderLibre.MOD_ID, name = EnderLibre.MOD_NAME, version = EnderLibre.VERSION)
public class EnderLibre {
    public static final String MOD_ID = "enderlibre";
    public static final String MOD_NAME = "EnderLibre";
    public static final String VERSION = "0.1.1";

    @Mod.Instance
    public static EnderLibre instance;

    @SidedProxy(clientSide = "craftheim.el.mod.proxy.ClientProxy", serverSide = "craftheim.el.mod.proxy.ServerProxy")
    public static ServerProxy PROXY;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandEnderLibre());
    }
}
