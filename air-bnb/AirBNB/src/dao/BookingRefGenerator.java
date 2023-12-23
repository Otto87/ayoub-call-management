package dao;

import java.security.SecureRandom;

public class BookingRefGenerator {
	
	private static String ref;
	
    public static String generateRef(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(ref.length());
            char randomChar = ref.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }
    

}
