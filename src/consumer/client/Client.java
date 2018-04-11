package consumer.client;

import consumer.common.Distante;
import consumer.common.IService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Client {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            String cmdLine = "";
            String cmd = "";
            do{
                cmd = scanner.next();
                cmdLine = scanner.nextLine();
                System.out.println(cmdLine);
                cmdLine = cmdLine.trim();
                String[] cmdArgs = cmdLine.split(" ");
                System.out.println(cmdArgs.length);
                doActionForCommand(cmd, cmdArgs);

            }while(!cmd.equals("quit"));

            /**
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
                    System.out.println("debut appel echo depuis le consumer.client sur l'objet distant");
                    IService distant = objetTrouve.createService();
                    System.out.println(distant);
                    for(int i=0; i <100; i++){
                        distant.setValue(1);
                        System.out.println(distant.getValue());
                        TimeUnit.MILLISECONDS.sleep(2000);
                    }
                    System.out.println(distant.getValue());
                    System.out.println("fin appel echo depuis le consumer.client sur l'objet distant");
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (NotBoundException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }**/
        }

    private static void doActionForCommand(String cmd, String[] args) {

        switch (cmd){
            case "contact-server":
                String producerPort = (args.length>0? args[0] : "");
                if(producerPort.isEmpty()){
                    System.out.println("You need to specify a port number to reach the producer server.");
                    displayHelp();
                }else{
                    connectToProducerServer(args[0]);
                }
                break;
            case "quit":
                System.out.println("Bye :)");
                break;
            case "help":
            default:
                displayHelp();
                break;
        }
    }

    private static void connectToProducerServer(String portNumber) {
        Registry r = null;
        try {
            r = LocateRegistry.getRegistry (portNumber);
            if(r!=null){
                System.out.println("Registry trouvé sur le port "+portNumber);
                System.out.println(r);
            }
            Distante objetTrouve = (Distante) r.lookup("RMI_DEZARNAUD");
            if(objetTrouve!=null){
                System.out.println("Objet distant trouvé sour le nom \"RMI_DEZARNAUD\"");
                System.out.println("debut appel echo depuis le consumer.client sur l'objet distant");
                IService distant = objetTrouve.createService();
                System.out.println(distant);
                for(int i=0; i <100; i++){
                    distant.setValue(1);
                    System.out.println(distant.getValue());
                    TimeUnit.MILLISECONDS.sleep(2000);
                }
                System.out.println(distant.getValue());
                System.out.println("fin appel echo depuis le consumer.client sur l'objet distant");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void displayHelp() {
        System.out.println("Help :\n" +
                "subscribe [producer_server_port]\n Subscribe to the producer server giving the port of this client.\n" +
                "help\n Display this help message.\n");
    }
}
