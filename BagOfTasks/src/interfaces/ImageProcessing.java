package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import objects.Task;

public interface ImageProcessing extends Remote {
    boolean executeImgTask(Task t) throws RemoteException;
}
