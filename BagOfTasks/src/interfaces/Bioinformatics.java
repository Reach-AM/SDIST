package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import objects.Task;

public interface Bioinformatics extends Remote {
    boolean executeBioTask(Task t) throws RemoteException;
}
