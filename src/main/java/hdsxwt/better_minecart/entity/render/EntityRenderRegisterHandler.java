package hdsxwt.better_minecart.entity.render;

import hdsxwt.better_minecart.entity.EntityRegisterHandler;
import net.minecraft.client.render.entity.EntityRendererFactories;

public class EntityRenderRegisterHandler {
	public static void register() {
		
		EntityRendererFactories.register(EntityRegisterHandler.ACCELERATED_MINECART, AcceleratedMinecartRenderer::new);
	}
}