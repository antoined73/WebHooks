package common;

import java.rmi.RemoteException;

public interface IPrintMessageService extends IService {
    void printMessage(String stream) throws RemoteException;
}
