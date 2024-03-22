package imd.ufrn.interfaces;

import java.util.function.Function;

// offers the sendMessage method and 
// calls the callbackFunctionMessageReceived function when a 
// new message is received from the server
// has to be initialized before use.
public abstract class BaseCommunicationWithClientController implements Runnable {

    protected Function<String, String> callbackFunctionMessageReceived;

    public BaseCommunicationWithClientController(Function<String, String> callbackFunctionMessageReceived) {
        this.callbackFunctionMessageReceived = callbackFunctionMessageReceived;
    }

    protected abstract boolean initialize();
}
