package craftheim.el.mod.data;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.*;

public class Market implements INBTSerializable<NBTBase>
{
    private GlobalData _globalData;
    private Map<UUID, ItemSale> _sales = new HashMap<>();
    private int _count = 0;

    public Market(GlobalData gd) {
        _globalData = gd;
    }

    public int getCount() { return _count; }

    public void setCount(int count) {
        _count = count;
        _globalData.markDirty();
    }

    public void clear() {
        _sales.clear();
        _globalData.markDirty();
    }

    public void addSale(UUID playerId, ItemStack stack, int price) {
        ItemSale sale = new ItemSale(UUID.randomUUID(), playerId, stack, price);
        _sales.put(sale.getSaleId(), sale);
        _globalData.markDirty();
    }

    public void removeSale(UUID saleId) {
        _sales.remove(saleId);
        _globalData.markDirty();
    }

    public List<ItemSale> getSales() {
        List<ItemSale> sales = new ArrayList<>(_sales.values());
        sales.sort((ItemSale is1, ItemSale is2) -> is1.getSaleId().compareTo(is2.getSaleId()));
        return sales;
    }

    public List<ItemSale> getPlayerSales(UUID playerId) {
        List<ItemSale> playerSales = new ArrayList<>();
        for(ItemSale sale : _sales.values()) {
            if(playerId.equals(sale.getSellerId()))
                playerSales.add(sale);
        }

        return playerSales;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound marketTag = new NBTTagCompound();

        NBTTagCompound sales = new NBTTagCompound();
        for(Map.Entry<UUID, ItemSale> entry : _sales.entrySet())
            sales.setTag(entry.getKey().toString(), entry.getValue().serializeNBT());

        marketTag.setTag("sales", sales);

        marketTag.setInteger("count", _count);

        return marketTag;
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        NBTTagCompound marketTag = (NBTTagCompound)nbt;

        NBTTagCompound sales = marketTag.getCompoundTag("sales");
        for(String saleId : sales.getKeySet()) {
            ItemSale sale = new ItemSale(UUID.fromString(saleId));
            sale.deserializeNBT(sales.getTag(saleId));
            _sales.put(sale.getSaleId(), sale);
        }

        _count = marketTag.getInteger("count");
    }
}
