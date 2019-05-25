package craftheim.el.mod.items;

import craftheim.el.mod.EnderLibre;
import craftheim.el.mod.items.base.ItemBase;
import craftheim.el.mod.server.data.GlobalData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemCreditCard extends ItemBase {

    public ItemCreditCard(String name) {
        super(name);
        setMaxStackSize(1);
    }


    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer playerIn, @Nonnull EnumHand hand) {
        ItemStack stack = playerIn.getHeldItem(hand);
        if (!world.isRemote) {
            /*
            EnderLibre.LOGGER.warn(
                    GlobalData.getInstance(world).getBank().get(playerIn.getUniqueID())
            );
            */
        }

        return new ActionResult<>(EnumActionResult.SUCCESS, stack);

    }



}
