package craftheim.el.common.events;

import craftheim.el.mod.EnderLibre;
import craftheim.el.common.capabilities.CapabilityBank;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = "enderlibre")
public class BankHandler {
    public static final ResourceLocation LOCATION = new ResourceLocation(EnderLibre.ID, "bank");

    @SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent<World> event)
    {
        event.addCapability(LOCATION, new CapabilityBank.DefaultProvider());
    }
}
