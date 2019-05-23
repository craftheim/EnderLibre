package craftheim.el.util;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;

public enum EnumOrientation implements IStringSerializable {
    DOWN_TONORTH(0, "down_tonorth"),
    DOWN_TOSOUTH(1, "down_tosouth"),
    DOWN_TOWEST(2, "down_towest"),
    DOWN_TOEAST(3, "down_toeast"),

    UP_TONORTH(4, "up_tonorth"),
    UP_TOSOUTH(5, "up_tosouth"),
    UP_TOWEST(6, "up_towest"),
    UP_TOEAST(7, "up_toeast"),

    NORTH(8, "north"),
    SOUTH(9, "south"),
    WEST(10, "west"),
    EAST(11, "east");

    private static final EnumOrientation[] META_LOOKUP = new EnumOrientation[values().length];
    private final int meta;
    private final String name;

    static
    {
        for (EnumOrientation orientation : values())
            META_LOOKUP[orientation.getMetadata()] = orientation;
    }

    EnumOrientation(int meta, String name) {
        this.meta = meta;
        this.name = name;
    }

    public int getMetadata() { return this.meta; }

    @Override
    public String getName() { return this.name; }

    public static EnumOrientation byMetadata(int meta) {
        if (meta < 0 || meta >= META_LOOKUP.length)
        {
            meta = 0;
        }

        return META_LOOKUP[meta];
    }

    public static EnumOrientation forFacings(EnumFacing clickedSide, EnumFacing entityFacing)
    {
        switch (clickedSide)
        {
            case DOWN:

                switch (entityFacing.getAxis())
                {
                    case X:

                        switch (entityFacing.getAxisDirection())
                        {
                            case NEGATIVE:
                                return DOWN_TOEAST;
                            case POSITIVE:
                                return DOWN_TOWEST;
                            default:
                                throw new IllegalArgumentException("Invalid entityFacing " + entityFacing + " for facing " + clickedSide);
                        }

                    case Z:
                        switch (entityFacing.getAxisDirection())
                        {
                            case NEGATIVE:
                                return DOWN_TONORTH;
                            case POSITIVE:
                                return DOWN_TOSOUTH;
                            default:
                                throw new IllegalArgumentException("Invalid entityFacing " + entityFacing + " for facing " + clickedSide);
                        }
                    default:
                        throw new IllegalArgumentException("Invalid entityFacing " + entityFacing + " for facing " + clickedSide);
                }

            case UP:

                switch (entityFacing.getAxis())
                {
                    case X:

                        switch (entityFacing.getAxisDirection())
                        {
                            case NEGATIVE:
                                return UP_TOEAST;
                            case POSITIVE:
                                return UP_TOWEST;
                            default:
                                throw new IllegalArgumentException("Invalid entityFacing " + entityFacing + " for facing " + clickedSide);
                        }

                    case Z:
                        switch (entityFacing.getAxisDirection())
                        {
                            case NEGATIVE:
                                return UP_TOSOUTH;
                            case POSITIVE:
                                return UP_TONORTH;
                            default:
                                throw new IllegalArgumentException("Invalid entityFacing " + entityFacing + " for facing " + clickedSide);
                        }
                    default:
                        throw new IllegalArgumentException("Invalid entityFacing " + entityFacing + " for facing " + clickedSide);
                }

            case NORTH:
                return NORTH;
            case SOUTH:
                return SOUTH;
            case WEST:
                return WEST;
            case EAST:
                return EAST;
            default:
                throw new IllegalArgumentException("Invalid facing: " + clickedSide);
        }
    }
}
