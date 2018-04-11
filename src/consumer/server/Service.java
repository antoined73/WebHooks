package consumer.server;

import consumer.common.IService;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Service extends UnicastRemoteObject implements IService, Serializable {

    private int value = 1;

    public Service(int initValue) throws RemoteException {
        super();
        this.value = initValue;
    }

    @Override
    public int getValue() throws RemoteException {
        return value;
    }

    @Override
    public void setValue(int value) throws RemoteException {
        this.value+=value;
    }
}
