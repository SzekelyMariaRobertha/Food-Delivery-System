package dal;

/**
 * The purpose of this class is to encrypt / decrypt user passwords
 *
 * @author Szekely Maria-Robertha
 */

public class CaesarCipher {

    /**
     * This method if moving the current character to the current position in ASCII table + offset
     *
     * @param password represents the user password
     * @param offset   represents the position with which the character will have to be moved forward from the current position
     * @return the encrypted password
     */

    public String cipher(String password, int offset) {
        StringBuilder res = new StringBuilder();
        for (char character : password.toCharArray()) {
            if (Character.isDigit(character)) {
                int originalPos = character - '0';
                int newPos = (originalPos + offset) % 26;
                char newCharacter = (char) ('0' + newPos);
                res.append(newCharacter);
            } else {
                if (character >= 'a' && character <= 'z') {
                    int originalPos = character - 'a';
                    int newPos = (originalPos + offset) % 26;
                    char newCharacter = (char) ('a' + newPos);
                    res.append(newCharacter);
                } else if (character >= 'A' && character <= 'Z') {
                    int originalPos = character - 'A';
                    int newPos = (originalPos + offset) % 26;
                    char newCharacter = (char) ('A' + newPos);
                    res.append(newCharacter);
                } else {
                    res.append(character);
                }
            }
        }
        return String.valueOf(res);
    }

    /**
     * This method if moving the current character to the current position in ASCII table - offset
     *
     * @param password represents the user password
     * @param offset   represents the position with which the character will have to be moved backward from the current position
     * @return the decrypted password
     *
     */

    public String decipher(String password, int offset) {
        return cipher(password, 26 - (offset % 26));
    }
}