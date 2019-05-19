package craftheim.el.mod.common.events;

import craftheim.el.mod.EnderLibre;
import craftheim.el.mod.common.capabilities.CapabilityBank;
import craftheim.el.mod.server.data.Bank;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = "enderlibre")
public class BankHandler {
    public static final ResourceLocation LOCATION = new ResourceLocation(EnderLibre.ID, "bank");

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event)
    {
        Bank bank = Bank.getInstance(event.player.getEntityWorld());
        UUID id = event.player.getPersistentID();
        if(!bank.existsAccount(id))
            bank.createAccount(id, 15);
    }
}
