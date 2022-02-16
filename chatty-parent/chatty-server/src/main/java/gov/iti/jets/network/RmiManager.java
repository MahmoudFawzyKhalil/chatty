package gov.iti.jets.network;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiManager {
    private final static RmiManager RMI_MANAGER = new RmiManager();
    private Registry registry;

    private RmiManager() {
        try {
            this.registry = LocateRegistry.createRegistry(1099);
        } catch (RemoteException e) {
            try {
                this.registry = LocateRegistry.getRegistry(1099);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
            System.err.println("Registry was already alive. Got registry.");
        }

        registerServices();
    }

    public static RmiManager getInstance() {
        return RMI_MANAGER;
    }

    private void registerServices() {
        try {
            registry.rebind("LoginService", new LoginServiceImpl());
            registry.rebind("RegisterService", new RegisterServiceImpl());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            registry.unbind("LoginService");
            registry.unbind("RegisterService");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
//        try {
//            Runtime rt = Runtime.getRuntime();
//            Process proc = rt.exec("cmd /c netstat -ano | findstr 1099");
//            BufferedReader stdInput = new BufferedReader(new
//                    InputStreamReader(proc.getInputStream()));
//            String s;
//            if ((s = stdInput.readLine()) != null) {
//                int index = s.lastIndexOf(" ");
//                String sc = s.substring(index);
//                rt.exec("cmd /c Taskkill /PID" + sc + " /F");
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
