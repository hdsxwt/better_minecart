package hdsxwt.better_minecart.entity.render;

import hdsxwt.better_minecart.BetterMinecartMod;
import hdsxwt.better_minecart.entity.EntityRegisterHandler;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.render.entity.EntityRendererFactories;
import net.minecraft.util.Identifier;


public abstract class EntityRenderRegisterHandler {
	public static void register() {
		RegisterEntityRendererFactories();
		RegisterSpeedHudRenderer();
	}

	private static void RegisterEntityRendererFactories() {
		AcceleratedMinecartRenderer.setTexture(Identifier.ofVanilla("textures/entity/endermite.png"));
		EntityRendererFactories.register(EntityRegisterHandler.ACCELERATED_MINECART, AcceleratedMinecartRenderer::new);
	}

	private static void RegisterSpeedHudRenderer() {
		SpeedHudRenderer speedHudRenderer = new SpeedHudRenderer();
		Identifier hudElementId = Identifier.of(BetterMinecartMod.MOD_ID, "speed_hud");
		
		// 注册 HUD 元素 - 将其放置在聊天层之前
		HudElementRegistry.attachElementBefore(
			VanillaHudElements.CHAT,
			hudElementId,
			speedHudRenderer
		);
	}
}