package hdsxwt.better_minecart.item;

import hdsxwt.better_minecart.BetterMinecartMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public abstract class ItemRegisterHandler {

	public static void register() {
		BetterMinecartMod.LOGGER.info(BetterMinecartMod.MESSAGE_HEAD_STRING + "registering mod items");
		
		AcceleratedMinecartItem.register();
		registerAcceleratedMinecartItemGroup();
		
		BetterMinecartMod.LOGGER.info(BetterMinecartMod.MESSAGE_HEAD_STRING + "registering mod items complete");
	}

	

	public static final RegistryKey<ItemGroup> BETTER_MINECART_GROUP = RegistryKey.of(
		RegistryKeys.ITEM_GROUP,
		Identifier.of(BetterMinecartMod.MOD_ID, "better_minecart_group")
	);

	private static void registerAcceleratedMinecartItemGroup() {
		Registry.register(Registries.ITEM_GROUP, BETTER_MINECART_GROUP,
            FabricItemGroup.builder()
                .displayName(Text.translatable("itemGroup.better_minecart.better_minecart_group"))
                .icon(() -> new ItemStack(AcceleratedMinecartItem.ACCELERATED_MINECART))
                .entries((context, entries) -> AcceleratedItemGroup.getGroupEntries().forEach(entries::add))
                .build()
        );
	}
}