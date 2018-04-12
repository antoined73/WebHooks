package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DistantObjectObserver extends Remote {

    void update(DistantObject observable, String updateMsg) throws RemoteException;

}