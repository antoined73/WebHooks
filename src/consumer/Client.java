package consumer;

import common.Distant;
import common.IClientConnectedService;
import common.IService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    private Distant objetDistant = null;

    public void launch(int portNumber, String name) {
        connectToProducerServer(portNumber,name);
    }

    private void connectToProducerServer(int producerPort,String name) {
        String registerName = name;
        //send my object
        Registry r;
        try {
            r = LocateRegistry.getRegistry(producerPort);
            objetDistant = new PrintMessageDistantObject();

            if (r != null) {
                r.rebind(registerName, objetDistant);
                System.out.println("Object sended on port " + producerPort + " with name \"" + registerName + "\". The distant server can now send data anytime.\n");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        //get server object to subscribe
        r = null;

        try {
            r = LocateRegistry.getRegistry(producerPort);
            Distant d = (Distant) r.lookup("server1278");
            IService result = d.createService();
            IClientConnectedService s = (IClientConnectedService) result;
            s.newClientConnected(registerName);
        } catch (NullPointerException | NotBoundException | RemoteException e) {
            e.printStackTrace();
        }
    }
}
