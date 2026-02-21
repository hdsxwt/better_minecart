package hdsxwt.better_minecart.entity;

import net.minecraft.entity.vehicle.DefaultMinecartController;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public class AcceleratedMinecartController extends DefaultMinecartController {

    private final AcceleratedMinecartEntity minecart;

    public AcceleratedMinecartController(AcceleratedMinecartEntity minecart) {
        super(minecart);
        this.minecart = minecart;
    }

    @Override
    public void moveOnRail(ServerWorld world) {
        super.moveOnRail(world);

        double customSpeed = this.minecart.getCustomSpeed();
        if (Math.abs(customSpeed) < 0.005) return;

        Vec3d vel = this.getVelocity();
        double horizLen = vel.horizontalLength();

        if (horizLen > 1e-6) {
            // 保持 super 给出的方向，替换速度大小
            this.setVelocity(new Vec3d(
                vel.x / horizLen * customSpeed,
                vel.y,
                vel.z / horizLen * customSpeed
            ));
        }
    }
}