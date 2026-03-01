package hdsxwt.better_minecart.entity.render;

import hdsxwt.better_minecart.BetterMinecartMod;
import hdsxwt.better_minecart.entity.AcceleratedMinecartEntity;
import net.minecraft.client.render.entity.EntityRendererFactories;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.state.MinecartEntityRenderState;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;

public class AcceleratedMinecartRenderer extends AbstractAcceleratedMinecartRenderer<AcceleratedMinecartEntity, MinecartEntityRenderState> {


	public final static Identifier DEFAULT_TEXTURE = Identifier.of(BetterMinecartMod.MOD_ID, "textures/entity/accelerated_minecart.png");
	public final static Identifier TEST_TEXTURE = Identifier.of(BetterMinecartMod.MOD_ID, "textures/entity/accelerated_minecart_test.png");
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