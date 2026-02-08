package hdsxwt.better_minecart.entity.render;

import hdsxwt.better_minecart.entity.EntityRegisterHandler;
import net.minecraft.client.render.entity.EntityRendererFactories;
import net.minecraft.client.render.entity.MinecartEntityRenderer;

public class EntityRenderRegisterHandler {
	public static void register() {
		// 如果需要自定义实体渲染，在这里注册
		// TODO 
		EntityRendererFactories.register(EntityRegisterHandler.ACCELERATED_MINECART, AcceleratedMinecartRenderer::new);
		// EntityRendererFactories.register(EntityRegisterHandler.ACCELERATED_MINECART, context -> new MinecartEntityRenderer(null, null));
	}
}