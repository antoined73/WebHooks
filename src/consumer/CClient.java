package consumer;

import common.Distant;
import common.IConsumerConnectionService;
import common.IService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class CClient {

    private Distant objetDistant = null;

    public void launch(int portNumber, String name) {
        boolean connected = connectToProducerServer(portNumber, name);
        if(!connected){
            System.err.println("Producer not found, press a key to retry connection.\n");
            Scanner s = new Scanner(System.in); //
            s.next();
            launch(portNumber,name); //retry connection
        }
    }

    /**
     * This methods subscribes the consumer to the producer by registering itself to the registry.
     *
     * @param producerPort the port where the producer's registry may be found
     * @param name         the consumer's name
     */
    private boolean connectToProducerServer(int producerPort, String name) {
        Registry r;

        // this binds the consumer to the registry
        try {
            r = LocateRegistry.getRegistry(producerPort);
            objetDistant = new PrintMessageDistantObject();
            if (r != null) {
                r.rebind(name, objetDistant);
                System.err.println("Consumer registered on port " + producerPort + " with name \"" + name
                        + "\". Producer may now send data anytime.\n");
            }else{
                return false;
            }
        } catch (RemoteException e) {
            //e.printStackTrace();
            return false;
        }

        // this notifies the server that a new client was added
        try {
            r = LocateRegistry.getRegistry(producerPort);
            Distant d = (Distant) r.lookup("Producer12");
            IService result = d.createService();
            IConsumerConnectionService s = (IConsumerConnectionService) result;
            s.newConsumerConnection(name);
        } catch (NullPointerException | NotBoundException | RemoteException e) {
            //e.printStackTrace();
            return false;
        }
        return true;
    }
}
