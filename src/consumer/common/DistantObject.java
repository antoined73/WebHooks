package consumer.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DistantObject extends Remote {

    void printStream(String stream) throws RemoteException;
}
