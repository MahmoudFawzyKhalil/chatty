package gov.iti.jets.network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMI {
    private final static RMI rmi = new RMI();
    private Registry registry;

    private RMI() {
        try {
            LocateRegistry.createRegistry(1099);
            this.registry = LocateRegistry.getRegistry();
            registerServices();

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static RMI getInstance() {
        return rmi;
    }

    private void registerServices() {
        try {
            registry.rebind("login-service", new LoginServiceImpl());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void closing() {
        try {
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec("cmd /c netstat -ano | findstr 1099");
            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(proc.getInputStream()));
            String s;
            if ((s = stdInput.readLine()) != null) {
                int index = s.lastIndexOf(" ");
                String sc = s.substring(index);
                rt.exec("cmd /c Taskkill /PID" + sc + " /F");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
