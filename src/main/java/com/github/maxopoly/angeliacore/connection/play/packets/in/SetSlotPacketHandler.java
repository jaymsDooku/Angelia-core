package com.github.maxopoly.angeliacore.connection.play.packets.in;

import com.github.maxopoly.angeliacore.connection.ServerConnection;
import com.github.maxopoly.angeliacore.event.events.UpdateInventorySlotEvent;
import com.github.maxopoly.angeliacore.model.inventory.Inventory;
import com.github.maxopoly.angeliacore.model.item.ItemStack;
import com.github.maxopoly.angeliacore.packet.EndOfPacketException;
import com.github.maxopoly.angeliacore.packet.ReadOnlyPacket;

public class SetSlotPacketHandler extends AbstractIncomingPacketHandler {

	public SetSlotPacketHandler(ServerConnection connection) {
		super(connection, 0x16);
	}

	@Override
	public void handlePacket(ReadOnlyPacket packet) {
		try {
			byte windowID = packet.readUnsignedByte();
			short slot = packet.readSignedShort();
			ItemStack is = packet.readItemStack();
			Inventory inv = connection.getPlayerStatus().getInventory(windowID);
			if (inv != null && slot >= 0 && slot < inv.getSize()) {
				connection.getEventHandler().broadcast(new UpdateInventorySlotEvent(inv, windowID, slot, is));
				inv.updateSlot(slot, is);
			}
		} catch (EndOfPacketException e) {
			connection.getLogger().error("Failed to parse set slot packet", e);
		}

	}

}
