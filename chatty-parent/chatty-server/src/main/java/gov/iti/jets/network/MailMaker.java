package gov.iti.jets.network;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;


public class MailMaker {
    private static MailMaker INSTANCE = new MailMaker();

    private HtmlEmail htmlEmail ;
    private final String accountEmail = "chatty373@gmail.com";
    private final String password = "chattychatty373";

    private Logger logger = LoggerFactory.getLogger(MailMaker.class);

    private MailMaker() {

    }
    public static MailMaker getInstance() {
        return INSTANCE;
    }
    private void config() {
        htmlEmail = new HtmlEmail();
        htmlEmail.setHostName("smtp.googlemail.com");
        htmlEmail.setSmtpPort(465);
        htmlEmail.setAuthenticator(new DefaultAuthenticator(accountEmail,
                password));
        htmlEmail.setSSLOnConnect(true);
        try {
            htmlEmail.setFrom(accountEmail);
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }


    public void sendAddContactMail(String receiverMail,String senderPhoneNumber) throws EmailException, MalformedURLException {
        config();
        htmlEmail.setSubject("New Contact");
        htmlEmail.addTo(receiverMail);
        htmlEmail.setHtmlMsg("<html>" +
                "<h2>You have a new friend request</h1> " +
                "<p> <strong>From: </strong>"+senderPhoneNumber+"</p>  " +
                "</html>");
        htmlEmail.send();
        logger.info("New Add contact mail sent!!");
    }
}
