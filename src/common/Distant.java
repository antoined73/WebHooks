package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Distant extends Remote {

    IService createService() throws RemoteException;
}
