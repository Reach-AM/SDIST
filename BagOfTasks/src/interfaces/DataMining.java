package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import objects.Task;

public interface DataMining extends Remote {
    boolean executeDataTask(Task t) throws RemoteException;
}
