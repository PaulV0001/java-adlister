import org.mindrot.jbcrypt.BCrypt;

public class PasswordTest {
    public static void main(String[] args) {

        int numberOfRounds = 12;
        String hash = BCrypt.hashpw("", BCrypt.gensalt(numberOfRounds));

        System.out.println(hash);
    }
}
