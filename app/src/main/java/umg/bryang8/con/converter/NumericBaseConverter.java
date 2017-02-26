package umg.bryang8.con.converter;

import java.util.Arrays;

/**
 * Created by bryan_g8 on 25/02/17.
 */
public class NumericBaseConverter {

    private char [] array = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

    public enum BASE {
        DECIMAL, BINARIA, OCTAGONAL, HEXADECIMAL
    }

    private BASE baseOriginal,baseDeseada;

    public String convert(String number, Integer typeOriginal, Integer typeDeseado){
        setOriginal(typeOriginal);
        setBaseDeseada(typeDeseado);

        number = convertToBase(convertToDecimal(number.toUpperCase()));
        return number;
    }

    public void setOriginal(Integer type){
        switch (type){
            case 1:
                baseOriginal = BASE.DECIMAL;
                break;
            case 2:
                baseOriginal = BASE.BINARIA;
                break;
            case 3:
                baseOriginal= BASE.OCTAGONAL;
                break;
            case 4:
                baseOriginal = BASE.HEXADECIMAL;
                break;
        }
    }

    public void setBaseDeseada(Integer type){
        switch (type){
            case 1:
                baseDeseada = BASE.DECIMAL;
                break;
            case 2:
                baseDeseada = BASE.BINARIA;
                break;
            case 3:
                baseDeseada= BASE.OCTAGONAL;
                break;
            case 4:
                baseDeseada = BASE.HEXADECIMAL;
                break;
        }
    }

    public long convertToDecimal(String number){
        switch (baseOriginal){
            case BINARIA:
                return (number.matches("[0-1]+")) ? baseToDecimal(number, 2) : -1;
            case OCTAGONAL:
                return (number.matches("[0-7]+")) ? baseToDecimal(number, 8) : -1;
            case DECIMAL:
                return (number.matches("[0-9]+")) ? Long.parseLong(number) : -1;
            case HEXADECIMAL:
                return (number.matches("[0-9A-F]+")) ? baseToDecimal(number, 16) : -1;
        }
        return -1;
    }

    public String convertToBase(long number){
        if (number == -1){
            return null;
        }
        switch (baseDeseada){
            case BINARIA:
                return decimalToBase(number, 2);
            case OCTAGONAL:
                return decimalToBase(number, 8);
            case DECIMAL:
                return String.valueOf(number);
            case HEXADECIMAL:
                return decimalToBase(number, 16);
        }
        return null;
    }

    public long baseToDecimal(String number, Integer base){
        int result = 0;
        for (int i = 0; i<number.length();i++){
            result +=  getNumber(number.charAt(i)) * Math.pow(base,number.length() -i - 1);
        }
        return result;
    }

    public String decimalToBase(long number, Integer base){
        String result = "";
        long sobra;
        char [] digits = Arrays.copyOfRange(array, 0, base );

        while(number>0)
        {
            sobra  = number % base;
            result = digits[(int)sobra] + result;
            number = number / base;
        }

        //System.out.println(result);
        return result;
    }

    public Integer getNumber(char number){
        return new String(array).indexOf(number);
    }
}
