package craftheim.el.mod.server.data;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagInt;
import net.minecraftforge.common.util.INBTSerializable;

public class BankAccount implements INBTSerializable<NBTBase> {
    private int _money;

    public BankAccount() {
    }

    public BankAccount(int money) {
        _money = money;
    }

    public int getMoney() {
        return _money;
    }

    public void setMoney(int money) {
        _money = money;
    }

    @Override
    public NBTBase serializeNBT() {
        return new NBTTagInt(_money);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        NBTTagInt money = (NBTTagInt)nbt;
        _money = money.getInt();
    }
}
