package consumer.server;

import consumer.common.Distante;
import consumer.common.IService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ObjetDistant extends UnicastRemoteObject implements Distante {

    private IService service = null;

    protected ObjetDistant() throws RemoteException {
    }

    /**
    @Override
    public void echo() throws RemoteException {
        System.out.println("Méthode écho de l'objet distabnt appellée !");
        System.out.println("Début sleep dans objetdistant");
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Fin sleep dans objet distant");
    }**/


    @Override
    public IService createService() throws RemoteException {
        if(this.service==null){
            service =  new Service(5);
        }
        return service;
    }
}
