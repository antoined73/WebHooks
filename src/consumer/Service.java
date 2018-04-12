package consumer;

import common.IService;

import java.io.Serializable;

public class Service implements IService, Serializable {

    public Service() {
    }

    @Override
    public void printStream(String stream) {
        System.err.println(stream);
        System.out.println(stream);
    }
}
