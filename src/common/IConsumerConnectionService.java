package common;

import java.rmi.RemoteException;

public interface IConsumerConnectionService extends IService {
    void newConsumerConnection(String name) throws RemoteException;
}
