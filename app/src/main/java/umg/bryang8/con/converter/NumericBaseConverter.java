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

    private String procedimiento = "";

    private BASE baseOriginal,baseDeseada;

    public String getProcedimiento(){
        return procedimiento;
    }

    public String convert(String number, Integer typeOriginal, Integer typeDeseado){
        setOriginal(typeOriginal);
        setBaseDeseada(typeDeseado);
        procedimiento = "";

        number = convertToBase(convertToDecimal(number.toUpperCase()));
        //System.out.println(procedimiento);
        return number;
    }

    public void setOriginal(Integer type){
        switch (type){
            case 10:
                baseOriginal = BASE.DECIMAL;
                break;
            case 2:
                baseOriginal = BASE.BINARIA;
                break;
            case 8:
                baseOriginal= BASE.OCTAGONAL;
                break;
            case 16:
                baseOriginal = BASE.HEXADECIMAL;
                break;
        }
    }

    public void setBaseDeseada(Integer type){
        switch (type){
            case 10:
                baseDeseada = BASE.DECIMAL;
                break;
            case 2:
                baseDeseada = BASE.BINARIA;
                break;
            case 8:
                baseDeseada= BASE.OCTAGONAL;
                break;
            case 16:
                baseDeseada = BASE.HEXADECIMAL;
                break;
        }
    }

    public long convertToDecimal(String number){
        try{

            switch (baseOriginal){
                case BINARIA:
                    procedimiento += "Convirtiendo de binario a decimal. \n";
                    return (number.matches("[0-1]+")) ? baseToDecimal(number, 2) : -1;
                case OCTAGONAL:
                    procedimiento += "Convirtiendo de octal a decimal. \n";
                    return (number.matches("[0-7]+")) ? baseToDecimal(number, 8) : -1;
                case DECIMAL:
                    procedimiento += "Convirtiendo de decimal a decimal. \n";
                    return (number.matches("[0-9]+")) ? Long.parseLong(number) : -1;
                case HEXADECIMAL:
                    procedimiento += "Convirtiendo de hexadecimal a decimal. \n";
                    return (number.matches("[0-9A-F]+")) ? baseToDecimal(number, 16) : -1;
            }
        }
        catch (NumberFormatException ex){
            return -1;
        }
        return -1;
    }

    public String convertToBase(long number){
        if (number == -1){
            return null;
        }
        switch (baseDeseada){
            case BINARIA:
                procedimiento += "Convirtiendo de decimal a binario. \n";
                return decimalToBase(number, 2, "Binario");
            case OCTAGONAL:
                procedimiento += "Convirtiendo de decimal a octal. \n";
                return decimalToBase(number, 8, "Octal");
            case DECIMAL:
                procedimiento += "Convirtiendo de decimal a decimal. \n";
                return String.valueOf(number);
            case HEXADECIMAL:
                procedimiento += "Convirtiendo de decimal a hexadecimal. \n";
                return decimalToBase(number, 16, "Hexadecimal");
        }
        return null;
    }

    public long baseToDecimal(String number, Integer base){
        procedimiento += "Decimal = 0 \n";
        int result = 0;
        for (int i = 0; i<number.length();i++){
            procedimiento += "Decimal = " + result + " + (";
            int temp = getNumber(number.charAt(i));
            procedimiento += temp + " x "+base+"^"+(number.length() -i - 1)+")\n";
            temp *= Math.pow(base,number.length() -i - 1);
            result += temp;
        }
        procedimiento += "Decimal = " +result + "\n";
        return result;
    }

    public String decimalToBase(long number, Integer base, String _base){
        String result = "";
        long sobra;
        char [] digits = Arrays.copyOfRange(array, 0, base );
        procedimiento += "Dígitos = {" + Arrays.toString(digits) + "}\n";
        procedimiento += _base + " = 0\n";

        while(number>0)
        {
            procedimiento += "\tnúmero = " + number + "\n";
            sobra  = number % base;

            procedimiento += "\tsobra = residuo de " +number + "/" +base+"\n";
            procedimiento += "\tsobra = "+sobra+"\n";

            procedimiento += "\t" +_base + " = " + "digito: " + sobra;
            procedimiento += (!result.isEmpty()) ? " concatenado: " +  result + "\n" : "\n";
            procedimiento += _base + " = " + digits[(int)sobra] +  result + "\n";

            result = digits[(int)sobra] + result;
            number = number / base;

            procedimiento += (number != 0) ? "\tnumero = "+number+" / "+base+"\n" : "";
        }
        //System.out.println(result);
        return result;
    }

    public Integer getNumber(char number){
        return new String(array).indexOf(number);
    }

}
