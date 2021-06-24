package manred.topo.server.framework.net;

import com.google.protobuf.MessageLite;

public class BaseMessage{
	private short packetId;
	private byte[] bytes;
	private MessageLite lite;
	
	
	public BaseMessage(short messageId, byte[] bytes) {
		this.packetId = messageId;
		this.bytes = bytes;
	}

	public BaseMessage(short messageId, MessageLite lite) {
		super();
		this.packetId = messageId;
		this.lite = lite;
	}
	
	public short getPacketId() {
		return packetId;
	}
	public void setPacketId(short messageId) {
		this.packetId = messageId;
	}
	
	public MessageLite getLite() {
		return lite;
	}
	public void setLite(MessageLite lite) {
		this.lite = lite;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	
	
}
