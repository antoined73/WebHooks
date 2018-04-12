package common;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class DistantStreamReader extends UnicastRemoteObject implements DistantObject {

    public DistantStreamReader() throws RemoteException {
    }

    @Override
    public void printStream(String stream) throws RemoteException {
        System.out.println(stream);
    }

    @Override
    public void addObserver(DistantObjectObserver o) throws RemoteException {
        //nothing
    }
}
