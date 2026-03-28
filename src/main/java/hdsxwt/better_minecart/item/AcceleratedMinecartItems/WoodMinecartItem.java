package hdsxwt.better_minecart.item.AcceleratedMinecartItems;

import hdsxwt.better_minecart.BetterMinecartMod;
import hdsxwt.better_minecart.entity.AcceleratedMinecartEntity;
import hdsxwt.better_minecart.entity.AcceleratedMinecartEntities.WoodMinecart;
import hdsxwt.better_minecart.item.AcceleratedItemGroup;
import hdsxwt.better_minecart.item.AcceleratedMinecartItem;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class WoodMinecartItem extends AcceleratedMinecartItem{

	public WoodMinecartItem(Settings settings) {
		super(settings);
	}

	
	@Override
	public EntityType<AcceleratedMinecartEntity> getMinecart() {
		return WoodMinecart.WOOD_MINECART;
	}

	public static void register() {
		ACCELERATED_MINECART_KEY = RegistryKey.of(
				Registries.ITEM.getKey(),
				Identifier.of(BetterMinecartMod.MOD_ID, "wood_minecart")
		);

		ACCELERATED_MINECART_SETTINGS = new Item
				.Settings()
				.maxCount(1)
				.registryKey(ACCELERATED_MINECART_KEY);

		ACCELERATED_MINECART = Registry.register(Registries.ITEM,
				Identifier.of(BetterMinecartMod.MOD_ID, "wood_minecart"),
				new WoodMinecartItem(ACCELERATED_MINECART_SETTINGS)
		);

		AcceleratedItemGroup.addToGroup(ACCELERATED_MINECART);
	}

}
