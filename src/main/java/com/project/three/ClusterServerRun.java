package com.project.three;

import com.project.three.server.CoordinatorServer;
import com.project.three.server.ProjectEnums.ConnectionType;
import com.project.three.server.ProjectEnums.ServiceKeys;
import com.project.three.server.Server;
import com.project.three.utills.ArchConf;
import com.project.three.utills.ServerNetConf;

import java.util.LinkedList;
import java.util.List;

public class ClusterServerRun {
    public static void run(String[] args) throws Exception {
        Object stopServer = new Object();
        CoordinatorServer coordinatorServer = new CoordinatorServer();
        coordinatorServer.startServer();
        ArchConf fullConf = coordinatorServer.getFullConfig();
        List<Server> participantsList = new LinkedList<Server>();

        // Start all Participants
        for (ServerNetConf participantConf : fullConf.getParticipantsConf()) {
            Server server = ServerRun.run(new String[]{ConnectionType.RPC.name(), participantConf.getHost(), String.valueOf(participantConf.getPort()), ServiceKeys.StoreService.name(), "true"});
            server.setClusterSemaphores(stopServer);
            participantsList.add(server);
        }

        // Connect Coordinator to Participants
        coordinatorServer.getCoordinatorService().connectParticipant();

        // Wait for stop server call
        synchronized (stopServer) {
            stopServer.wait();
        }

        // Stopping Cluster
        coordinatorServer.stopServer();
        for (Server server: participantsList) {
            server.stopServerAsync(false);
        }

    }
}
