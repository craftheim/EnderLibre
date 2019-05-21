package craftheim.el.mod.blocks;

import craftheim.el.mod.blocks.base.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;

public class BlockPos extends BlockBase
{
    private static final Double px = 1d / 16d;
    public static final AxisAlignedBB AABB = new AxisAlignedBB(4 * px, 0 * px, 2 * px, 12 * px, 5 * px, 14 * px);

    public BlockPos(String name)
    {
        super(name, Material.CLOTH);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, net.minecraft.util.math.BlockPos pos)
    {
        return AABB;

    }
}
