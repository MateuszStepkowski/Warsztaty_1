package pl.coderslab.dicesThrowGenerator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class dicesThrowGenerator {

    public static void main(String[] args){

        System.out.println(diceThrowResult());

    }

    static int diceThrowResult(){

        ArrayList<Integer> dices = new ArrayList<>();
        dices.add(3);
        dices.add(4);
        dices.add(6);
        dices.add(8);
        dices.add(10);
        dices.add(12);
        dices.add(20);
        dices.add(100);

        Scanner scan = new Scanner(System.in);
        String diceCode="";

        System.out.println("Enter code of dice throw according to 'xDy+z' pattern, where:\n" +
                "x - number of throws or number of dices to be thrown \n" +
                "Dy - type of the dice, for example D6 means cubic dice\n" +
                "+z/-z - (optional) number to be added or substracted from the result");

        while (! diceCode.contains("D")) {
            diceCode = scan.nextLine().toUpperCase().replaceAll(" ", "");
            if (!diceCode.contains("D")) System.out.println("invalid format of dice throw parameters." +
                                                            " Read explanation of pattern and try again: ");
        }
        String tmpSubstring;
        int[] xyz = new int[3];

        //catch 'x' value

        int indexD = diceCode.indexOf('D');
        tmpSubstring = diceCode.substring(0, indexD);
        if (tmpSubstring.length() == 0) xyz[0] = 1;
        else {
            try { xyz[0] = Integer.parseInt(tmpSubstring);
            }catch (NumberFormatException e){
                    while (true){
                    System.out.println("Invalid format for 'x' parameter. Please enter 'x' value again: ");
                        try {
                            xyz[0] = Integer.parseInt(scan.nextLine().trim());
                            break;
                        }catch (NumberFormatException ex){}
                        }
                    }
                }

        //catch y value

        int indexPlusMinus;
        if (diceCode.indexOf('+') == diceCode.indexOf('-')) indexPlusMinus = diceCode.length();
        else if (diceCode.indexOf('+') > diceCode.indexOf('-')) indexPlusMinus = diceCode.indexOf('+');
        else indexPlusMinus = diceCode.indexOf('-');

        tmpSubstring = diceCode.substring(indexD+1,indexPlusMinus);


        try { xyz[1] = Integer.parseInt(tmpSubstring);
        }catch (NumberFormatException e){
            while (true){
                System.out.println("Invalid format for 'y' parameter. Please enter 'y' value again: ");
                try {
                    xyz[1] = Integer.parseInt(scan.nextLine().trim());
                    break;
                }catch (NumberFormatException ex){}
            }
        }
        while (! dices.contains(xyz[1])){
            System.out.println("Nonexistent type of dice.\n" +
                    "Avalible sizes of dice: 3, 4, 6, 8, 10, 12, 20, 100\n" +
                    "Please enter 'y' parameter again: ");

            while (true){
                try {
                    xyz[1] = Integer.parseInt(scan.nextLine().trim());
                    break;
                }catch (NumberFormatException ex){
                    System.out.println("Invalid format for 'y' parameter. Please enter 'y' value again: ");
                }
            }
        }

        //catch 'z' value

        if (indexPlusMinus == -1) xyz[2] = 0;
        else{
            tmpSubstring = diceCode.substring(indexPlusMinus, diceCode.length());
            try {
                xyz[2] = Integer.parseInt(tmpSubstring);
            }catch (NumberFormatException e){
                while (true) {
                    System.out.println("Invalid format for 'z' parameter. Please enter 'z' value again: ");
                    try {
                        xyz[2] = Integer.parseInt(scan.nextLine().trim());
                        break;
                    } catch (NumberFormatException ex) {
                    }
                }
                }
            }

        //generate x-number of throws ale return result

        Random rand = new Random();
        int sum =0;

        for (int j=0; j < xyz[0]; j++) sum += rand.nextInt(xyz[1])+1;

        return sum + xyz[2];

        }
    }
