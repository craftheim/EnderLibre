package craftheim.el.mod.gui.container;

import java.util.List;

import craftheim.el.mod.data.GlobalData;
import craftheim.el.mod.data.ItemSale;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MarketContainer extends Container {
    private World _world;
    private BlockPos _pos;

    public MarketContainer(World world, BlockPos pos) {
        _pos = pos;
        _world = world;

        IInventory inventory = getSideInventory(world);
        addSlots(inventory);
    }

    IInventory getSideInventory(World world) {
        if(world.isRemote)
            return new InventoryBasic("Market", false, 9);
        else {
            List<ItemSale> sales = GlobalData.getInstance(world).getMarket().getSales();
            
            InventoryBasic inventory = new InventoryBasic("Market", false, sales.size());
            for(int i = 0; i < sales.size(); i++) {
                ItemSale sale = sales.get(i);
                ItemStack stackToAdd = sales.get(i).getItemStack();
                stackToAdd.setTagInfo("price", new NBTTagInt(sale.getPrice()));
                inventory.setInventorySlotContents(i, stackToAdd);
            }

            return inventory;
        }
    }
    
    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        if (_world.getBlockState(_pos).getBlock() != Block.getBlockFromName("enderlibre:block_market"))
            return false;
        else
            return playerIn.getDistanceSq(_pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
    }

    public void addSlots(IInventory inventory) {
        for(int i = 0; i < 9; i++) {
            this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 18) {
                @Override
                public boolean canTakeStack(EntityPlayer playerIn) {
                    return false;
                }
            });
        }
    }

    @Override
    public boolean canMergeSlot(ItemStack stack, Slot slotIn)
    {
        return false;
    }
}