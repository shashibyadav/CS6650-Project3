package com.project.three.utills;

import java.util.List;

/**
 * The ArchConf class represents the architectural configuration of the distributed system.
 * It includes configuration details for the coordinator server and a list of participant servers.
 */
public class ArchConf {
    private ServerNetConf coordinatorConf;
    private List<ServerNetConf> participantsConf;

    /**
     * Gets the configuration for the coordinator server.
     *
     * @return The ServerNetConf object representing coordinator server configuration.
     */
    public ServerNetConf getCoordinatorConf() {
        return coordinatorConf;
    }

    /**
     * Sets the configuration for the coordinator server.
     *
     * @param coordinatorConf The ServerNetConf object representing coordinator server configuration.
     */
    public void setCoordinatorConf(ServerNetConf coordinatorConf) {
        this.coordinatorConf = coordinatorConf;
    }

    public List<ServerNetConf> getParticipantsConf() {
        return participantsConf;
    }

    public void setParticipantsConf(List<ServerNetConf> participantsConf) {
        this.participantsConf = participantsConf;
    }
}
