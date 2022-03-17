package com.example.demo;

import com.example.demo.methods.Methods;
import com.example.demo.model.Duelist;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        System.out.println("Welcome to Winning probability app!");

        int duelistCounter = 0;

        List<Duelist> duelists = Methods.createListOfDuelist();

        System.out.println("Your list of duelists:");
        for (Duelist duelist : duelists) {
            System.out.println(duelist.getName() + " with " + duelist.getAccuracy() + " accuracy.");
        }


        System.out.println("xxxxx");
        /*System.out.println("Please type your name:");
        Scanner input = new Scanner(System.in);
        String username = input.nextLine();
        System.out.println("Hello " + username + "!");*/
    }
}
