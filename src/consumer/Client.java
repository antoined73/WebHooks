package consumer;

import common.Distant;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    private Distant objetDistant = null;

    public void launch() {
        connectToProducerServer("2501");
    }

    private void connectToProducerServer(String portNumber) {
        Registry r;
        try {
            int producerPort = Integer.parseInt(portNumber);
            r = LocateRegistry.getRegistry(producerPort);
            objetDistant = new DistantObject();
            String registerName = "client";
            if (r != null) {
                r.rebind(registerName, objetDistant);
                System.out.println("Object sended on port " + portNumber + " with name \"" + registerName + "\". The distant server can now send data anytime.");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
