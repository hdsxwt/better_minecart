package hdsxwt.better_minecart.item;

import java.util.List;

// import net.minecraft.client.item.TooltipContext;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

public abstract class CartModuleItem extends Item {
    public enum ModuleType { ENGINE, BRAKE }
    
    protected final ModuleType moduleType;
    protected final int powerValue;
    protected final int moduleTier;
    protected final Formatting displayColor;
    protected final String moduleId;
    
    public CartModuleItem(Settings settings, ModuleType type, int power, int tier, Formatting color, String id) {
        super(settings.maxCount(1));
        this.moduleType = type;
        this.powerValue = power;
        this.moduleTier = tier;
        this.displayColor = color;
        this.moduleId = id;
    }
    
    @Override
    public Text getName(ItemStack stack) {
        String translationKey = "item.better_minecart." + moduleId;
        return Text.translatable(translationKey)
            .setStyle(Style.EMPTY.withColor(displayColor.getColorValue()));
    }
    
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        // super.appendTooltip(stack, context, tooltip, context);
		// TODO
		// fix the bug
        
        String statKey = moduleType == ModuleType.ENGINE 
            ? "tooltip.better_minecart.power_value" 
            : "tooltip.better_minecart.resistance_value";
        
        tooltip.add(Text.translatable(statKey, powerValue).formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("tooltip.better_minecart.module_tier", moduleTier).formatted(Formatting.GOLD));
        
        if (moduleType == ModuleType.ENGINE) {
            tooltip.add(Text.translatable("tooltip.better_minecart.engine_desc").formatted(Formatting.DARK_GRAY));
        } else {
            tooltip.add(Text.translatable("tooltip.better_minecart.brake_desc").formatted(Formatting.DARK_GRAY));
        }
    }
    
    public ModuleType getModuleType() { return moduleType; }
    public int getPowerValue() { return powerValue; }
    public int getModuleTier() { return moduleTier; }
    public Formatting getDisplayColor() { return displayColor; }
    public String getModuleId() { return moduleId; }

}