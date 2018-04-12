package consumer.client;

import common.DistantObject;
import common.DistantObjectObserver;
import common.DistantStreamReader;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client implements DistantObjectObserver{

    private DistantObject objetDistant = null;

    public void launch(int numPort) {

        //Listening keyboard
        Scanner scanner = new Scanner(System.in);
        String cmdLine;
        String cmd;
        do{
            cmd = scanner.next();
            cmdLine = scanner.nextLine().trim();
            String[] cmdArgs = cmdLine.split(" ");
            doActionForCommand(cmd, cmdArgs);

        }while(!cmd.equals("quit"));
    }

    private void doActionForCommand(String cmd, String[] args) {

        switch (cmd){
            case "subscribe":
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

    private void connectToProducerServer(String portNumber) {
        Registry r;
        try {
            int numPort = Integer.parseInt(portNumber);
            r = LocateRegistry.getRegistry (numPort);
            if(r!=null){
                System.out.println("Registry trouvé sur le port "+numPort);
                System.out.println(r);
            }
            objetDistant = new DistantStreamReader();
            objetDistant.addObserver(this);
            r.rebind("RMI_DEZARNAUD",objetDistant);
            System.out.println("Object sended on port "+numPort+" with name \"RMI_DEZARNAUD\". The distant server can now send data anytime.");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void displayHelp() {
        System.out.println("Help :\n" +
                "subscribe [producer_server_port]\n Subscribe to the producer server giving the remote object.\n" +
                "help\n Display this help message.\n");
    }


    @Override
    public void update(DistantObject observable, String updateMsg) throws RemoteException {
        System.out.println(updateMsg);
    }
}
