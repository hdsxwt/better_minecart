package hdsxwt.better_minecart.entity.AcceleratedMinecartEntities;

import hdsxwt.better_minecart.entity.AcceleratedMinecartEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class IronMinecart extends AcceleratedMinecartEntity{

	public IronMinecart(EntityType<?> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public double getMaxSpeed(double currentSpeed) {
		return 5;
	}

	@Override
	public double getAcceleration(double currentSpeed) {
		return 0.05;
	}

	@Override
	public double getDeceleration(double currentSpeed) {
		return 0.1;
	}

}