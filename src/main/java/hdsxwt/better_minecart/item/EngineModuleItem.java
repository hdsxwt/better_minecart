package hdsxwt.better_minecart.item;

import net.minecraft.item.Item;
import net.minecraft.util.Formatting;

public class EngineModuleItem extends CartModuleItem {
    public EngineModuleItem(Settings settings, int powerValue, int tier, Formatting color, String id) {
        super(settings, ModuleType.ENGINE, powerValue, tier, color, id);
    }
    
    public static EngineModuleItem createIronEngine() {
        return new EngineModuleItem(
            new Item.Settings(),
            35, 1, Formatting.GRAY, "iron_engine"
        );
    }
    
    public static EngineModuleItem createGoldEngine() {
        return new EngineModuleItem(
            new Item.Settings(),
            55, 2, Formatting.GOLD, "gold_engine"
        );
    }
    
    public static EngineModuleItem createDiamondEngine() {
        return new EngineModuleItem(
            new Item.Settings(),
            80, 3, Formatting.AQUA, "diamond_engine"
        );
    }
    
    public static EngineModuleItem createNetheriteEngine() {
        return new EngineModuleItem(
            new Item.Settings().fireproof(),
            100, 4, Formatting.DARK_RED, "netherite_engine"
        );
    }
}