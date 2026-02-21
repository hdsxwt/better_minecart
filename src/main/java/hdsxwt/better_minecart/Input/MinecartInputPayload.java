package hdsxwt.better_minecart.Input;

import hdsxwt.better_minecart.entity.AcceleratedMinecartEntity;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record MinecartInputPayload(boolean forward, boolean backward) implements CustomPayload {

    public static final CustomPayload.Id<MinecartInputPayload> ID =
            new CustomPayload.Id<>(Identifier.of("yourmod", "minecart_input"));

    public static final PacketCodec<PacketByteBuf, MinecartInputPayload> CODEC =
            PacketCodec.tuple(
                    PacketCodecs.BOOLEAN, MinecartInputPayload::forward,
                    PacketCodecs.BOOLEAN, MinecartInputPayload::backward,
                    MinecartInputPayload::new
            );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return ID;
    }

	public static void registerForServer() {
		PayloadTypeRegistry.playC2S().register(MinecartInputPayload.ID, MinecartInputPayload.CODEC);

		ServerPlayNetworking.registerGlobalReceiver(MinecartInputPayload.ID, (payload, context) -> {
			context.server().execute(() -> {
				if (context.player().getVehicle() instanceof AcceleratedMinecartEntity minecart) {
					minecart.setInput(payload.forward(), payload.backward());
				}
			});
		});
	}

	public static void registerForClient() {
		// ClientModInitializer
		// PayloadTypeRegistry.playC2S().register(MinecartInputPayload.ID, MinecartInputPayload.CODEC);

		boolean[] last = {false, false};

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (client.player == null) return;
			if (!(client.player.getVehicle() instanceof AcceleratedMinecartEntity)) return;

			boolean forward  = client.options.forwardKey.isPressed();
			boolean backward = client.options.backKey.isPressed();

			if (forward != last[0] || backward != last[1]) {
				ClientPlayNetworking.send(new MinecartInputPayload(forward, backward));
				last[0] = forward;
				last[1] = backward;
			}
		});
	}
}