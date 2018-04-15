package common;

import java.rmi.RemoteException;

public interface IPrintMessageService extends IService {
    void printStream(String stream) throws RemoteException;
}
