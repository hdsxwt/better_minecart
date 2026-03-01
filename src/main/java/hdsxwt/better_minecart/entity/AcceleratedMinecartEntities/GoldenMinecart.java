package hdsxwt.better_minecart.entity.AcceleratedMinecartEntities;

import hdsxwt.better_minecart.entity.AcceleratedMinecartEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class GoldenMinecart extends AcceleratedMinecartEntity{

	public GoldenMinecart(EntityType<?> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public double getMaxSpeed(double currentSpeed) {
		return 7;
	}

	@Override
	public double getAcceleration(double currentSpeed) {
		return 0.07;
	}

	@Override
	public double getDeceleration(double currentSpeed) {
		return 0.14;
	}

}
