package consumer;

import common.Distant;
import common.IService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PrintMessageDistantObject extends UnicastRemoteObject implements Distant {

    private IService service = null;

    protected PrintMessageDistantObject() throws RemoteException {
    }

    @Override
    public IService createService() throws RemoteException {
        if (this.service == null) {
            service = new PrintMessageService();
        }
        return service;
    }
}
