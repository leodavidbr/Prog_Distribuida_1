package imd.ufrn;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.function.Consumer;

import imd.ufrn.interfaces.BaseCommunicationWithClientController;
import imd.ufrn.interfaces.RmiRemoteInterface;

public class ClientCommunicationRmiImpl extends BaseCommunicationWithClientController implements RmiRemoteInterface {

    public ClientCommunicationRmiImpl(Consumer<String> callbackFunctionMessageReceived) throws RemoteException {
        super(callbackFunctionMessageReceived);
        createStubAndBind();
    }

    // @Override
    // public void run() {
    // }

    @Override
    protected boolean initialize() {
        return true;
    }

    @Override
    public void sendMessage(String mensagem) {
    }

    @Override
    public String getMessage(String message) throws RemoteException {
        return "Hello World";
    }

    public void createStubAndBind() throws RemoteException {

        RmiRemoteInterface stub = (RmiRemoteInterface) UnicastRemoteObject.exportObject((RmiRemoteInterface) this, 0);
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("RmiRemoteInterface", stub);
    }

}
