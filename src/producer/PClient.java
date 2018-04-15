package producer;

import common.Distant;
import common.IPrintMessageService;
import common.IService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PClient {

    private static int REGISTRY_PORT_NUMBER;

    private List<IPrintMessageService> streamReaders = new CopyOnWriteArrayList<>();

    public void launch(int port) throws RemoteException {
        Registry r = LocateRegistry.createRegistry(port);
        Distant objetDistant = new ClientConnectedDistantObject(this);
        String registerName = "server1278";
        if (r != null) {
            r.rebind(registerName, objetDistant);
            System.out.println("Object send on port " + port + " with name \"" + registerName + "\". The distant client can now send subscribe anytime.");
        }
        REGISTRY_PORT_NUMBER = port;
    }

    /**
     * this methods lets a new consumer subscribe to the stream.
     *
     * @return -1 when the registry could not be found<br>0 when the consumer is correctly added,<br>1 if the consumer was already subscribed<br>2 if there was an error adding the consumer
     */
    public int registerSubscriber(String name) {

        Registry r = null;
        int result = -1;

        try {
            r = LocateRegistry.getRegistry(REGISTRY_PORT_NUMBER);
            Distant d = (Distant) r.lookup(name);
            result = addStreamReader(d.createService());
        } catch (NullPointerException | NotBoundException | RemoteException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        return result;
    }

    private int addStreamReader(IService dsr) {
        IPrintMessageService printService = (IPrintMessageService) dsr;
        if (streamReaders.contains(printService))
            return 1;
        try {
            streamReaders.add(printService);
            return 0;
        } catch (Exception e) {
            return 2;
        }
    }

    public void readFile() throws IOException, InterruptedException {
        FileReader fileReader = new FileReader("data\\datafile.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String buffer;

        do {
            buffer = bufferedReader.readLine();
            Iterator<IPrintMessageService> it = streamReaders.iterator();
            while (it.hasNext()) {
                it.next().printStream(buffer);
                Thread.sleep(1000);
            }
        }
        while (buffer != null);
    }
}
