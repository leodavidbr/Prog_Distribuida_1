package imd.ufrn;

import java.util.function.Consumer;

import imd.ufrn.interfaces.BaseCommunicationWithClientController;

public class ClientCommunicationRmiImpl extends BaseCommunicationWithClientController {

    public ClientCommunicationRmiImpl(Consumer<String> callbackFunctionMessageReceived) {
        super(callbackFunctionMessageReceived);
    }

    @Override
    public void run() {
    }

    @Override
    protected boolean initialize() {
        return true;
    }

    @Override
    public void sendMessage(String mensagem) {
    }

}
