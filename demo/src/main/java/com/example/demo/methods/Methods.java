package com.example.demo.methods;

import com.example.demo.model.Duelist;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Methods {

    public static List<Duelist> createListOfDuelist(){
        List<Duelist> duelists = new ArrayList<>();
        int duelistCounter = 1;
        boolean finish = false;

        while (finish == false){
            String duelistName = "";
            double accuracy = 0;

            boolean next0 = false;
            while (next0 == false){
                System.out.println("Please type name of your " + duelistCounter + ". duelist:");
                Scanner inputName = new Scanner(System.in);
                duelistName = inputName.nextLine();
                String nameCheck = duelistName;
                long checkUnique = duelists.stream()
                        .filter(x -> x.getName().equals(nameCheck))
                        .count();
                if (checkUnique > 0){
                    System.out.println("Name must be unique (can not match other duelists name)... Try again!");
                } else {
                    next0 = true;
                }
            }

            System.out.println("Please type " + duelistName + "'s accuracy (0 < accuracy < 1 ):");
            boolean next1 = false;
            while (next1 == false) {
                String accuracyString;
                Scanner inputAccuracy = new Scanner(System.in);
                accuracyString = inputAccuracy.nextLine();
                try {
                    accuracy = Double.parseDouble(accuracyString);
                    double accuracyCheck = accuracy;
                    long checkUnique = duelists.stream()
                            .filter(x -> x.getAccuracy() == accuracyCheck)
                            .count();
                    if (accuracy <= 0) {
                        System.out.println("Accuracy must be bigger than zero... Try again!");
                    } else if (accuracy >= 1){
                        System.out.println("Accuracy must be lower than one... Try again!");
                    } else if (checkUnique > 0){
                        System.out.println("Accuracy must be unique (can not match other duelists accuracy)... Try again!");
                    } else {
                        next1 = true;
                    }
                } catch (NumberFormatException nfe){
                    System.out.println("Not valid input, try again! (should look like '0.1234567')");
                }
            }

            Duelist duelist = new Duelist(duelistName, accuracy);
            duelists.add(duelist);

            if (duelistCounter > 1){
                System.out.println("You currently have " + duelistCounter + " duelists, do you wanna add some more? (y/n):");
                Scanner inputAddMore = new Scanner(System.in);
                String addMore = inputAddMore.nextLine();
                boolean continuer = yesOrNoChecker(addMore);
                if (!continuer){
                    finish = true;
                }
            }
            duelistCounter++;
        }

        return duelists;
    }

    public static boolean yesOrNoChecker(String stringToCheck) {
        boolean answer = true;
        boolean next = false;
        String continuer = stringToCheck;
        while (next == false) {
            if (continuer.equals("n")) {
                answer = false;
                next = true;
            } else if (continuer.equals("y")) {
                next = true;
            } else {
                System.out.println("Not valid input, try again! (You should use character 'y' or 'n')");
                Scanner inputContinuer = new Scanner(System.in);
                continuer = inputContinuer.nextLine();
            }
        }
        return answer;
    }

    public static Integer chooseDuelist(int nmbrOfUsers) {
        boolean next = false;
        int userPosition = 0;
        while (next == false) {
            Scanner inputContinuer = new Scanner(System.in);
            String continuer = inputContinuer.nextLine();
            try {
                int integer = Integer.parseInt(continuer);
                if (integer > 0 && integer <= nmbrOfUsers){
                    userPosition = integer;
                    next = true;
                };
            } catch (NumberFormatException nfe){
                System.out.println("Not valid input, try again! (should look like '1')");
            }
        }
        return userPosition;
    }

    public static Integer numberOfTests() {
        int tests = 0;
        while (tests == 0) {
            Scanner inputContinuer = new Scanner(System.in);
            String continuer = inputContinuer.nextLine();
            try {
                int integer = Integer.parseInt(continuer);
                if (integer > 0){
                    tests = integer;
                };
            } catch (NumberFormatException nfe){
                System.out.println("Not valid input, try again! (must be integer bigger than 0 => should look like '9999')");
            }
        }
        return tests;
    }

    public static void pressEnterToContinue(){
        System.out.println("Press enter to continue....");
        Scanner input = new Scanner(System.in);
        String continuer = input.nextLine();
        return;
    }

    public static double battle(Duelist duelist1, int nmbrOfTests){
        double counter1 = 0;
        for (int i = 0; i < nmbrOfTests; i++) {
            double result1 = Math.random();
            if (result1 <= duelist1.getAccuracy()) {
                counter1++;
            }
        }
        double battleResults = counter1/nmbrOfTests;
        return battleResults;
    }

    public static boolean shot(double accuracy){
        double result1 = Math.random();
        if (result1 <= accuracy) {
            return true;
        } else {
            return false;
        }
    }

}
