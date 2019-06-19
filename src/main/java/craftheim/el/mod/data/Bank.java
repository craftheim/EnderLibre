package craftheim.el.mod.data;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Bank implements INBTSerializable<NBTBase>
{
    private GlobalData _globalData;
    private Map<UUID,BankAccount> _accounts = new HashMap<>();

    public Bank(GlobalData gd) {
        _globalData = gd;
    }

    public boolean existsAccount(UUID playerID) {
        return _accounts.containsKey(playerID);
    }

    public void createAccount(UUID playerID, int startAmount) {
        _accounts.put(playerID, new BankAccount(startAmount));
        _globalData.markDirty();
    }

    public int get(UUID playerID) {
        return _accounts.get(playerID).getMoney();
    }

    public void set(UUID playerID, int qty) {
        _accounts.get(playerID).setMoney(qty);
        _globalData.markDirty();
    }

    public void setAll(int qty) {
        for(Map.Entry<UUID,BankAccount> entry : _accounts.entrySet()) {
            entry.getValue().setMoney(qty);
        }
        _globalData.markDirty();
    }

    public void take(UUID playerID, int qty) {
        BankAccount acc = _accounts.get(playerID);
        acc.setMoney(acc.getMoney() - qty);
        _globalData.markDirty();
    }

    public void give(UUID playerID, int qty) {
        BankAccount acc = _accounts.get(playerID);
        acc.setMoney(acc.getMoney() + qty);
        _globalData.markDirty();
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound accountsTag = new NBTTagCompound();
        for (Map.Entry<UUID, BankAccount> entry : _accounts.entrySet()) {
            accountsTag.setTag(entry.getKey().toString(), entry.getValue().serializeNBT());
        }

        return accountsTag;
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        NBTTagCompound accountsTag = (NBTTagCompound)nbt;

        for(String playerId : accountsTag.getKeySet()) {
            BankAccount account = new BankAccount();
            account.deserializeNBT(accountsTag.getTag(playerId));
            _accounts.put(UUID.fromString(playerId), account);
        }
    }
}
