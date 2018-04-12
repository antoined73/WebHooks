package consumer;

import common.Distant;
import common.IService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class DistantObject extends UnicastRemoteObject implements Distant {

    private IService service = null;

    protected DistantObject() throws RemoteException {
    }

    @Override
    public IService createService() throws RemoteException {
        if (this.service == null) {
            service = new Service();
        }
        return service;
    }
}
