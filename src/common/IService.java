package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IService extends Remote {

    int getValue() throws RemoteException;

    void setValue(int value) throws RemoteException;
}
