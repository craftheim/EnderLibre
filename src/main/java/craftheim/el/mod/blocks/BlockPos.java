package craftheim.el.mod.blocks;

import craftheim.el.mod.blocks.base.BlockBase;
import net.minecraft.block.BlockLever;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPos extends BlockBase
{
    private static final Double px = 1d / 16d;
    public static final AxisAlignedBB AABB = new AxisAlignedBB(4 * px, 0 * px, 2 * px, 12 * px, 5 * px, 14 * px);
    //public static final PropertyDirection FACING = PropertyDirection.create("facing");
    public static final PropertyEnum<BlockLever.EnumOrientation> FACING = PropertyEnum.<BlockLever.EnumOrientation>create("facing", BlockLever.EnumOrientation.class);

    public BlockPos(String name)
    {
        super(name, Material.CLOTH);

        setDefaultState(blockState.getBaseState().withProperty(FACING, BlockLever.EnumOrientation.NORTH));
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) { return false; }

    @Override
    public boolean isFullCube(IBlockState state) { return false; }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, net.minecraft.util.math.BlockPos pos)
    {
        return AABB;

    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, net.minecraft.util.math.BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(FACING, BlockLever.EnumOrientation.forFacings(facing, placer.getHorizontalFacing()));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        //return this.getDefaultState().withProperty(FACING, EnumFacing.getFront(meta & 7));
        return this.getDefaultState().withProperty(FACING, BlockLever.EnumOrientation.byMetadata(meta & 7));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getMetadata();
    }
}
