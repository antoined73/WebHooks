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
        connectToProducerServer(portNumber, name);
    }

    /**
     * This methods subscribes the consumer to the producer by registering itself to the registry.
     *
     * @param producerPort the port where the producer's registry may be found
     * @param name         the consumer's name
     */
    private void connectToProducerServer(int producerPort, String name) {
        Registry r;

        // this binds the consumer to the registry
        try {
            r = LocateRegistry.getRegistry(producerPort);
            objetDistant = new PrintMessageDistantObject();
            if (r != null) {
                r.rebind(name, objetDistant);
                System.err.println("Consumer registered on port " + producerPort + " with name \"" + name
                        + "\". Producer may now send data anytime.\n");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        // this notifies the server that a new client was added
        try {
            r = LocateRegistry.getRegistry(producerPort);
            Distant d = (Distant) r.lookup("Producer12");
            IService result = d.createService();
            IClientConnectedService s = (IClientConnectedService) result;
            s.newClientConnected(name);
        } catch (NullPointerException | NotBoundException | RemoteException e) {
            e.printStackTrace();
        }
    }
}
