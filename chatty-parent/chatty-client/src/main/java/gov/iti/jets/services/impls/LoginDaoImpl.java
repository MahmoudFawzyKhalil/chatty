package gov.iti.jets.services.impls;

import gov.iti.jets.commons.dtos.LoginDto;
import gov.iti.jets.commons.remoteinterfaces.LoginService;
import gov.iti.jets.services.LoginDao;
import gov.iti.jets.services.util.CipherUtil;
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

    private void saveLoggedData(LoginDto loginDto) {
        CipherUtil cipherUtil = CipherUtil.getInstance();
        String encryptedPhone = cipherUtil.encrypt(loginDto.getPhoneNumber());
        String encryptedPassword = cipherUtil.encrypt(loginDto.getPassword());
        try {
            File file = new File("myData.chatty");
            file.createNewFile();
            try (FileWriter fileWriter = new FileWriter(file);) {
                fileWriter.write(encryptedPhone + "\n" + encryptedPassword);
                fileWriter.flush();
                System.out.println(cipherUtil.decrypt(encryptedPassword));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Optional<LoginDto> loadIfFileExist() {
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
    }
}
