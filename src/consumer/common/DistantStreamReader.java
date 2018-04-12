package consumer.common;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class DistantStreamReader extends UnicastRemoteObject implements DistantObject {

    protected DistantStreamReader() throws RemoteException {
    }

    @Override
    public void printStream(String stream) throws RemoteException {

    }
}
