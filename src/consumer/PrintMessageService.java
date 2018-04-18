package consumer;

import common.IPrintMessageService;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PrintMessageService extends UnicastRemoteObject implements IPrintMessageService, Serializable {

    protected PrintMessageService() throws RemoteException {
        super();
    }

    @Override
    public void printStream(String stream) throws RemoteException {
        System.out.println(stream);
    }
}
