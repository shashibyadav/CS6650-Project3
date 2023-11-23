package com.project.three;

import com.project.three.client.Client;
import com.project.three.client.ClientConfig;
import com.project.three.server.ProjectEnums.ConnectionType;
import com.project.three.server.ProjectEnums.ServiceKeys;
import com.project.three.utills.BusinessException;
import com.project.three.utills.Utills;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientRun {

	public static boolean dummyData(Client client) throws IOException, ClassNotFoundException {
		client.putData("Name1", "Shashi Bhushan Yadav");
//		client.putData("Name1", "Shashi Bhushan Yadav");
		client.putData("Name2", "Priyanka Salla");
		client.putData("Name3", "Dhruvil Jhala");
		client.putData("Name4", "Pratyusha Parashar");
		client.putData("Name5", "Amritanj Ayush");
//
		client.getData("Name1");
		client.getData("Name2");
		client.getData("Name3");
		client.getData("Name4");
		client.getData("Name5");
		client.initiateGlobalState();
//
		client.deleteData("Name1");
		client.deleteData("Name2");
		client.deleteData("Name3");
		client.deleteData("Name4");
		client.deleteData("Name5");
//
		client.getData("Name1");
		client.getData("Name2");
		client.getData("Name3");
		client.getData("Name4");
		client.getData("Name5");
		return true;
	}

	public static void printOptions() {
		System.out.println();
		System.out.println("Enter one of the code to perform actions");
		System.out.println("1. GET");
		System.out.println("2. PUT");
		System.out.println("3. DELETE");
		System.out.println("4. LOG STATES");
		System.out.println("5. CLOSE SERVER and EXIT");
		System.out.println("6. EXIT");
		System.out.println();
	}

	private static void window(Client client) throws Exception {
		boolean loop = true;
		BufferedReader reader = null;
		while (loop) {
			reader = new BufferedReader(new InputStreamReader(System.in));
			ClientRun.printOptions();
			int action = Integer.parseInt(reader.readLine());
			String key = null, value = null, request = null;
			switch (action) {
			case 1:
				System.out.println("Enter key to fetch");
				request = reader.readLine();
				client.getData(request);
				break;
			case 2:
				System.out.println("Enter key");
				key = reader.readLine();
				System.out.println("Enter value");
				value = reader.readLine();
				client.putData(key, value);
				break;
			case 3:
				System.out.println("Enter key to delete");
				key = reader.readLine();
				client.deleteData(key);
				break;
			case 4:
				client.initiateGlobalState();
				System.out.println("States Logged Check Individual Server logs");
				break;
			case 5:
				loop = false;
				client.stopServerCall(false);
				client.stopClient();
				break;
			default:
				loop = false;
				client.stopClient();
				break;
			}

		}
	}

	public static void run(String[] args) throws Exception {
		ConnectionType type = Enum.valueOf(ConnectionType.class, args[0]);
		String serverHost = (args.length >= 2) ? args[1] : null;
		int serverPort = (args.length >= 3) ? Integer.parseInt(args[2]) : -1;
		ClientConfig tcpCConfig = new ClientConfig();
		String clientHost = null;
		int clientPort = -1, registryPort = -1;
		String registryHost = null;
		ServiceKeys serviceKey = null;
		int numberOfThreads = 1;
		if (type == ConnectionType.TCP) {

		} else if (type == ConnectionType.UDP && args.length > 3) {
			clientHost = (args.length >= 4) ? args[3] : null;
			clientPort = (args.length >= 5) ? Integer.parseInt(args[4]) : -1;
		} else if (type == ConnectionType.RPC) {
			registryHost = serverHost;
			registryPort = serverPort;
			if (args.length >= 4) {
				serviceKey = ServiceKeys.valueOf(args[3]);
			} else {
				throw new BusinessException("Please provide Service key");
			}
			numberOfThreads = (args.length >= 5) ? Integer.parseInt(args[4]) : 1;
		}
		tcpCConfig.setServerPort(serverPort);
		tcpCConfig.setType(type);
		tcpCConfig.setServerHost(serverHost);
		tcpCConfig.setClientHost(clientHost);
		tcpCConfig.setClientPort(clientPort);
		tcpCConfig.setRegistryHost(registryHost);
		tcpCConfig.setRegistryPort(registryPort);
		tcpCConfig.setServiceKey(serviceKey);
//		tcpCConfig.setRequestTimeout(5000);
		if (type == ConnectionType.RPC && numberOfThreads > 1) {
			ExecutorService clientService = Executors.newFixedThreadPool(numberOfThreads);
			final ClientConfig cConfig = tcpCConfig;
			for (int i = 0; i < numberOfThreads; i++) {
				clientService.submit(new Runnable() {

					@Override
					public void run() {
						try {
							Client client = new Client(cConfig);
							client.startClient();
							while (!Utills.checkInterrupt()) {
								Utills.checkInterrupt();
								ClientRun.dummyData(client);
								Thread.yield();
							}
							client.stopClient();

						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				});
			}
			Thread.sleep(1000);
			clientService.shutdownNow();
			while (!clientService.isTerminated()) {
			}
			Client client = new Client(cConfig);
			client.startClient();
			client.stopServerCall(false);
			client.stopClient();
		} else {
			Client client = new Client(tcpCConfig);
			client.startClient();
			ClientRun.dummyData(client);
			ClientRun.window(client);
		}
	}

}
