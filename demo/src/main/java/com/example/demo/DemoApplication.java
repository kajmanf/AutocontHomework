package com.example.demo;

import com.example.demo.methods.Methods;
import com.example.demo.model.Duelist;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        System.out.println("Welcome to Winning probability app!");

        ////
        System.out.println("Are you ready to start? (y/n):");
        Scanner inputAppStart = new Scanner(System.in);
        String appStarter = inputAppStart.nextLine();
        boolean continuer = Methods.yesOrNoChecker(appStarter);
        ////

        /*
        List<Duelist> duelists = Methods.createListOfDuelist();
        */
        ///////////
        List<Duelist> duelists = new ArrayList<>();
        duelists.add(new Duelist("Adam", 0.556456));
        duelists.add(new Duelist("Eva", 0.1));
        duelists.add(new Duelist("Jirka", 0.45121455));
        ///////////


        System.out.println("Your list of duelists:");
        int duelistPosition = 1;
        for (Duelist duelist : duelists) {
            System.out.println(duelistPosition +". " + duelist.getName() + " with " + duelist.getAccuracy()*100 + "% accuracy.");
            duelistPosition++;
        }

        List<Duelist> duelistsSorted = duelists.stream()
                .sorted(Comparator.comparing(Duelist::getAccuracy))
                .collect(Collectors.toList());

        System.out.println("Choose duelist you wanna test (by position number of your list):");
        int userToTest = Methods.chooseDuelist(duelistsSorted.size());
        System.out.println("You have chosen " + duelists.get(userToTest-1).getName());

        System.out.println("Choose number of tests you wanna make for each availible option:");
        int numberOfTests = Methods.numberOfTests();
        System.out.println("We are goonna make " + numberOfTests + " tests for each of " + (duelistsSorted.size()-1) + " options.");

        System.out.println(".........................................................................................");
        System.out.println("May we procede to start testing? (y/n):");
        Scanner inputTestStarter = new Scanner(System.in);
        String startTests = inputTestStarter.nextLine();
        boolean continuer2 = Methods.yesOrNoChecker(startTests);

        String duelOrder = "Duelists are gonna duel in this order: ";
        for (Duelist duelist : duelists) {
            duelOrder = duelOrder + duelist.getName() + " > ";
        }
        String duelOrder2 = duelOrder.substring(0,duelOrder.length()-2);
        System.out.println(duelOrder2);

        Methods.pressEnterToContinue();

        for (int i = 0; i < duelists.size(); i++) {
            
        }

        int[] results = Methods.battle(duelistsSorted.get(0), duelistsSorted.get(1), numberOfTests);
        System.out.println("Results are: " + results[0]);

















        System.out.println("xxxxx");
    }
}
