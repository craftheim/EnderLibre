package craftheim.el.mod.events;

import craftheim.el.mod.EnderLibre;
import craftheim.el.mod.data.Bank;
import craftheim.el.mod.data.GlobalData;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = "enderlibre")
public class BankHandler {
    public static final ResourceLocation LOCATION = new ResourceLocation(EnderLibre.MOD_ID, "bank");

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event)
    {
        Bank bank = GlobalData.getInstance(event.player.world).getBank();
        UUID id = event.player.getPersistentID();
        if(!bank.existsAccount(id))
            bank.createAccount(id, 15);
    }
}
