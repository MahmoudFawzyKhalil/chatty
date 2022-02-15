package gov.iti.jets.network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiManager {
    private final static RmiManager RMI_MANAGER = new RmiManager();
    private Registry registry;

    private RmiManager() {
        try {
            this.registry = LocateRegistry.createRegistry(1099);
            registerServices();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static RmiManager getInstance() {
        return RMI_MANAGER;
    }

    private void registerServices() {
        try {
            registry.rebind("loginService", new LoginServiceImpl());
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
