package server;

import common.Distante;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Serveur {
    public static void main(String[] args) {
        int numPort = 2500;
        if(args.length > 1){
            numPort = Integer.parseInt(args[1]);
        }

        Registry r = null;
        try {
            r = LocateRegistry.createRegistry(numPort);
            System.out.println(r.toString());
            Distante objetDistant = new ObjetDistant();
            r.rebind("RMI_DEZARNAUD",objetDistant);
            System.out.println("registry OK - ouvert sur le port "+numPort+". L'objet distant est enregistré sous le nom \"RMI_DEZARNAUD\"");
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
