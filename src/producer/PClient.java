package producer;

import common.Distant;
import common.IService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

import static jdk.nashorn.internal.objects.NativeBoolean.valueOf;

public class PClient {

    private static int REGISTRY_PORT_NUMBER;

    private List<IService> streamReaders = new ArrayList<>();

    public void launch(int port) throws RemoteException {
        Registry r = LocateRegistry.createRegistry(port);
        REGISTRY_PORT_NUMBER = port;
    }

    /**
     * this methods lets a new consumer subscribe to the stream.
     *
     * @return -1 when the registry could not be found<br>0 when the consumer is correctly added,<br>1 if the consumer was already subscribed<br>2 if there was an error adding the consumer
     */
    public int registerSubscriber() {

        Registry r = null;
        int result = -1;

        try {
            r = LocateRegistry.getRegistry(REGISTRY_PORT_NUMBER);
            Distant d = (Distant) r.lookup("client");
            result = addStreamReader(d.createService());
        } catch (NullPointerException | NotBoundException | RemoteException e) {
            e.printStackTrace();
        }
        return result;
    }

    private int addStreamReader(IService dsr) {
        if (streamReaders.contains(dsr))
            return 1;
        try {
            streamReaders.add(dsr);
            return 0;
        } catch (Exception e) {
            return 2;
        }
    }

    public void readFile() throws IOException, InterruptedException {
        FileReader fileReader = new FileReader("data\\datafile.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        int index = 0, bufferSize = 2 + (int) (Math.random() * 8);
        char[] buffer = new char[10];

        while (bufferedReader.read(buffer, index, bufferSize) != -1) {
            index += bufferSize;
            bufferSize = 2 + (int) (Math.random() * 8);
            for (IService dsr : streamReaders) {
                dsr.printStream(String.valueOf(buffer));
                Thread.sleep(1000);
                System.err.println(String.valueOf(buffer));
            }
        }
    }
}
