package imd.ufrn.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiRemoteInterface extends Remote {
    public String getMessage(String message) throws RemoteException;
}
