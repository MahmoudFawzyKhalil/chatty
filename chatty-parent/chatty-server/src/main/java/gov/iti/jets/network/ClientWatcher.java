package gov.iti.jets.network;

public class ClientWatcher {
    private static ClientWatcher INSTANCE = new ClientWatcher();

    private ClientWatcher(){

    }

    public static ClientWatcher getInstance() {
        return INSTANCE;
    }

    //TODO implement client watching
}
