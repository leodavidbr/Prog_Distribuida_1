package imd.ufrn.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiSendToServerRemoteInterface extends Remote {
    public void messageToServer(String message) throws RemoteException;
}
