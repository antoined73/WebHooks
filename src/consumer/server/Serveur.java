package consumer.server;

import consumer.common.Distante;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Serveur {

    private Registry r = null;

    public void launch(int numPort) {
        try {
            r = LocateRegistry.createRegistry(numPort);
            Distante objetDistant = new ObjetDistant();
            r.rebind("RMI_DEZARNAUD",objetDistant);
            System.out.println("Serveur OK - ouvert sur le port "+numPort+". L'objet distant est enregistré sous le nom \"RMI_DEZARNAUD\"");
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
