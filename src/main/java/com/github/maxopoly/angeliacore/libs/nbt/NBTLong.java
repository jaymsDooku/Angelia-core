package com.github.maxopoly.angeliacore.libs.nbt;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class NBTLong extends NBTElement {

	public static final byte ID = 4;

	private long value;

	public NBTLong(String name, long value) {
		super(name);
		this.value = value;
	}

	public long getValue() {
		return value;
	}

	@Override
	public byte[] serializeContent() {
		byte[] bytes = new byte[8];
		ByteBuffer.wrap(bytes).order(ByteOrder.BIG_ENDIAN).putLong(value);
		return bytes;
	}

	@Override
	public byte getID() {
		return ID;
	}

	@Override
	public boolean equals(Object o) {
		return o instanceof NBTLong && ((NBTLong) o).value == value;
	}

	@Override
	public NBTElement clone() {
		return new NBTLong(name, value);
	}


	@Override
	public String toString() {
		return "" + value;
	}

	@Override
	public String getTypeName() {
		return "long";
	}
}
