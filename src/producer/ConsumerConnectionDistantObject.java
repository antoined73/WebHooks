package producer;

import common.Distant;
import common.IService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ConsumerConnectionDistantObject extends UnicastRemoteObject implements Distant {

    private final PClient client;
    private IService service = null;

    protected ConsumerConnectionDistantObject(PClient client) throws RemoteException {
        this.client = client;
    }

    @Override
    public IService createService() throws RemoteException {
        if (this.service == null) {
            service = new ConsumerConnectionService(client);
        }
        return service;
    }
}