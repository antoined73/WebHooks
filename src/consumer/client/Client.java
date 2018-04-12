package consumer.client;

import common.DistantObject;
import common.DistantObjectObserver;
import common.DistantStreamReader;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client implements DistantObjectObserver{

    private DistantObject objetDistant = null;

    public void launch() {
        connectToProducerServer("2501");
    }

    private void connectToProducerServer(String portNumber) {
        Registry r;
        try {
            int producerPort = Integer.parseInt(portNumber);
            r = LocateRegistry.getRegistry (producerPort);
            if(r!=null){
                System.out.println("Registry trouvé sur le port "+producerPort);
                System.out.println(r);
            }
            objetDistant = new DistantStreamReader();
            objetDistant.addObserver(this);
            String registerName = "client";
            r.rebind(registerName,objetDistant);
            System.out.println("Object sended on port "+ 2501 +" with name \""+registerName+"\". The distant server can now send data anytime.");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(DistantObject observable, String updateMsg) throws RemoteException {
        System.out.println(updateMsg);
    }
}
