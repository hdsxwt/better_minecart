package hdsxwt.better_minecart.entity.render;

import hdsxwt.better_minecart.BetterMinecartMod;
import hdsxwt.better_minecart.entity.AcceleratedMinecartEntity;
import hdsxwt.better_minecart.entity.AcceleratedMinecartEntities.CopperMinecart;
import hdsxwt.better_minecart.entity.AcceleratedMinecartEntities.DiamondMinecart;
import hdsxwt.better_minecart.entity.AcceleratedMinecartEntities.GoldenMinecart;
import hdsxwt.better_minecart.entity.AcceleratedMinecartEntities.IronMinecart;
import hdsxwt.better_minecart.entity.AcceleratedMinecartEntities.StoneMinecart;
import hdsxwt.better_minecart.entity.AcceleratedMinecartEntities.WoodMinecart;
import net.minecraft.client.render.entity.EntityRendererFactories;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.state.MinecartEntityRenderState;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;

public class AcceleratedMinecartRenderer extends AbstractAcceleratedMinecartRenderer<AcceleratedMinecartEntity, MinecartEntityRenderState> {


	public final static Identifier DEFAULT_TEXTURE = Identifier.of(BetterMinecartMod.MOD_ID, "textures/entity/accelerated_minecart.png");
	public final static Identifier TEST_TEXTURE = Identifier.of(BetterMinecartMod.MOD_ID, "textures/entity/accelerated_minecart_test.png");
	public final static Identifier WOOD_TEXTURE = Identifier.of(BetterMinecartMod.MOD_ID, "textures/entity/wood_minecart.png");
	public final static Identifier STONE_TEXTURE = Identifier.of(BetterMinecartMod.MOD_ID, "textures/entity/stone_minecart.png");
	public final static Identifier COPPER_TEXTURE = Identifier.of(BetterMinecartMod.MOD_ID, "textures/entity/copper_minecart.png");
	public final static Identifier IRON_TEXTURE = Identifier.of(BetterMinecartMod.MOD_ID, "textures/entity/iron_minecart.png");
	public final static Identifier GOLDEN_TEXTURE = Identifier.of(BetterMinecartMod.MOD_ID, "textures/entity/golden_minecart.png");
	public final static Identifier DIAMOND_TEXTURE = Identifier.of(BetterMinecartMod.MOD_ID, "textures/entity/diamond_minecart.png");
	private Identifier texture;

	public AcceleratedMinecartRenderer(EntityRendererFactory.Context context) {
		super(context, EntityModelLayers.MINECART);
		texture = DEFAULT_TEXTURE;
	}

	public AcceleratedMinecartRenderer(EntityRendererFactory.Context context, Identifier texture) {
		super(context, EntityModelLayers.MINECART);
		this.texture = texture;
	}

	@Override
	public MinecartEntityRenderState createRenderState() {
		return new MinecartEntityRenderState();
	}

	public static void register() {
		register(AcceleratedMinecartEntity.ACCELERATED_MINECART, TEST_TEXTURE);
		register(WoodMinecart.WOOD_MINECART, WOOD_TEXTURE);
		register(StoneMinecart.STONE_MINECART, STONE_TEXTURE);
		register(CopperMinecart.COPPER_MINECART, COPPER_TEXTURE);
		register(IronMinecart.IRON_MINECART, IRON_TEXTURE);
		register(GoldenMinecart.GOLDEN_MINECART, GOLDEN_TEXTURE);
		register(DiamondMinecart.DIAMOND_MINECART, DIAMOND_TEXTURE);
		
	}

	private static void register(EntityType<AcceleratedMinecartEntity> entityType, Identifier texture) {
		EntityRendererFactories.register(entityType,
				(context) -> new AcceleratedMinecartRenderer(context, texture));
	}

	@Override
	public Identifier getTexture() {
		return texture;
	}
}