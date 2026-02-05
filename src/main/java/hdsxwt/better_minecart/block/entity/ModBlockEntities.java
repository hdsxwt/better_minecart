package hdsxwt.better_minecart.block.entity;

import hdsxwt.better_minecart.BetterMinecartMod;
import hdsxwt.better_minecart.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.BlockEntityType;

// import net.minecraft.block.entity.BlockEntityType;
// import net.minecraft.registry.Registries;
// import net.minecraft.registry.Registry;
// import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final AssemblyTableBlockEntity ASSEMBLY_TABLE =
        Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            Identifier.of(BetterMinecartMod.MOD_ID, "assembly_table"),
            FabricBlockEntityTypeBuilder.create(
                AssemblyTableBlockEntity::new,
                ModBlocks.ASSEMBLY_TABLE
            ).build()
        );
    
    public static void registerModBlockEntities() {
        BetterMinecartMod.LOGGER.info("注册模组方块实体");
    }
}