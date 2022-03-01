package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import interfaces.*;
import objects.Task;

public class SlaveNode implements Bioinformatics, DataMining, ImageProcessing {

    public SlaveNode() throws RemoteException {
        super();
    }

    public void start(String service) throws RemoteException {
        try {
            System.setProperty("java.security.policy", "src/server/server.policy");
            if(System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }

            SlaveNode engine = new SlaveNode();

            switch (service) {
                case "Bioinformatics": {
                    Bioinformatics stub = (Bioinformatics) UnicastRemoteObject.exportObject(engine, 0);
                    Registry registry = LocateRegistry.getRegistry();
                    registry.rebind(service, stub);
                    break;
                }
                case "DataMining": {
                    DataMining stub = (DataMining) UnicastRemoteObject.exportObject(engine, 0);
                    Registry registry = LocateRegistry.getRegistry();
                    registry.rebind(service, stub);
                    break;
                }
                case "ImageProcessing": {
                    ImageProcessing stub = (ImageProcessing) UnicastRemoteObject.exportObject(engine, 0);
                    Registry registry = LocateRegistry.getRegistry();
                    registry.rebind(service, stub);
                    break;
                }
            }
            System.out.println(service + " bound");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean executeBioTask(Task t) throws RemoteException {
        try {
            Thread.sleep(t.getLength());
        } catch (InterruptedException ex) {
            Logger.getLogger(SlaveNode.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public boolean executeDataTask(Task t) throws RemoteException {
        try {
            Thread.sleep(t.getLength());
        } catch (InterruptedException ex) {
            Logger.getLogger(SlaveNode.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public boolean executeImgTask(Task t) throws RemoteException {
        try {
            Thread.sleep(t.getLength()* 1000L);
        } catch (InterruptedException ex) {
            Logger.getLogger(SlaveNode.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
