package craftheim.el.mod.common.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CapabilityBank implements ICapabilityBank {
    private int money = 0;

    @Override
    public int get() {
        return money;
    }

    @Override
    public void set(int qty) {
        money = qty;
    }

    @Override
    public void take(int qty) {
        money -= qty;
    }

    @Override
    public void give(int qty) {
        money += qty;
    }

    public static class DefaultStorage implements Capability.IStorage<ICapabilityBank> {
        @Nullable
        @Override
        public NBTBase writeNBT(Capability<ICapabilityBank> capability, ICapabilityBank instance, EnumFacing side) {
            return new NBTTagInt(instance.get());
        }

        @Override
        public void readNBT(Capability<ICapabilityBank> capability, ICapabilityBank instance, EnumFacing side, NBTBase nbt) {
            instance.set(((NBTPrimitive)nbt).getInt());
        }
    }

    public static class DefaultProvider implements ICapabilitySerializable<NBTBase> {
        @CapabilityInject(ICapabilityBank.class)
        public static final Capability<ICapabilityBank> CAP = null;

        private ICapabilityBank instance = CAP.getDefaultInstance();

        @Override
        public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
            return capability == instance;
        }

        @Nullable
        @Override
        public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
            return capability == CAP ? CAP.cast(instance) : null;
        }

        @Override
        public NBTBase serializeNBT() {
            return CAP.getStorage().writeNBT(CAP, instance, null);
        }

        @Override
        public void deserializeNBT(NBTBase nbt) {
            CAP.getStorage().readNBT(CAP, instance, null, nbt);
        }
    }
}
