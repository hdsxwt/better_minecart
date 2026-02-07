// package hdsxwt.better_minecart.item;

// import net.fabricmc.api.EnvType;
// import net.fabricmc.api.Environment;
// import net.minecraft.component.type.TooltipDisplayComponent;
// import net.minecraft.item.Item;
// import net.minecraft.item.ItemStack;
// import net.minecraft.item.tooltip.TooltipType;
// import net.minecraft.text.Text;

// import java.util.function.Consumer;

// public abstract class AbstractItem extends Item {
//     public AbstractItem(Settings settings) {
//         super(settings);
//     }
    
//     // @Override
//     // @Environment(EnvType.CLIENT) // 标记为仅客户端
//     // public void appendTooltip(ItemStack stack, TooltipContext context, 
//     //                           List<Text> tooltip, TooltipType type) {
//     //     super.appendTooltip(stack, context, tooltip, type);
//     //     appendCustomTooltip(stack, context, tooltip, type);
//     // }

//     @Override
//     @Environment(EnvType.CLIENT) // 标记为仅客户端
//     public void appendTooltip(ItemStack stack, Item.TooltipContext context,
// 			TooltipDisplayComponent displayComponent,Consumer<Text> textConsumer, TooltipType type) {
//         super.appendTooltip(stack, context, displayComponent, textConsumer, type);
//         appendCustomTooltip(stack, context, displayComponent, textConsumer, type);
//     }

//     @Environment(EnvType.CLIENT) // 标记为仅客户端
//     protected abstract void appendCustomTooltip(ItemStack stack, Item.TooltipContext context,
// 			TooltipDisplayComponent displayComponent,Consumer<Text> textConsumer, TooltipType type
//     );
// }