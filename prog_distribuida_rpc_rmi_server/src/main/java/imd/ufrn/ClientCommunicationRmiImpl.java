package imd.ufrn;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.function.Function;

import imd.ufrn.interfaces.BaseCommunicationWithClientController;
import imd.ufrn.interfaces.RmiSendToServerRemoteInterface;

public class ClientCommunicationRmiImpl extends BaseCommunicationWithClientController
        implements RmiSendToServerRemoteInterface {
    private Registry registry;

    public ClientCommunicationRmiImpl(Function<String, String> callbackFunctionMessageReceived) throws RemoteException {
        super(callbackFunctionMessageReceived);
        initialize();
        System.out.println("communication initialized");
    }

    @Override
    protected boolean initialize() {
        try {
            createStubAndBind();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public String messageToServer(String message) throws RemoteException {
        System.out.println("client said: " + message);
        String botResponse = this.callbackFunctionMessageReceived.apply(message);
        System.out.println("botSaid" + botResponse);
        return botResponse;
    }

    private void createStubAndBind() throws RemoteException {

        RmiSendToServerRemoteInterface stub = (RmiSendToServerRemoteInterface) UnicastRemoteObject
                .exportObject((RmiSendToServerRemoteInterface) this, 0);
        System.out.println("run");
        registry = LocateRegistry.createRegistry(1099);
        registry.rebind("RmiToServerRemoteInterface", stub);
    }
}
