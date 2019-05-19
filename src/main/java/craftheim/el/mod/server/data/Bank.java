package craftheim.el.mod.server.data;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Bank extends WorldSavedData
{
    private static final String DATA_NAME = "enderlibre";

    private Map<UUID,Integer> accounts = new HashMap<UUID,Integer>();

    public Bank() {
        super(DATA_NAME);
    }

    public Bank(String name) {
        super(name);
    }

    public boolean existsAccount(UUID playerID) {
        return accounts.containsKey(playerID);
    }

    public void createAccount(UUID playerID, int startAmount) {
        accounts.put(playerID, startAmount);
        this.markDirty();
    }

    public int get(UUID playerID) {
        return accounts.get(playerID);
    }

    public void set(UUID playerID, int qty) {
        accounts.put(playerID, qty);
        this.markDirty();
    }

    public void setAll(int qty) {
        for(Map.Entry<UUID,Integer> entry : accounts.entrySet()) {
            entry.setValue(qty);
        }
        this.markDirty();
    }

    public void take(UUID playerID, int qty) {
        accounts.put(playerID, accounts.get(playerID) - qty);
        this.markDirty();
    }

    public void give(UUID playerID, int qty) {
        accounts.put(playerID, accounts.get(playerID) + qty);
        this.markDirty();
    }


    public static Bank getInstance(World world) {
        MapStorage storage = world.getMapStorage();
        Bank instance = (Bank) storage.getOrLoadData(Bank.class, DATA_NAME);

        if (instance == null) {
            instance = new Bank();
            storage.setData(DATA_NAME, instance);
        }
        return instance;
    }

    /**
     * reads in data from the NBTTagCompound into this MapDataBase
     */
    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        NBTTagCompound accountsTag = nbt.getCompoundTag("bank");

        for(String uuid : accountsTag.getKeySet()) {
            accounts.put(UUID.fromString(uuid), accountsTag.getInteger(uuid));
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        NBTTagCompound accountsTag = new NBTTagCompound();
        for (Map.Entry<UUID, Integer> entry : accounts.entrySet()) {
            accountsTag.setInteger(entry.getKey().toString(), entry.getValue());
        }

        nbt.setTag("bank", accountsTag);
        return nbt;
    }
}
