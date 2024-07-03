package Resources.Utility;

import com.sun.mail.imap.IMAPFolder;
import org.testng.Reporter;

import javax.mail.*;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Test Class OutlookHandler
 * Being used to perform mail reading operations for Outlook accounts
 */
public class OutlookHandler {

    /**
     * getImapSession
     * <p>
     * Sets an IMAP email session
     *
     * @return Session instance of IMAP protocol
     */
    private Session getImapSession() {
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imap");
        props.setProperty("mail.debug", "true");
        props.setProperty("mail.imap.host", "outlook.office365.com");
        props.setProperty("mail.imap.port", "993");
        props.setProperty("mail.imap.ssl.enable", "true");
        props.setProperty("mail.debug", "false");

        Session session = Session.getDefaultInstance(props, null);
        return session;
    }

    /**
     * startSessionAndGetVerificationCode
     * Sign in to given mail and gets Trendyol email verification code.
     *
     * @param username: Outlook account. Should include @outlook.com
     * @param password: Password of outlook account.
     */
    public String[] startSessionAndGetVerificationCode(String username, String password) {
        // Wait 4 seconds for verification code to come
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Session session = getImapSession();
        try {
            Store store = session.getStore("imap");
            store.connect("outlook.office365.com", 993, username, password);
            IMAPFolder inbox = (IMAPFolder) store.getFolder("Inbox");
            inbox.open(Folder.READ_WRITE);

            // Iterate through all mails
            for (int i = 1; i < inbox.getMessageCount() + 1; i++) {
                Message msg = inbox.getMessage(i);
                Enumeration<Header> messageHeaders = msg.getAllHeaders();
                while (messageHeaders.hasMoreElements()) {
                    Header messageHeader = messageHeaders.nextElement();
                    String value = messageHeader.getValue();

                    //Trendyol verification code string is found here
                    //It's usually something like _KOD:_198211?= and we need to extract 6 digits from here
                    int index = value.indexOf("_KOD:_") + 6;

                    //If any string matches '_KOD:_', we need to be sure it's found at least once
                    if (index > 6) {
                        String[] codeNumber = new String[6];
                        for (int j = 0; j < 6; j++) {
                            codeNumber[j] = Character.toString(value.charAt(index++));
                        }
                        System.out.println("Mail verification code is: " + Arrays.toString(codeNumber));
                        Reporter.log("Mail verification code is: " + Arrays.toString(codeNumber), 5);
                        return codeNumber;
                    }
                }
            }
            System.out.println("Unable to find any verification code in " + username);
            Reporter.log("Unable to find any verification code in " + username, 5);
        } catch (Exception e) {
            System.out.println("Exception happened in reading EMails");
            Reporter.log("Exception happened in reading EMails", 5);
        }
        return null;
    }
}
