package craftheim.el.mod.tiles;

import javax.annotation.Nullable;

import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;

public class TileEntityPos extends TileEntityOCComponent {
    private boolean enabled = false;

    public TileEntityPos() {
        super("pos");
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enable) {
        this.enabled = enable;
        markDirty();
        notifyBlockUpdate();
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        enabled = compound.getBoolean("enabled");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setBoolean("enabled", enabled);
        return compound;
    }

    private void notifyBlockUpdate() {
        final IBlockState state = getWorld().getBlockState(getPos());
        getWorld().notifyBlockUpdate(getPos(), state, state, 3);
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return writeToNBT(new NBTTagCompound());
    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(getPos(), 0, getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        readFromNBT(pkt.getNbtCompound());
        notifyBlockUpdate();
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
        return oldState.getBlock() != newState.getBlock();
    }

    @Callback
    @Optional.Method(modid = "opencomputers")
    public Object[] getMoney(Context cxt, Arguments arg) {
        return new Object[]{5};
    }
}
