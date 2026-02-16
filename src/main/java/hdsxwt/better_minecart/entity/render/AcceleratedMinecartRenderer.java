package hdsxwt.better_minecart.entity.render;

import hdsxwt.better_minecart.entity.AcceleratedMinecartEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.state.MinecartEntityRenderState;

public class AcceleratedMinecartRenderer extends AbstractAcceleratedMinecartRenderer<AcceleratedMinecartEntity, MinecartEntityRenderState> {

	public AcceleratedMinecartRenderer(EntityRendererFactory.Context context) {
		super(context, EntityModelLayers.MINECART);
	}

	@Override
	public MinecartEntityRenderState createRenderState() {
		return new MinecartEntityRenderState();
	}
}