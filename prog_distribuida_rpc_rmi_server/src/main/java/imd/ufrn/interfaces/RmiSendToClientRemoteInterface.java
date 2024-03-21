package imd.ufrn.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiSendToClientRemoteInterface extends Remote {
    public void messageToClient(String message) throws RemoteException;

}
