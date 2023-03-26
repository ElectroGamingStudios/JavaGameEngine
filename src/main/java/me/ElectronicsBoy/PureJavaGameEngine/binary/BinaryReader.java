package me.ElectronicsBoy.PureJavaGameEngine.binary;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class BinaryReader {

	private DataInputStream reader;

	public BinaryReader(File file) {
		try {
			reader = new DataInputStream(new FileInputStream(file));
		} catch (Exception e) {
			System.err.println("Couldn't open read file " + file);
		}
	}

	public BinaryReader(FileInputStream fis) {
		try {
			reader = new DataInputStream(fis);
		} catch (Exception e) {
			System.err.println("Couldn't open read file");
		}
	}
	
	public BinaryReader(byte[] data) {
		try {
			reader = new DataInputStream(new ByteArrayInputStream(data));
		} catch (Exception e) {
			System.err.println("Couldn't open data stream");
		}
	}

	public void skipBytes(int byteCount) throws Exception {
		long skippedBytes = reader.skip(byteCount);
		if (skippedBytes < byteCount) {
			throw new Exception();
		}
	}
	
	public void skipByteArray() throws Exception {
		int count = readInt();
		skipBytes(count);
	}
	
	public byte[] readByteArray() throws Exception {
		int byteCount = readInt();
		return readBytes(byteCount);
	}

	public int readInt() throws Exception {
		return reader.readInt();
	}

	public float readFloat() throws Exception {
		return reader.readFloat();
	}

	public boolean readBoolean() throws Exception {
		return reader.readBoolean();
	}

	public String readString() throws Exception {
		return reader.readUTF();
	}

	public long readLong() throws Exception {
		return reader.readLong();
	}

	public short readShort() throws Exception {
		return reader.readShort();
	}

	public byte[] readBytes(int count) throws Exception {
		byte[] data = new byte[count];
		reader.readFully(data);
		return data;
	}

	public void close() {
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean available() throws IOException {
		return reader.available() > 0;
	}
}
