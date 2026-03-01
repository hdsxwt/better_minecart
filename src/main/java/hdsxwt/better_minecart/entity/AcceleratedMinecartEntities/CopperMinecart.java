package hdsxwt.better_minecart.entity.AcceleratedMinecartEntities;

import hdsxwt.better_minecart.entity.AcceleratedMinecartEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class CopperMinecart extends AcceleratedMinecartEntity{

	public CopperMinecart(EntityType<?> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public double getMaxSpeed(double currentSpeed) {
		return 3;
	}

	@Override
	public double getAcceleration(double currentSpeed) {
		return 0.03;
	}

	@Override
	public double getDeceleration(double currentSpeed) {
		return 0.06;
	}

}
