package hdsxwt.better_minecart.entity.AcceleratedMinecartEntities;

import hdsxwt.better_minecart.entity.AcceleratedMinecartEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class DiamondMinecart extends AcceleratedMinecartEntity{

	public DiamondMinecart(EntityType<?> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public double getMaxSpeed(double currentSpeed) {
		return 10;
	}

	@Override
	public double getAcceleration(double currentSpeed) {
		return 0.1;
	}

	@Override
	public double getDeceleration(double currentSpeed) {
		return 0.2;
	}
}
