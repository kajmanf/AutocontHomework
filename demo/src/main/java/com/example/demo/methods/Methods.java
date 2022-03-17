package com.example.demo.methods;

import com.example.demo.model.Duelist;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
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
                    System.out.println("Not valid input, try again! (should look like 0.1234567)");
                }
            }

            Duelist duelist = new Duelist(duelistName, accuracy);
            duelists.add(duelist);

            if (duelistCounter > 1){
                System.out.println("You currently have " + duelistCounter + " duelists, do you wanna add some more? (y/n):");
                boolean next2 = false;
                while (next2 == false) {
                    Scanner inputAddMore = new Scanner(System.in);
                    String addMore = inputAddMore.nextLine();
                    if (addMore.equals("n")) {
                        finish = true;
                        next2 = true;
                    } else if (addMore.equals("y")){
                        next2 = true;
                    } else {
                        System.out.println("Not valid input, try again! (You should use character 'y' or 'n')");
                    }
                }
            }
            duelistCounter++;
        }

        List<Duelist> duelistsSorted = duelists.stream()
                .sorted(Comparator.comparing(Duelist::getAccuracy))
                .collect(Collectors.toList());

        return duelistsSorted;
    }



}
