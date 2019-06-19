package craftheim.el.mod.items.base;

import craftheim.el.mod.EnderLibre;
import craftheim.el.mod.blocks.render.IHasModel;
import craftheim.el.mod.creative.CreativeTab;
import craftheim.el.mod.items.InitItems;
import net.minecraft.item.Item;

public abstract class ItemBase extends Item implements IHasModel {

    public ItemBase(String name) {
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(CreativeTab.INSTANCE);

        InitItems.ITEMS.add(this);
    }

    @Override
    public void registerRendering()
    {
        EnderLibre.PROXY.registerItemRenderer(this, 0, "inventory");
    }
}
