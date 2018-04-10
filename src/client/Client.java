package client;

import common.Distante;
import common.IService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.TimeUnit;

public class Client {

        public static void main(String[] args) {
            int numPort = 2500;
            if(args.length > 1){
                numPort = Integer.parseInt(args[1]);
            }
            Registry r = null;
            try {
                r = LocateRegistry.getRegistry (numPort);
                if(r!=null){
                    System.out.println("Registry trouvé sur le port "+numPort);
                }
                Distante objetTrouve = (Distante) r.lookup("RMI_DEZARNAUD");
                if(objetTrouve!=null){
                    System.out.println("Objet distant trouvé sour le nom \"RMI_DEZARNAUD\"");
                    System.out.println("debut appel echo depuis le client sur l'objet distant");
                    IService distant = objetTrouve.createService();
                    System.out.println(distant);
                    for(int i=0; i <100; i++){
                        distant.setValue(1);
                        System.out.println(distant.getValue());
                        TimeUnit.MILLISECONDS.sleep(2000);
                    }
                    System.out.println(distant.getValue());
                    System.out.println("fin appel echo depuis le client sur l'objet distant");
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (NotBoundException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
}
