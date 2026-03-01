package hdsxwt.better_minecart.entity.AcceleratedMinecartEntities;

import hdsxwt.better_minecart.entity.AcceleratedMinecartEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class StoneMinecart extends AcceleratedMinecartEntity{

	public StoneMinecart(EntityType<?> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public double getMaxSpeed(double currentSpeed) {
		return 2;
	}

	@Override
	public double getAcceleration(double currentSpeed) {
		return 0.02;
	}

	@Override
	public double getDeceleration(double currentSpeed) {
		return 0.04;
	}

}
