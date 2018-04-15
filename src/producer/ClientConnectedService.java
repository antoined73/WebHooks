package producer;

import common.IClientConnectedService;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientConnectedService  extends UnicastRemoteObject implements IClientConnectedService, Serializable {

    private PClient client;

    protected ClientConnectedService(PClient client) throws RemoteException {
        super();
        this.client = client;
    }

    @Override
    public void newClientConnected(String name) throws RemoteException {
        this.client.registerSubscriber(name);
        System.out.println(name);
    }
}
