package craftheim.el.mod.events;

import craftheim.el.mod.EnderLibre;
import craftheim.el.mod.blocks.InitBlocks;
import craftheim.el.mod.blocks.render.IHasModel;
import craftheim.el.mod.items.InitItems;
import craftheim.el.mod.tiles.TileEntityPos;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class RegistryHandler
{

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll(InitItems.ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event)
    {
        event.getRegistry().registerAll(InitBlocks.BLOCKS.toArray(new Block[0]));
        GameRegistry.registerTileEntity(TileEntityPos.class, new ResourceLocation(EnderLibre.MOD_ID + ":pos"));
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event)
    {
        for (Item item : InitItems.ITEMS)
        {
            if (item instanceof IHasModel)
            {
                ((IHasModel)item).registerRendering();
            }
        }

        for (Block block : InitBlocks.BLOCKS)
        {
            if (block instanceof IHasModel)
            {
                ((IHasModel)block).registerRendering();
            }
        }
    }
}
