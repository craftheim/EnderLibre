package craftheim.el.mod.data;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

public class GlobalData extends WorldSavedData
{
    private static final String DATA_NAME = "enderlibre";

    private Bank _bank;
    private Market _market;

    public GlobalData(World world) {
        super(DATA_NAME);

        createDataModules();
    }

    public GlobalData(String name) {
        super(name);

        createDataModules();
    }

    private void createDataModules() {
        _bank = new Bank(this);
        _market = new Market(this);
    }

    public static GlobalData getInstance(World world) {
        MapStorage storage = world.getMapStorage();
        GlobalData instance = (GlobalData) storage.getOrLoadData(GlobalData.class, DATA_NAME);

        if (instance == null) {
            instance = new GlobalData(world);
            storage.setData(DATA_NAME, instance);
        }
        return instance;
    }

    public Bank getBank() {
        return _bank;
    }

    public Market getMarket() {
        return _market;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt.setTag("bank", _bank.serializeNBT());
        nbt.setTag("market", _market.serializeNBT());

        return nbt;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        _bank.deserializeNBT(nbt.getCompoundTag("bank"));
        _market.deserializeNBT(nbt.getCompoundTag("market"));
    }
}

