package hdsxwt.better_minecart.entity.render;

import hdsxwt.better_minecart.Colors;
import hdsxwt.better_minecart.entity.AcceleratedMinecartEntity;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.text.Text;

public class SpeedHudRenderer implements HudElement {
	// HUD position and size constants
	private static final int HUD_X = 100;
	private static final int HUD_Y = 100;
	private static final int HUD_WIDTH = 120;
	private static final int HUD_HEIGHT = 40;
	
	// color constants
	private static final int BACKGROUND_COLOR = 0x80000000; // semi-transparent black
	private static final int TEXT_COLOR = Colors.WHITE; // white
	private static final int SPEED_COLOR = Colors.GREEN; // green
	private static final int WARNING_COLOR = Colors.RED; // red
	private static final int BORDER_COLOR = Colors.WHITE; // white

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
		
		// background
		drawContext.fill(HUD_X, HUD_Y, 
				HUD_X + HUD_WIDTH, HUD_Y + HUD_HEIGHT, 
				BACKGROUND_COLOR);
						
		
		// border
		drawBorder(drawContext, HUD_X, HUD_Y, HUD_WIDTH, HUD_HEIGHT, 2, BORDER_COLOR);
		
		// title
		drawContext.drawText(textRenderer, 
				Text.translatable("hud.better_minecart.speed"), 
				HUD_X + 5, HUD_Y + 5, 
				TEXT_COLOR, 
				false);
		
		// speed
		double speed = minecart.getVelocity().length();
		int speedColor = speed > 1.5 ? WARNING_COLOR : SPEED_COLOR;
		
		String speedText = String.format("%.2f m/s", speed);
		drawContext.drawText(textRenderer, 
				Text.literal(speedText), 
				HUD_X + 5, HUD_Y + 20, 
				speedColor, 
				false);
		
		// speed bar (max speed 4.0 m/s)
		renderSpeedBar(drawContext, HUD_X + 5, HUD_Y + 35, 
					  Math.min(speed / 4.0, 1.0));
	}

	
	
	private void renderSpeedBar(DrawContext drawContext, int x, int y, double progress) {
		int barWidth = HUD_WIDTH - 10;
		int barHeight = 3;
		
		// background
		drawContext.fill(x, y, x + barWidth, y + barHeight, 0xFF444444);
		
		// fill
		int fillWidth = (int)(barWidth * progress);
		int barColor = getBarColor(progress);
		drawContext.fill(x, y, x + fillWidth, y + barHeight, barColor);
	}
	
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
	
	private int getBarColor(double progress) {
		if (progress < 0.3) return 0xFF00FF00; // 绿色
		if (progress < 0.6) return 0xFFFFFF00; // 黄色
		if (progress < 0.9) return 0xFFFFA500; // 橙色
		return 0xFFFF0000; // 红色
	}

	
}