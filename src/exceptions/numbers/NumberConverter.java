package exceptions.numbers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class NumberConverter {
    private String lang;
    private String filePath;

    public NumberConverter(String lang) {
        this.lang = lang;

        FileInputStream is = null;
        filePath = "src/exceptions/numbers/numbers_"+ lang + ".properties";

        try {
            is = new FileInputStream(filePath);
            InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8);

            if (Character.isLetter(reader.read())){
                throw new IOException();
            }

        } catch (FileNotFoundException e) {
            throw new MissingLanguageFileException(lang, e);
        }catch (IOException e){
            throw new BrokenLanguageFileException(lang, e);
        }finally {
            close(is);
        }
    }


    private static void close(FileInputStream is) {
        if (is == null) {
            return;
        }
        try {
            is.close();
        } catch (IOException ignore) {}
    }

    private String dozens(Integer number, Properties properties){

        String result;

        String valueString = String.valueOf(number);
        String firstNum = String.valueOf(valueString.charAt(0));
        String secondNum = String.valueOf(valueString.charAt(1));

        String possibleDozen = firstNum + "0";

        if (properties.containsKey(valueString)) {
            result = properties.getProperty(valueString);

        }else if (properties.containsKey(possibleDozen)) { // if dozen is defined in properties

            if (firstNum.equals("1")){ // if it's first dozen (10-19)
                result = properties.getProperty(secondNum) + properties.getProperty("teen");
            }else{
                result = properties.getProperty(possibleDozen) + properties.getProperty("tens-after-delimiter") + properties.getProperty(secondNum);
            }

        }else{
            result = properties.getProperty(firstNum) + properties.getProperty("tens-suffix");

            if (!secondNum.equals("0")){
                result += properties.getProperty("tens-after-delimiter") + properties.getProperty(secondNum);
            }
        }

        return result;
    }

    private String hundreds(Integer number, Properties properties){

        String result;
        String valueString = String.valueOf(number);
        String firstNum = String.valueOf(valueString.charAt(0));
        String secondNum = String.valueOf(valueString.charAt(1));
        String thirdNum = String.valueOf(valueString.charAt(2));
        String dozensNum = secondNum + thirdNum;

        result = properties.getProperty(firstNum) + properties.getProperty("hundreds-before-delimiter") +
                properties.getProperty("hundred");

        if (Integer.parseInt(thirdNum) < 10 && secondNum.equals("0") && Integer.parseInt(thirdNum) != 0 ){ // if less than 10 (101-109)
            result += properties.getProperty("hundreds-after-delimiter") + properties.getProperty(thirdNum);
        }else if (Integer.parseInt(dozensNum) > 9){ // if more than 9 (110-...)
            result += properties.getProperty("hundreds-after-delimiter") + dozens(Integer.valueOf(dozensNum), properties);
        }

        return result;
    }

    public String numberInWords(Integer number) {
        try {
            String result;
            result = "";

            FileInputStream is = new FileInputStream(filePath);
            InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8);


            Properties properties = new Properties();
            properties.load(reader);

            // checking essential ints for lang
            for (int i = 0; i < 10; i++){
                if (!properties.containsKey(String.valueOf(i))){
                    throw new MissingTranslationException(lang);
                }
            }

            String valueString = String.valueOf(number);

            if (properties.containsKey(valueString)){
                result = properties.getProperty(valueString);
            }else{
                if (valueString.length() == 2){
                    result = dozens(number, properties);
                }else if (valueString.length() == 3){
                    result = hundreds(number, properties);
                }
            }

            return result;
        }catch (IOException e){
            return null;
        }

    }
}
