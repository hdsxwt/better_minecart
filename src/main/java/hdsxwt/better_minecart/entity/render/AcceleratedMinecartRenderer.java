package hdsxwt.better_minecart.entity.render;

import hdsxwt.better_minecart.BetterMinecartMod;
import hdsxwt.better_minecart.entity.AcceleratedMinecartEntity;
import net.minecraft.client.render.entity.EntityRendererFactories;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.state.MinecartEntityRenderState;
import net.minecraft.util.Identifier;

public class AcceleratedMinecartRenderer extends AbstractAcceleratedMinecartRenderer<AcceleratedMinecartEntity, MinecartEntityRenderState> {

	public AcceleratedMinecartRenderer(EntityRendererFactory.Context context) {
		super(context, EntityModelLayers.MINECART);
	}

	@Override
	public MinecartEntityRenderState createRenderState() {
		return new MinecartEntityRenderState();
	}

	public static void register() {
		AcceleratedMinecartRenderer.setTexture(Identifier.of(BetterMinecartMod.MOD_ID, "textures/entity/accelerated_minecart.png"));
		EntityRendererFactories.register(AcceleratedMinecartEntity.ACCELERATED_MINECART, AcceleratedMinecartRenderer::new);
	}
}