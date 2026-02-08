package hdsxwt.better_minecart.entity.render;

import hdsxwt.better_minecart.entity.AcceleratedMinecartEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MinecartEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;

public class AcceleratedMinecartRenderer extends MinecartEntityRenderer{
	
	private static final Identifier TEXTURE = Identifier.of("textures/entity/minecart.png");

	public AcceleratedMinecartRenderer(EntityRendererFactory.Context context) {
		super(context, EntityModelLayers.MINECART);
	}

	public Identifier getTexture(AcceleratedMinecartEntity entity) {
		return TEXTURE;
	}

}
