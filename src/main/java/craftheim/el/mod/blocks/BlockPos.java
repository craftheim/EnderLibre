package craftheim.el.mod.blocks;

import craftheim.el.mod.blocks.base.BlockBase;
import craftheim.el.mod.tiles.TileEntityPos;
import craftheim.el.util.EnumOrientation;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlockPos extends BlockBase
{
    private static final Double px = 1d / 16d;
    private static final AxisAlignedBB AABB_UP_TONORTH = new AxisAlignedBB(4 * px, 0 * px, 2 * px, 12 * px, 5 * px, 14 * px);
    private static final AxisAlignedBB AABB_UP_TOEAST = new AxisAlignedBB(2 * px, 0 * px, 4 * px, 14 * px, 5 * px, 12 * px);
    private static final AxisAlignedBB AABB_DOWN_TONORTH = new AxisAlignedBB(4 * px, 16 * px, 2 * px, 12 * px, 11 * px, 14 * px);
    private static final AxisAlignedBB AABB_DOWN_TOEAST = new AxisAlignedBB(2 * px, 16 * px, 4 * px, 14 * px, 11 * px, 12 * px);
    private static final AxisAlignedBB AABB_NORTH = new AxisAlignedBB(4 * px, 2 * px, 16 * px, 12 * px, 14 * px, 11 * px);
    private static final AxisAlignedBB AABB_SOUTH = new AxisAlignedBB(4 * px, 2 * px, 0 * px, 12 * px, 14 * px, 5 * px);
    private static final AxisAlignedBB AABB_WEST = new AxisAlignedBB(16 * px, 2 * px, 4 * px, 11 * px, 14 * px, 12 * px);
    private static final AxisAlignedBB AABB_EAST = new AxisAlignedBB(0 * px, 2 * px, 4 * px, 5 * px, 14 * px, 12 * px);

    private static final PropertyEnum<EnumOrientation> FACING = PropertyEnum.<EnumOrientation>create("facing", EnumOrientation.class);
    public static final PropertyBool ENABLED = PropertyBool.create("enabled");


    public BlockPos(String name)
    {
        super(name, Material.CLOTH);

        setDefaultState(blockState.getBaseState().withProperty(FACING, EnumOrientation.NORTH));
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) { return false; }

    @Override
    public boolean isFullCube(IBlockState state) { return false; }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityPos();
    }

    public TileEntityPos getTileEntity(IBlockAccess world, net.minecraft.util.math.BlockPos pos) {
        return (TileEntityPos) world.getTileEntity(pos);
    }

    public Boolean getEnabled(IBlockAccess world, net.minecraft.util.math.BlockPos pos) {
        final TileEntityPos tileEntity = getTileEntity(world, pos);
        return tileEntity != null ? tileEntity.getEnabled() : false;
    }

    public void setEnabled(IBlockAccess world, net.minecraft.util.math.BlockPos pos, Boolean enabled) {
        final TileEntityPos tileEntity = getTileEntity(world, pos);
        if (tileEntity != null) {
            tileEntity.setEnabled(enabled);
        }
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, net.minecraft.util.math.BlockPos pos, Block blockIn, net.minecraft.util.math.BlockPos fromPos) {
        int powered = worldIn.isBlockIndirectlyGettingPowered(pos);
        setEnabled(worldIn, pos, powered > 0);
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, net.minecraft.util.math.BlockPos pos) {
        return state.withProperty(ENABLED, getEnabled(worldIn, pos));
    }

    @Override
    public @Nonnull AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, net.minecraft.util.math.BlockPos pos)
    {
        switch( state.getValue(FACING) )
        {
            case UP_TONORTH:
            case UP_TOSOUTH:
            default:
                return AABB_UP_TONORTH;
            case UP_TOEAST:
            case UP_TOWEST:
                return AABB_UP_TOEAST;
            case DOWN_TONORTH:
            case DOWN_TOSOUTH:
                return AABB_DOWN_TONORTH;
            case DOWN_TOEAST:
            case DOWN_TOWEST:
                return AABB_DOWN_TOEAST;
            case NORTH:
                return AABB_NORTH;
            case SOUTH:
                return AABB_SOUTH;
            case WEST:
                return AABB_WEST;
            case EAST:
                return AABB_EAST;
        }
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, net.minecraft.util.math.BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(FACING, EnumOrientation.forFacings(facing, placer.getHorizontalFacing()));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {FACING, ENABLED});
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FACING, EnumOrientation.byMetadata(meta & 15));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getMetadata();
    }
}
