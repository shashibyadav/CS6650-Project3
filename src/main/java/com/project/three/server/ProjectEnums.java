package com.project.three.server;

public class ProjectEnums {
	public enum ConnectionType {
		UDP,
		TCP,
		RPC,
		ALL
	};
	public enum MethodType {
		PUT,
		GET,
		DELETE,
		STOP,
		RUN
	}
	public enum RequestKeys {
		type,
		data,
		authId
	}
	public enum ServiceKeys {
		StoreService,
		CoordinatorService
	}
	public enum DataKeys {
		Priority
	}
}