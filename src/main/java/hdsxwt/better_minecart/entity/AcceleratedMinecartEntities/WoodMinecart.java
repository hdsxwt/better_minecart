package hdsxwt.better_minecart.entity.AcceleratedMinecartEntities;

import hdsxwt.better_minecart.entity.AcceleratedMinecartEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class WoodMinecart extends AcceleratedMinecartEntity{

	public WoodMinecart(EntityType<?> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public double getMaxSpeed(double currentSpeed) {
		return 1;
	}

	@Override
	public double getAcceleration(double currentSpeed) {
		return 0.01;
	}

	@Override
	public double getDeceleration(double currentSpeed) {
		return 0.02;
	}

}
