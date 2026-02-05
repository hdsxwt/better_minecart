package hdsxwt.better_minecart.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.RailBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MessageRailBlock extends RailBlock {
    public MessageRailBlock(Settings settings) {
        super(settings);
    }
    
    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!world.isClient() && entity.getWorld().getTime() % 20 == 0) {
            if (entity instanceof AbstractMinecartEntity) {
                AbstractMinecartEntity minecart = (AbstractMinecartEntity) entity;
                
                minecart.getPassengerList().forEach(passenger -> {
                    if (passenger instanceof PlayerEntity) {
                        PlayerEntity player = (PlayerEntity) passenger;
                        
                        double speed = Math.sqrt(
                            entity.getVelocity().x * entity.getVelocity().x +
                            entity.getVelocity().z * entity.getVelocity().z
                        );
                        
                        String message = String.format(
                            "§6[消息铁轨] §f位置: §a%d, %d, %d §f| 速度: §b%.2f§f m/s",
                            pos.getX(), pos.getY(), pos.getZ(), speed
                        );
                        
                        player.sendMessage(net.minecraft.text.Text.literal(message), false);
                    }
                });
            }
        }
        super.onEntityCollision(state, world, pos, entity);
    }
}