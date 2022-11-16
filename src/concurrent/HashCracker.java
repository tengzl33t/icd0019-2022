package concurrent;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static concurrent.examples.Common.waitTillAllDone;

public class HashCracker {

    private static final String ALPHABET = getAlphabet();
    private static final String HASH_TO_CRACK_1 =
            "d4faeb0660ce6d23883eced24805d2ff0da3600685e2ac9ad1dedd8cc66104ef";
    private static final String HASH_TO_CRACK_2 =
            "5443bf770be3146692aaeea227235917bc36cf1cbcf366f239f160a3ef149fee";

    public static void main(String[] args) {

        int total = 30000000;
        int partCount = 32;
        int chuckSize = total / partCount;
        int start = 0;

        ExecutorService pool = Executors.newFixedThreadPool(16);
        Timer timer = new Timer();

//        while (start < total){
//            int end = start + chuckSize;
//            int startPos = start;
//
//            pool.submit(() -> checkRange(startPos, end, HASH_TO_CRACK_2));
//            start = end;
//        }
//
//        waitTillAllDone(pool);

        Optional<String> result = IntStream.range(0, total).mapToObj(HashCracker::decToString).filter(combination -> sha256(combination).equals(HASH_TO_CRACK_1))
                        .findFirst();

        System.out.println(result);

        System.out.println("\n" + timer.getPassedTime());
    }

    private static String decToString(int decimal){
        if (decimal == 0){
            return String.valueOf(ALPHABET.charAt(0));
        }

        StringBuilder builder = new StringBuilder();

        while (decimal > 0){
            int remainder = decimal % ALPHABET.length();
            builder.append(ALPHABET.charAt(remainder));
            decimal /= ALPHABET.length();
        }


        return builder.reverse().toString();
    }

    private static void checkRange(int start, int end, String hashToMatch){
        for (int i = start; i < end; i++) {
            String hash = sha256(decToString(i));
            if (hash.equals(hashToMatch)){
                System.out.println(decToString(i));
            }
        }
    }

    private static String sha256(String plainText) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        byte[] bytes = digest.digest(plainText.getBytes(StandardCharsets.UTF_8));

        return bytesToHex(bytes);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte byteValue : bytes) {
            String hex = Integer.toHexString(0xff & byteValue);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }

    private static String getAlphabet() {
        StringBuilder sb = new StringBuilder();
        for (char i = 48; i < 123; i++) {
            sb.append(i);
        }

        return sb.toString();
    }
}

