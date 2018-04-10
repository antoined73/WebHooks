package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Distante extends Remote {

    IService createService() throws RemoteException;
}
