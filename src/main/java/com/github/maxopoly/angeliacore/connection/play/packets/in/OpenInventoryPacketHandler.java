package com.github.maxopoly.angeliacore.connection.play.packets.in;

import com.github.maxopoly.angeliacore.connection.ServerConnection;
import com.github.maxopoly.angeliacore.event.events.OpenInventoryEvent;
import com.github.maxopoly.angeliacore.model.inventory.Inventory;
import com.github.maxopoly.angeliacore.packet.EndOfPacketException;
import com.github.maxopoly.angeliacore.packet.ReadOnlyPacket;
import com.github.maxopoly.angeliacore.util.ChatParser;

public class OpenInventoryPacketHandler extends AbstractIncomingPacketHandler {

	public OpenInventoryPacketHandler(ServerConnection connection) {
		super(connection, 0x13);
	}

	@Override
	public void handlePacket(ReadOnlyPacket packet) {
		try {
			byte windowID = packet.readUnsignedByte();
			String windowType = packet.readString();
			String name = ChatParser.getRawText(packet.readString());
			byte numberOfSlots = packet.readUnsignedByte();
			Inventory inv = Inventory.constructInventory(windowType, name, numberOfSlots);
			if (inv != null) {
				connection.getEventHandler().broadcast(new OpenInventoryEvent(inv, windowID));
				connection.getPlayerStatus().addInventory(inv, windowID);
			}
		} catch (EndOfPacketException e) {
			connection.getLogger().error("Failed to parse open inventory packet", e);
		}

	}
}
