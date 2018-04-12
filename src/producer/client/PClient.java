package producer.client;

import common.DistantStreamReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PClient {

    private static int REGISTRY_PORT_NUMBER = 2501;

    private List<DistantStreamReader> streamReaders = new ArrayList<>();

    /**
     * this methods lets a new consumer subscribe to the stream.
     * @return -1 when the registry could not be found<br>0 when the consumer is correctly added,<br>1 if the consumer was already subscribed<br>2 if there was an error adding the consumer
     */
    public int registerSubscriber() {

        Registry r = null;
        int result = -1;

        try {
            r = LocateRegistry.getRegistry(REGISTRY_PORT_NUMBER);
            result = addStreamReader((DistantStreamReader) r.lookup("client"));
        } catch (NullPointerException | NotBoundException | RemoteException e) {
            e.printStackTrace();
        }
        return result;
    }

    private int addStreamReader(DistantStreamReader dsr) {
        if (streamReaders.contains(dsr))
            return 1;
        try {
            streamReaders.add(dsr);
            return 0;
        } catch (Exception e) {
            return 2;
        }
    }

    public void readFile(String filename) throws IOException {
        FileReader fileReader = new FileReader("data\\datafile.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        int index = 0, bufferSize = (int) (Math.random() * 10);
        char[] buffer = new char[10];

        while (bufferedReader.read(buffer, index, bufferSize) != -1) {
            index += bufferSize;
            bufferSize = (int) (Math.random() * 10);
            for (DistantStreamReader dsr : streamReaders)
                dsr.printStream(String.valueOf(buffer));
        }
    }
}
