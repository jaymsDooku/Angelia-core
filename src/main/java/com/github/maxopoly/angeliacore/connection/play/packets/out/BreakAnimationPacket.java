package com.github.maxopoly.angeliacore.connection.play.packets.out;

import com.github.maxopoly.angeliacore.binary.WriteOnlyPacket;

import java.io.IOException;

public class BreakAnimationPacket extends WriteOnlyPacket {

	public BreakAnimationPacket() throws IOException {
		super(0x1D);
		writeVarInt(0);
	}
}
