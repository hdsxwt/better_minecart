package hdsxwt.better_minecart.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class BrakeModuleItem extends CartModuleItem {
    private final float controlFactor;
    
    public BrakeModuleItem(Settings settings, int resistanceValue, int tier, Formatting color, String id, float controlFactor) {
        super(settings, ModuleType.BRAKE, resistanceValue, tier, color, id);
        this.controlFactor = controlFactor;
    }
    
    public float getControlFactor() { return controlFactor; }
    
    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(Text.translatable("tooltip.better_minecart.control_factor", 
            String.format("%.1f", controlFactor)).formatted(Formatting.BLUE));
    }
    
    public static BrakeModuleItem createBasicBrake() {
        return new BrakeModuleItem(
            new Item.Settings(),
            40, 1, Formatting.WHITE, "basic_brake", 1.0f
        );
    }
    
    public static BrakeModuleItem createAdvancedBrake() {
        return new BrakeModuleItem(
            new Item.Settings(),
            65, 2, Formatting.YELLOW, "advanced_brake", 1.3f
        );
    }
    
    public static BrakeModuleItem createPrecisionBrake() {
        return new BrakeModuleItem(
            new Item.Settings(),
            85, 3, Formatting.GREEN, "precision_brake", 1.7f
        );
    }
}