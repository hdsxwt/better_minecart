package hdsxwt.better_minecart;

import hdsxwt.better_minecart.screen.ModScreenHandlers;
import hdsxwt.better_minecart.screen.AssemblyTableScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class BetterMinecartClientMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // 注册装配台屏幕
        HandledScreens.register(ModScreenHandlers.ASSEMBLY_TABLE, AssemblyTableScreen::new);
        
        // 如果需要自定义实体渲染，在这里注册
        // EntityRendererRegistry.register(ModEntities.ACCELERATED_MINECART, AcceleratedMinecartRenderer::new);
        
        BetterMinecartMod.LOGGER.info("Better Minecart Mod 客户端初始化完成");
    }
}