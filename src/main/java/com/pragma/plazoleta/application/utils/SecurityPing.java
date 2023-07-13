package com.pragma.plazoleta.application.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class SecurityPing {
    private static final String ALGORITHM = "AES";
    private static final String SECRET_KEY = "YourSecretKey123";

    public static String generatePin(int orderId, int restaurantId) {
        try {
            String plaintext = orderId + "_" + restaurantId;
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int decodePin(String pin, int restaurantId) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decodedBytes = Base64.getDecoder().decode(pin);
            String decryptedText = new String(cipher.doFinal(decodedBytes), StandardCharsets.UTF_8);
            String[] parts = decryptedText.split("_");
            int orderId = Integer.parseInt(parts[0]);
            int decryptedRestaurantId = Integer.parseInt(parts[1]);
            if (decryptedRestaurantId == restaurantId) {
                return orderId;
            } else {
                throw new IllegalArgumentException("Invalid restaurant ID");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}
