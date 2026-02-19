package hdsxwt.better_minecart.item;

import hdsxwt.better_minecart.entity.AcceleratedMinecartEntity;
import hdsxwt.better_minecart.entity.EntityRegisterHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.RailBlock;
import net.minecraft.block.enums.RailShape;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AcceleratedMinecartItem extends Item {
	public AcceleratedMinecartItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        BlockState blockState = world.getBlockState(blockPos);
        PlayerEntity player = context.getPlayer();
        ItemStack stack = context.getStack();


		// Check if the block above the rail is air to prevent spawning the minecart inside a block
		BlockPos spawnPos = blockPos.up();
        if (!world.getBlockState(spawnPos).isAir()) {
            return ActionResult.PASS;
        }

		if (world instanceof ServerWorld serverWorld) {
            // create the minecart entity
            AcceleratedMinecartEntity minecart = new AcceleratedMinecartEntity(
                EntityRegisterHandler.ACCELERATED_MINECART, 
                world
            );
            minecart.setPosition(spawnPos.getX() + 0.5, spawnPos.getY() , spawnPos.getZ() + 0.5);

            // Set the minecart's yaw based on the rail shape
            if (blockState.getBlock() instanceof RailBlock) {
                RailShape railShape = blockState.get(RailBlock.SHAPE);
                minecart.setYaw(getYawFromRailShape(railShape));
            }

            // spawn the minecart entity in the world
            serverWorld.spawnEntity(minecart);

            // consume one item from the stack if the player is not in creative mode
            if (player == null || !player.isCreative()) {
                stack.decrement(1);
            }
        }

		return ActionResult.SUCCESS;
	}

	private float getYawFromRailShape(RailShape shape) {
        return switch (shape) {
            case EAST_WEST -> 90.0f;
            case NORTH_SOUTH -> 0.0f;
            case ASCENDING_EAST -> 90.0f;
            case ASCENDING_WEST -> 90.0f;
            case ASCENDING_NORTH -> 0.0f;
            case ASCENDING_SOUTH -> 0.0f;
            case SOUTH_EAST -> 45.0f;
            case SOUTH_WEST -> 135.0f;
            case NORTH_WEST -> -135.0f;
            case NORTH_EAST -> -45.0f;
            default -> 0.0f;
        };
    }

}
