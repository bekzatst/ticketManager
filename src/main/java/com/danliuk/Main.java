package com.danliuk;

import com.danliuk.model.BusTicket;
import com.danliuk.validation.TicketValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {

        int x = 0;
        int invalidDate;
        int invalidType;
        int invalidPrice;
        int totalInvalid;
        String filePath = "src/main/resources/ticketData.txt";

        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            do {
                String input = scanner.nextLine();
                BusTicket busTicket = new ObjectMapper().readValue(input, BusTicket.class);

                // TODO: ticket validation
                TicketValidator.validateTicket(busTicket);

                System.out.println(busTicket.toString());
                x++;

            } while (x < 10);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        invalidType=TicketValidator.getInvalidType();
        invalidDate=TicketValidator.getInvalidDate();
        invalidPrice=TicketValidator.getInvalidPrice();
        totalInvalid=TicketValidator.getTotalInvalid();

        System.out.println("Total tickets: " + x);
        System.out.println("Valid tickets: " + (x-totalInvalid));

        if (totalInvalid>0){
            if (invalidDate >= invalidPrice && invalidDate >= invalidType) {
                System.out.println("Most popular violation is \"start date\"");
            } else if (invalidPrice >= invalidDate && invalidPrice >= invalidType) {
                System.out.println("Most popular violation is \"price\"");
            } else {
                System.out.println("Most popular violation is \"ticket type\"");
            }
        }
    }
}
