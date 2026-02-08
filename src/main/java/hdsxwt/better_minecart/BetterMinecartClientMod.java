package hdsxwt.better_minecart;

import net.fabricmc.api.ClientModInitializer;

public class BetterMinecartClientMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BetterMinecartMod.LOGGER.info("正在初始化 Better Minecart Mod (1.21.11)");
        // 如果需要自定义实体渲染，在这里注册
        // EntityRendererRegistry.register(ModEntities.ACCELERATED_MINECART, AcceleratedMinecartRenderer::new);
        
        BetterMinecartMod.LOGGER.info("Better Minecart Mod 客户端初始化完成");
    }
}