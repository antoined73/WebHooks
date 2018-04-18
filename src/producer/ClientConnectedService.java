package producer;

import common.IClientConnectedService;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientConnectedService extends UnicastRemoteObject implements IClientConnectedService, Serializable {

    private PClient client;

    protected ClientConnectedService(PClient client) throws RemoteException {
        super();
        this.client = client;
    }

    @Override
    public void newClientConnected(String name) throws RemoteException {
        int result = this.client.registerSubscriber(name);
        switch (result) {
            case -1:
                System.err.println("# New consumer \"" + name + "\" tried to subscribe, but registry could not be found.");
                break;
            case 0:
                System.err.println("# New consumer \"" + name + "\" successfully subscribed.");
                break;
            case 1:
                System.err.println("# New consumer \"" + name + "\" tried to subscribe, but was already subscribed.");
                break;
            case 2:
                System.err.println("# New consumer \"" + name + "\" tried to subscribe, but there was an error adding it to the consumer list.");
        }
    }
}
