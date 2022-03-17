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
        System.out.println("1) you'll add duelists");
        System.out.println("2) you'll enter Duealist to start with");
        System.out.println("3) you'll enter number of simulations you wanna make");
        System.out.println("May we procede to start application? (y/n):");
        Scanner inputAppStart = new Scanner(System.in);
        String appStarter = inputAppStart.nextLine();
        boolean continuer = Methods.yesOrNoChecker(appStarter);

        ///////////////
        //Input filing part:
        ///////////////
        //Create list of duelist
        List<Duelist> duelists = Methods.createListOfDuelist();
        /*
        List<Duelist> duelists = new ArrayList<>();
        duelists.add(new Duelist("Adam", 0.556456));
        duelists.add(new Duelist("Eva", 0.1));
        duelists.add(new Duelist("Jirka", 0.45121455));
        */


        System.out.println("Your list of duelists:");
        int duelistPosition = 1;
        for (Duelist duelist : duelists) {
            System.out.println(duelistPosition +". " + duelist.getName() + " with " + duelist.getAccuracy()*100 + "% accuracy.");
            duelistPosition++;
        }

        //Sort the list by accuracy
        List<Duelist> duelistsSorted = duelists.stream()
                .sorted(Comparator.comparing(Duelist::getAccuracy))
                .collect(Collectors.toList());

        //Choose duelist that you wanna test probability of win for
        System.out.println("Choose duelist you wanna test (by position number of your list):");
        int userToTest = Methods.chooseDuelist(duelistsSorted.size());
        Duelist testedDuelist = duelists.get(userToTest-1);
        System.out.println("You have chosen " + testedDuelist.getName());

        //Choose number of test per each combination of fights
        System.out.println("Choose number of tests you wanna make for each availible option:");
        int numberOfTests = Methods.numberOfTests();
        System.out.println("We are goonna make " + numberOfTests + " tests for each of " + (duelistsSorted.size()-1) + " options.");
        System.out.println(".........................................................................................");
        ///////////////
        //Testing part:
        ///////////////
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

        //break
        Methods.pressEnterToContinue();

        List<Duelist> duelistsToBattle = duelistsSorted.stream()
                .filter(x -> !x.getName().equals(testedDuelist.getName()))
                .collect(Collectors.toList());

        //test single probabilities
        HashMap<String, Double> probabilities = new HashMap<String, Double>();
        System.out.println("Results of single duelists (not considering order) is:");
        for (int i = 0; i < duelistsToBattle.size(); i++) {
            double results = Methods.battle(testedDuelist, numberOfTests);
            probabilities.put(duelistsToBattle.get(i).getName(), results);
            System.out.println(" - Probability of " + testedDuelist.getName() + " hitting "  + duelistsToBattle.get(i).getName() + " is " + results*100 + "%.");
        }

        //test probabilities influenced by order
        int indexOfTestedDuelistInSortedList = duelistsSorted.indexOf(testedDuelist);
        for (int j = 0; j < duelistsSorted.size(); j++) {
            if (j != indexOfTestedDuelistInSortedList){
                Duelist duelist = duelistsSorted.get(j);
                int numberOfWins = 0;
                for (int i = 0; i < numberOfTests; i++) {
                    boolean breaker = false;
                    List<Duelist> survivalList = duelistsSorted;
                    survivalList.remove(j);
                    while (breaker == false){
                        int currentIndex = 0;
                        int maxIndex = survivalList.size()-1;
                        if (Methods.shot(duelistsSorted.get(currentIndex).getAccuracy()) == true){
                            int randomIndex = 0;
                            boolean done = false;
                            while (done == false){
                                int index = (int) ((Math.random() * (survivalList.size()-1)));
                                if (index != currentIndex){
                                    survivalList.remove(index);
                                    done = true;
                                }
                            }
                            if (currentIndex != survivalList.size()){
                                currentIndex++;
                            } else {
                                currentIndex = 0;
                            }
                        }
                        long countIfSurvivestested = survivalList.stream()
                                .filter(x -> x.getName().equals(testedDuelist.getName()))
                                .count();
                        if (countIfSurvivestested == 0){
                            numberOfWins++;
                            breaker = true;
                        }
                        if (survivalList.size() == 1){
                            breaker = true;
                        }
                        System.out.println("test " + i);
                    }
                }
                System.out.println(" - Probability of surviving " + testedDuelist.getName() + " after hitting "  + duelist.getName() + " first is " + numberOfWins/numberOfTests + "%.");
            }
        }
    }
}
