package common;

import java.rmi.Remote;

public interface IService extends Remote {

    public void printStream(String stream);
}
