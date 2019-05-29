package craftheim.el.mod.items;

import craftheim.el.mod.items.base.ItemBase;
import craftheim.el.mod.server.data.GlobalData;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class ItemCreditCard extends ItemBase {

    private final String TAG_OWNER = "Owner";
    private final String TAG_OUID = "OwnerID";

    public ItemCreditCard(String name) {
        super(name);
        setMaxStackSize(1);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer playerIn, @Nonnull EnumHand hand) {
        ItemStack stack = playerIn.getHeldItem(hand);
        if (!world.isRemote) {
            if(getOwner(stack).equals("")) {
                setOwner(stack, playerIn.getUniqueID(), playerIn.getName());
            }
            /*
            EnderLibre.LOGGER.warn(
                    GlobalData.getInstance(world).getBank().get(playerIn.getUniqueID())
            );
            */
        }

        return new ActionResult<>(EnumActionResult.SUCCESS, stack);

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(TextFormatting.GRAY + I18n.format("tooltip.credit_card.signature") + ":");
        tooltip.add(TextFormatting.AQUA + (getOwner(stack).equals("") ? "_____" : getOwner(stack)) );
    }

    private void init(ItemStack stack) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }

        NBTTagCompound nbt = stack.getTagCompound();
        if (!nbt.hasKey(TAG_OWNER)) {
            nbt.setString(TAG_OWNER, "");
        }
        if (!nbt.hasKey(TAG_OUID)) {
            nbt.setString(TAG_OUID, "");
        }
    }

    private String getOwner(ItemStack stack) {
        init(stack);
        return stack.getTagCompound().getString(TAG_OWNER);
    }

    private void setOwner(ItemStack stack, UUID owner, String name) {
        init(stack);
        stack.getTagCompound().setString(TAG_OUID, owner.toString());
        stack.getTagCompound().setString(TAG_OWNER, name);
    }
}
