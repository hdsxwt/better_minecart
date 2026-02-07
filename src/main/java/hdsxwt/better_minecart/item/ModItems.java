package hdsxwt.better_minecart.item;

import hdsxwt.better_minecart.BetterMinecartMod;
import hdsxwt.better_minecart.entity.ModEntities;
// import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.MinecartItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModItems {
    // 基础物品
    public static final Item ACCELERATED_MINECART = register(
        "accelerated_minecart",
        new MinecartItem(ModEntities.ACCELERATED_MINECART, new Item.Settings().maxCount(1))
    );
    
    public static final Item CHASSIS = register("chassis", new ChassisItem(new Item.Settings()));
    
    // 发动机系列
    public static final EngineModuleItem IRON_ENGINE = registerEngine(
        "iron_engine", EngineModuleItem.createIronEngine()
    );
    
    public static final EngineModuleItem GOLD_ENGINE = registerEngine(
        "gold_engine", EngineModuleItem.createGoldEngine()
    );
    
    public static final EngineModuleItem DIAMOND_ENGINE = registerEngine(
        "diamond_engine", EngineModuleItem.createDiamondEngine()
    );
    
    public static final EngineModuleItem NETHERITE_ENGINE = registerEngine(
        "netherite_engine", EngineModuleItem.createNetheriteEngine()
    );
    
    // 刹车系列
    public static final BrakeModuleItem BASIC_BRAKE = registerBrake(
        "basic_brake", BrakeModuleItem.createBasicBrake()
    );
    
    public static final BrakeModuleItem ADVANCED_BRAKE = registerBrake(
        "advanced_brake", BrakeModuleItem.createAdvancedBrake()
    );
    
    public static final BrakeModuleItem PRECISION_BRAKE = registerBrake(
        "precision_brake", BrakeModuleItem.createPrecisionBrake()
    );
    
    // 注册辅助方法
    private static Item register(String name, Item item) {
        return Registry.register(Registries.ITEM, 
            Identifier.of(BetterMinecartMod.MOD_ID, name), item);
    }
    
    private static EngineModuleItem registerEngine(String name, EngineModuleItem engine) {
        return Registry.register(Registries.ITEM,
            Identifier.of(BetterMinecartMod.MOD_ID, name), engine);
    }
    
    private static BrakeModuleItem registerBrake(String name, BrakeModuleItem brake) {
        return Registry.register(Registries.ITEM,
            Identifier.of(BetterMinecartMod.MOD_ID, name), brake);
    }
    
    // 获取模块列表
    public static List<EngineModuleItem> getAllEngines() {
        return List.of(IRON_ENGINE, GOLD_ENGINE, DIAMOND_ENGINE, NETHERITE_ENGINE);
    }
    
    public static List<BrakeModuleItem> getAllBrakes() {
        return List.of(BASIC_BRAKE, ADVANCED_BRAKE, PRECISION_BRAKE);
    }
    
    public static void registerModItems() {
        BetterMinecartMod.LOGGER.info("注册 {} 个模组物品", 
            getAllEngines().size() + getAllBrakes().size() + 2);
    }
}