package common;

import java.rmi.RemoteException;

public interface IClientConnectedService extends IService {
    void newClientConnected(String name) throws RemoteException;
}
