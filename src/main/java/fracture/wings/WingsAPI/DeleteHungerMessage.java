package fracture.wings.WingsAPI;

import fracture.fracture;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.fabricmc.fabric.api.network.PacketContext;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public class DeleteHungerMessage
{
	public static final Identifier ID = new Identifier(fracture.MOD_ID, "delete_hunger");

	public static void send()
	{
		PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
		ClientSidePacketRegistry.INSTANCE.sendToServer(ID, buf);
	}

	public static void handle(PacketContext context, PacketByteBuf buf)
	{
		context.getTaskQueue().submit(new Runnable()
		{
			@Override
			public void run()
			{
				context.getPlayer().getHungerManager().addExhaustion(WingConfig.exhaustionAmount);
			}
		});
	}
}
