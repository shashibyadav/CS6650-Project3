package com.project.three.server;

import com.project.three.server.ProjectEnums.ConnectionType;
import com.project.three.server.ProjectEnums.ServiceKeys;
import com.project.three.utills.Utills;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerConfig {
	private int tcpPort = -1;
	private int udpPort = -1;
	private int rpcPort = -1;
	private int clientUDPPort = -1;
	private String serverHost = "localhost";
	private String clientHost = "localhost";
	private ConnectionType type = ConnectionType.TCP;
	private ExecutorService executor = Executors.newCachedThreadPool();
	private int requestTimeout = 0;
	private int udpPacketLength = 65535;
	private boolean logs = false;
	private ServiceKeys ServiceKey = null;
	private boolean hasCoordinator = false;

	public int getTcpPort() {
		return tcpPort;
	}

	public void setTcpPort(int tcpPort) {
		this.tcpPort = tcpPort;
	}

	public int getUdpPort() {
		return udpPort;
	}

	public void setUdpPort(int udpPort) {
		this.udpPort = udpPort;
	}

	public int getClientUDPPort() {
		return clientUDPPort;
	}

	public void setClientUDPPort(int clientUDPPort) {
		this.clientUDPPort = clientUDPPort;
	}

	public String getServerHost() {
		return serverHost;
	}

	public void setServerHost(String serverHost) {
		this.serverHost = (Utills.isEmptyString(serverHost)) ? "localhost" : serverHost;
	}

	public String getClientHost() {
		return clientHost;
	}

	public void setClientHost(String clientHost) {
		this.clientHost = (Utills.isEmptyString(clientHost)) ? "localhost" : clientHost;
	}

	public ConnectionType getType() {
		return type;
	}

	public void setType(ConnectionType type) {
		this.type = type;
	}

	public ExecutorService getExecutor() {
		return executor;
	}

	public void setExecutor(ExecutorService executor) {
		this.executor = (executor != null) ? executor : this.executor;
	}

	public int getRequestTimeout() {
		return requestTimeout;
	}

	public void setRequestTimeout(int requestTimeout) {
		this.requestTimeout = (requestTimeout < this.requestTimeout) ? this.requestTimeout : requestTimeout;
	}

	public boolean isLogs() {
		return logs;
	}

	public void setLogs(boolean logs) {
		this.logs = logs;
	}

	public int getUdpPacketLength() {
		return udpPacketLength;
	}

	public void setUdpPacketLength(int udpPacketLength) {
		this.udpPacketLength = (udpPacketLength < this.udpPacketLength) ? this.udpPacketLength : udpPacketLength;
	}

	public int getRpcPort() {
		return rpcPort;
	}

	public void setRpcPort(int rpcPort) {
		this.rpcPort = rpcPort;
	}

	public ServiceKeys getServiceKey() {
		return ServiceKey;
	}

	public void setServiceKey(ServiceKeys serviceKey) {
		ServiceKey = serviceKey;
	}

	public boolean isHasCoordinator() {
		return hasCoordinator;
	}

	public void setHasCoordinator(boolean hasCoordinator) {
		this.hasCoordinator = hasCoordinator;
	}
}
