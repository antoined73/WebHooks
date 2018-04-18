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
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PClient {

    private static int REGISTRY_PORT_NUMBER;

    private List<IPrintMessageService> streamReaders = new CopyOnWriteArrayList<>();

    void launch(int port) throws RemoteException {
        REGISTRY_PORT_NUMBER = port;
        Registry r = LocateRegistry.createRegistry(port);
        Distant objetDistant = new ClientConnectedDistantObject(this);
        String registerName = "Producer12";
        r.rebind(registerName, objetDistant);
        System.err.println("# Producer is registered on port " + REGISTRY_PORT_NUMBER + " with name \"" + registerName
                + "\". Consumers may now subscribe anytime.");
    }

    /**
     * this methods emulates the Producer's server and lets a new consumer subscribe to the stream.
     *
     * @return -1 when the registry could not be found
     * <br>0 when the consumer is correctly added,
     * <br>1 if the consumer was already subscribed
     * <br>2 if there was an error adding the consumer
     */
    int registerSubscriber(String name) {

        Registry r = null;
        int result = -1;

        try {
            r = LocateRegistry.getRegistry(REGISTRY_PORT_NUMBER);
            Distant d = (Distant) r.lookup(name);
            result = addStreamReader(d.createService());
        } catch (NullPointerException | NotBoundException | RemoteException e) {
            e.printStackTrace();
        }
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

    /**
     * This method emulates the Producer's client and notifies all the subscribed clients
     * whenever new data is received (every 0,5 seconds). Once used once, it will run a
     * neverending loop, reading the data\datafile.txt file.
     *
     * @throws IOException if there's an error with the data file
     */
    void readFile() throws IOException {

        for (; ; ) {
            FileReader fileReader = new FileReader("data\\datafile.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            char[] buffer = new char[20];
            int isOver;

            do {
                isOver = bufferedReader.read(buffer, 0, 20);
                for (IPrintMessageService streamReader : streamReaders) {
                    streamReader.printStream(String.valueOf(buffer));
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            while (isOver != -1);
        }
    }
}
