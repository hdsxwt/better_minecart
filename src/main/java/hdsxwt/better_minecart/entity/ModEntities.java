package hdsxwt.better_minecart.entity;

import hdsxwt.better_minecart.BetterMinecartMod;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<AcceleratedMinecartEntity> ACCELERATED_MINECART = 
        Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(BetterMinecartMod.MOD_ID, "accelerated_minecart"),
            FabricEntityTypeBuilder.<AcceleratedMinecartEntity>create(
                    SpawnGroup.MISC, 
                    AcceleratedMinecartEntity::new
                )
                .dimensions(EntityDimensions.fixed(0.98f, 0.7f))
                .trackRangeChunks(8)
                .build()
        );
    
    public static void registerModEntities() {
        BetterMinecartMod.LOGGER.info("注册模组实体");
    }
}