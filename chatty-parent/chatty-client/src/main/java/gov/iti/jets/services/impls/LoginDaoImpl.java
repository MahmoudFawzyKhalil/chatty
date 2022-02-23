package gov.iti.jets.services.impls;

import gov.iti.jets.commons.dtos.LoginDto;
import gov.iti.jets.commons.remoteinterfaces.LoginService;
import gov.iti.jets.presentation.datasaved.LoginData;
import gov.iti.jets.services.LoginDao;
import gov.iti.jets.services.util.ServiceFactory;

import java.io.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Optional;

public class LoginDaoImpl implements LoginDao {
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Override
    public boolean isAuthenticated(LoginDto loginDto) throws NotBoundException, RemoteException {
        LoginService loginService = serviceFactory.getLoginService();
        boolean isLogged = loginService.login(loginDto);
        if (isLogged) {
            saveLoggedData(loginDto);
        }
        return isLogged;
    }

    @Override
    public Optional<LoginData> getLoginDate() {
        try {
            File file = new File("myData.chatty");
            if (file.exists()) {
                try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
                    LoginData loginData = (LoginData) objectInputStream.readObject();
                    System.out.println(loginData);
                    loginData.deCipherAll();
                    System.out.println(loginData);


                    return Optional.of(loginData);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    private void saveLoggedData(LoginDto loginDto) {
        LoginData loginData = LoginData.getInstance();
        loginData.setPhoneNumber(loginDto.getPhoneNumber());
        loginData.setPassword(loginDto.getPassword());
        loginData.setCiphered(false);
        loginData.cipherAll();
        save(loginData);
    }

    @Override
    public void save(LoginData loginData) {
        try {
            File file = new File("myData.chatty");
            file.createNewFile();
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
                objectOutputStream.writeObject(loginData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public Optional<LoginDto> loadIfFileExist() {
        CipherUtil cipherUtil = CipherUtil.getInstance();


        try {
            File file = new File("myData.chatty");
            if (file.exists()) {
                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                    String encryptedPhone = bufferedReader.readLine();
                    String encryptedPassword = bufferedReader.readLine();
                    LoginDto loginDto = new LoginDto(cipherUtil.decrypt(encryptedPhone), cipherUtil.decrypt(encryptedPassword));
                    return Optional.of(loginDto);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }*/
}
