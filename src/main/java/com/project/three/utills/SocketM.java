package com.project.three.utills;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketImpl;
import java.net.UnknownHostException;

public class SocketM extends Socket{
	private ObjectOutputStream os = null;
	private ObjectInputStream is = null;
	public SocketM (String domain, int port) throws UnknownHostException, IOException {
		super (domain, port);
	}
	public SocketM(SocketImpl socketImpl) throws IOException {
		super(socketImpl);
	}
	public void initialize () throws IOException {
		OutputStream os = this.getOutputStream();
		InputStream is = this.getInputStream();
		this.os = new ObjectOutputStream(os);
		this.is = new ObjectInputStream(is);
	}
	public ObjectOutputStream getObjectOutputStream() {
		return os;
	}
	public void setObjectOutputStream(ObjectOutputStream os) {
		this.os = os;
	}
	public ObjectInputStream getObjectInputStream() {
		return is;
	}
	public void setObjectInputStream(ObjectInputStream is) {
		this.is = is;
	}
	
}
