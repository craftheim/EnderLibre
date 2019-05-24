package craftheim.el.mod.creative;

import craftheim.el.mod.EnderLibre;
import craftheim.el.mod.blocks.InitBlocks;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

    public static final CreativeTab INSTANCE = new CreativeTab();
    private NonNullList<ItemStack> list;

    public CreativeTab() {
        super(EnderLibre.MOD_ID);
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(InitBlocks.blockPos);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void displayAllRelevantItems(NonNullList<ItemStack> list) {
        this.list = list;

        this.add(InitBlocks.blockPos);


    }

    public void add(Item item)
    {
        if (item != null) {
            item.getSubItems(INSTANCE, this.list);
        }
    }

    public void add(Block block)
    {
        if (block != null) {
            block.getSubBlocks(INSTANCE, this.list);
        }
    }
}
