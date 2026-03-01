package hdsxwt.better_minecart.entity.render;

import hdsxwt.better_minecart.BetterMinecartMod;
import hdsxwt.better_minecart.Colors;
import hdsxwt.better_minecart.entity.AcceleratedMinecartEntity;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class SpeedHudRenderer implements HudElement {
	// HUD position and size constants
	// TODO make these configurable
	private static final double MAIN_HUD_X = 0.5;
	private static final double MAIN_HUD_Y = 0.8;
	private static final int MAIN_HUD_WIDTH = 120;
	private static final int MAIN_HUD_HEIGHT = 20;
	
	private static final double BAR_HUD_X = 0.95;
	private static final double BAR_HUD_Y = 0.5;
	private static final int BAR_HUD_WIDTH = 10;
	private static final int BAR_HUD_HEIGHT = 150;

	// color constants
	// TODO make these configurable
	private static final int BACKGROUND_COLOR = 0x80000000; // semi-transparent black
	private static final int BORDER_COLOR = Colors.BLACK.toInt();

	// private static final Logger LOGGER = BetterMinecartMod.LOGGER;

	@Override
	public void render(DrawContext context, RenderTickCounter tickCounter) {
	
		MinecraftClient client = MinecraftClient.getInstance();
		
		// check if player is in an AcceleratedMinecartEntity

		if (client.player != null && client.player.getVehicle() instanceof AcceleratedMinecartEntity minecart) {
			renderSpeedHud(context, client, minecart, tickCounter.getDynamicDeltaTicks());
		}
	}
	
	private void renderSpeedHud(DrawContext drawContext, MinecraftClient client, 
								AcceleratedMinecartEntity minecart, float tickDelta) {
		TextRenderer textRenderer = client.textRenderer;

		// calculate HUD position (middle corner)
		int mainHudX = (int)(client.getWindow().getScaledWidth() * MAIN_HUD_X - MAIN_HUD_WIDTH / 2);
		int mainHudY = (int)(client.getWindow().getScaledHeight() * MAIN_HUD_Y - MAIN_HUD_HEIGHT / 2);

		int barHudX = (int)(client.getWindow().getScaledWidth() * BAR_HUD_X - BAR_HUD_WIDTH / 2);
		int barHudY = (int)(client.getWindow().getScaledHeight() * BAR_HUD_Y - BAR_HUD_HEIGHT / 2);
		
		// background and border
		drawBackground(drawContext, mainHudX, mainHudY, MAIN_HUD_WIDTH, MAIN_HUD_HEIGHT, BACKGROUND_COLOR);
		drawBorder(drawContext, mainHudX, mainHudY, MAIN_HUD_WIDTH, MAIN_HUD_HEIGHT, 2, BORDER_COLOR);
		
		// speed
		double speed = minecart.getVelocity().length();
		double maxSpeed = minecart.getMaxSpeed(speed);
		int speedColor = getBarColor(Math.min(speed / maxSpeed, 1.0));
		
		String speedText = Text.translatable("hud.better_minecart.speed").getString() +
					String.format(": %.2f m/s", speed * 20);
		drawContext.drawText(textRenderer, 
				Text.literal(speedText), 
				mainHudX + 5, mainHudY + 5, 
				speedColor, 
				false);
		
		// TODO add configurable max speed
		// speed bar (max speed 4.0 m/s)
		drawBackground(drawContext, barHudX, barHudY, BAR_HUD_WIDTH, BAR_HUD_HEIGHT, BACKGROUND_COLOR);
		drawBorder(drawContext, barHudX, barHudY, BAR_HUD_WIDTH, BAR_HUD_HEIGHT, 2, BORDER_COLOR);
		drawSpeedBar(drawContext, barHudX, barHudY, Math.min(speed / maxSpeed, 1.0), speedColor);
	}

	
	/**
	 * Renders a horizontal speed bar based on the current speed progress (0.0 to 1.0).
	 * The bar fills from left to right, changing color based on the speed level.
	 * @param drawContext
	 * @param x
	 * @param y
	 * @param progress
	 */
	private void drawSpeedBar(DrawContext drawContext, int x, int y, double progress, int color) {
		int fillHeight = (int)(BAR_HUD_HEIGHT * progress);
		drawContext.fill(x, y + BAR_HUD_HEIGHT - fillHeight, x + BAR_HUD_WIDTH, y + BAR_HUD_HEIGHT, color);
	}

	/**
	 * Draws a filled rectangle as the background for the HUD element.
	 * @param drawContext
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param color
	 */
	private void drawBackground(DrawContext drawContext, int x, int y, int width, int height, int color) {
			drawContext.fill(x, y, x + width, y + height, color);
	}
	/**
	 * Draws a border around the HUD element with specified thickness and color.
	 * @param drawContext
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param thickness
	 * @param color
	 */
	private void drawBorder (DrawContext drawContext, int x, int y, int width, int height, int thickness, int color) {
			// upper border
			drawContext.fill(x - thickness, y - thickness, x + width + thickness, y, color);
			// bottom border
			drawContext.fill(x - thickness, y + height, x + width + thickness, y + height + thickness, color);
			// left border
			drawContext.fill(x - thickness, y, x, y + height, color);
			// right border
			drawContext.fill(x + width, y, x + width + thickness, y + height, color);
	}
	
	/**
	 * Determines the color of the speed bar based on the current speed progress.
	 * The color transitions from green (slow) to red (fast) as progress increases.
	 * @param progress Speed progress (0.0 to 1.0)
	 * @return ARGB color as an integer
	 */
	private int getBarColor(double progress) {
		Colors color = new Colors(255, (int)(255 * progress), (int)(255 * (1.0 - progress)), 0); // green to red
		return color.toInt();
	}
	
	public static void register() {
		SpeedHudRenderer speedHudRenderer = new SpeedHudRenderer();
		Identifier hudElementId = Identifier.of(BetterMinecartMod.MOD_ID, "speed_hud");
		
		HudElementRegistry.attachElementBefore(
			VanillaHudElements.CHAT,
			hudElementId,
			speedHudRenderer
		);
	}
}