package hdsxwt.better_minecart.block;

import hdsxwt.better_minecart.BetterMinecartMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block MESSAGE_RAIL = registerBlock(
        "message_rail",
        new MessageRailBlock(
            FabricBlockSettings.copyOf(Blocks.RAIL)
                .sounds(BlockSoundGroup.METAL)
                .strength(0.7f)
        )
    );
    
    public static final Block ASSEMBLY_TABLE = registerBlock(
        "assembly_table",
        new AssemblyTableBlock(
            FabricBlockSettings.copyOf(Blocks.CRAFTING_TABLE)
                .sounds(BlockSoundGroup.WOOD)
                .strength(2.0f)
        )
    );
    
    private static Block registerBlock(String name, Block block) {
        Block registeredBlock = Registry.register(
            Registries.BLOCK,
            Identifier.of(BetterMinecartMod.MOD_ID, name),
            block
        );
        
        Registry.register(
            Registries.ITEM,
            Identifier.of(BetterMinecartMod.MOD_ID, name),
            new BlockItem(registeredBlock, new FabricItemSettings())
        );
        
        return registeredBlock;
    }
    
    public static void registerModBlocks() {
        BetterMinecartMod.LOGGER.info("注册模组方块");
    }
}