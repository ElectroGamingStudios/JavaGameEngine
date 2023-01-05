package me.ElectronicsBoy.GameEngine.binary;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class BinaryWriter {
	private OutputStream outputStream;
	private DataOutputStream writer;

	public BinaryWriter(File file) throws Exception {
		this(new FileOutputStream(file));
	}
	
	public BinaryWriter(OutputStream outputStream) throws Exception {
		this.writer = new DataOutputStream(outputStream);
		this.outputStream = outputStream;
	}
	
	public void writeBytes(byte[] data) throws IOException {
		writer.write(data);
	}
	
	public void writeByteArray(byte[] data) throws IOException {
		writeInt(data.length);
		writeBytes(data);
	}

	public void writeInt(int value) throws IOException {
			writer.writeInt(value);
	}
	
	public void writeBoolean(boolean bool) throws IOException{
		writer.writeBoolean(bool);
	}

	public void writeFloat(float value) throws IOException {
		writer.writeFloat(value);
	}

	public void writeLong(long value) throws IOException {
		writer.writeLong(value);
	}
	
	public void writeShort(short value) throws IOException {
		writer.writeShort(value);
	}

	public void writeString(String value) throws IOException {	
		writer.writeUTF(value);
	}

	public byte[] getDataAsBytes() {
		if(outputStream instanceof ByteArrayOutputStream) {
			return ((ByteArrayOutputStream)outputStream).toByteArray();
		}else {
			System.err.println("Trying to get byte array from BinaryWriter that isn't backed up with a byte array.");
			return null;
		}
	}

	public void close() throws IOException {
		writer.close();
	}
	
	public static BinaryWriter openByteArrayWriter() throws Exception {
		return new BinaryWriter(new ByteArrayOutputStream());
	}

}
