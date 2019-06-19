package craftheim.el.mod.data;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.UUID;

public class ItemSale implements INBTSerializable<NBTBase> {
    private UUID _saleId;
    private UUID _sellerId;
    private ItemStack _stack;
    private int _price;

    public ItemSale(UUID saleId) {
        _saleId = saleId;
    }

    public ItemSale(UUID saleId, UUID sellerId, ItemStack stack, int price) {
        _saleId = saleId;
        _sellerId = sellerId;
        _stack = stack;
        _price = price;
    }

    public UUID getSaleId() { return _saleId; }

    public UUID getSellerId() { return _sellerId; }

    public ItemStack getItemStack() { return _stack; }

    public int getPrice() { return _price; }

    @Override
    public NBTBase serializeNBT() {
        NBTTagCompound saleTag = new NBTTagCompound();

        saleTag.setString("seller", _sellerId.toString());
        saleTag.setTag("item", _stack.serializeNBT());
        saleTag.setInteger("price", _price);

        return saleTag;
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        NBTTagCompound saleTag = (NBTTagCompound)nbt;

        _sellerId = UUID.fromString(saleTag.getString("seller"));
        _stack = new ItemStack(saleTag.getCompoundTag("item"));
        _price = saleTag.getInteger("price");
    }
}
