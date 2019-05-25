package craftheim.el.mod.blocks.base;

import craftheim.el.mod.EnderLibre;
import craftheim.el.mod.blocks.InitBlocks;
import craftheim.el.mod.blocks.render.IHasModel;
import craftheim.el.mod.creative.CreativeTab;
import craftheim.el.mod.items.InitItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block implements IHasModel
{
    public BlockBase(String name, Material material)
    {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTab.INSTANCE);

        InitBlocks.BLOCKS.add(this);
        InitItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));

    }

    @Override
    public void registerRendering()
    {
        EnderLibre.PROXY.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

}
