package hdsxwt.better_minecart.entity;

import hdsxwt.better_minecart.BetterMinecartMod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.MinecartEntity;
import net.minecraft.world.World;

public class AcceleratedMinecartEntity extends MinecartEntity{

	public AcceleratedMinecartEntity(EntityType<?> entityType, World world) {
		super(entityType, world);
		BetterMinecartMod.LOGGER.info(BetterMinecartMod.MESSAGE_HEAD_STRING + "AcceleratedMinecartEntity initialized");
	}
	
}
