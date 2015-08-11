package resources;

import service.SuperService;

public class SuperResource {
	
	protected SuperService service;

	public SuperService getService() {
		return service;
	}

	public void setService(SuperService service) {
		this.service = service;
	}

}
