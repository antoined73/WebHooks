package consumer;

import common.Distant;
import common.IClientConnectedService;
import common.IService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CClient {
    private Distant objetDistant = null;

    public void launch(int portNumber, String name) {
        connectToProducerServer(portNumber,name);
    }

    private void connectToProducerServer(int producerPort,String name) {
        Registry r;

        try {
            r = LocateRegistry.getRegistry(producerPort);
            objetDistant = new PrintMessageDistantObject();

            if (r != null) {
                r.rebind(name, objetDistant);
                System.err.println("Object on port " + producerPort + " with name \"" + name
                        + "\". The Server may now send data anytime.\n");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        try {
            r = LocateRegistry.getRegistry(producerPort);
            Distant d = (Distant) r.lookup("server1278");
            IService result = d.createService();
            IClientConnectedService s = (IClientConnectedService) result;
            s.newClientConnected(name);
        } catch (NullPointerException | NotBoundException | RemoteException e) {
            e.printStackTrace();
        }
    }
}
