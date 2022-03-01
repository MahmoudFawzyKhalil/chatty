package gov.iti.jets.presentation.datasaved;

import gov.iti.jets.services.util.CipherUtil;

import java.io.Serializable;

public class LoginData implements Serializable {
    private static final LoginData LOGIN_DATA = new LoginData();
    private transient CipherUtil cipherUtil = CipherUtil.getInstance();
    private String phoneNumber;
    private String password;
    private boolean isCiphered;
    private boolean loadAll = true;

    private LoginData(String phoneNumber, String password, boolean isCiphered) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.isCiphered = isCiphered;
    }

    private LoginData() {

    }

    public static LoginData getInstance() {
        return LOGIN_DATA;
    }

    public void cipherAll() {
        if (cipherUtil == null) {
            cipherUtil = CipherUtil.getInstance();
        }
        if (!isCiphered) {
            this.phoneNumber = cipherUtil.encrypt(this.phoneNumber);
            this.password = cipherUtil.encrypt(this.password);
            this.isCiphered = true;
        }

    }

    public void deCipherAll() {
        if (cipherUtil == null) {
            cipherUtil = CipherUtil.getInstance();
        }
        if (isCiphered) {
            this.phoneNumber = cipherUtil.decrypt(this.phoneNumber);
            this.password = cipherUtil.decrypt(this.password);
            this.isCiphered = false;
        }
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoadAll() {
        return loadAll;
    }

    public void setLoadAll(boolean loadAll) {
        this.loadAll = loadAll;
    }

    public boolean isCiphered() {
        return isCiphered;
    }

    public CipherUtil getCipherUtil() {
        return cipherUtil;
    }

    public void setCipherUtil(CipherUtil cipherUtil) {
        this.cipherUtil = cipherUtil;
    }

    public void setCiphered(boolean ciphered) {
        isCiphered = ciphered;
    }

    @Override
    public String toString() {
        return "LoginDataDto{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", isCiphered=" + isCiphered +
                ", loadAll=" + loadAll +
                '}';
    }
}
