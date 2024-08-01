package com.danliuk;

import com.danliuk.model.BusTicket;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {

        int x = 0;
        int invalidDate = 0;
        int invalidType = 0;
        int invalidPrice = 0;
        int totalInvalid = 0;

        try {
            do {
                String input = getInput();
                BusTicket busTicket = new ObjectMapper().readValue(input, BusTicket.class);

                // TODO: ticket validation
                int a = 0;

                if (busTicket.getTicketType()==null || busTicket.getTicketType().isBlank() || (!busTicket.getTicketType().equals("DAY") && !busTicket.getTicketType().equals("WEEK")
                        && !busTicket.getTicketType().equals("MONTH") && !busTicket.getTicketType().equals("YEAR"))) {
                    invalidType++;
                    totalInvalid++;
                    a++;
                }
                if (busTicket.getStartDate()==null || busTicket.getStartDate().isBlank() || LocalDate.parse(busTicket.getStartDate()).isAfter(LocalDate.now())) {
                    if (a == 0) {
                        totalInvalid++;
                        a++;
                    }
                    invalidDate++;
                }
                if (busTicket.getPrice()==null || busTicket.getPrice().isBlank() || Integer.parseInt(busTicket.getPrice())/2 == 1 || Integer.parseInt(busTicket.getPrice())<=0) {
                    if (a == 0) {
                        totalInvalid++;
                    }
                    invalidPrice++;
                }

                System.out.println(busTicket.toString());
                x++;

            } while (x < 5);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

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

    private static String getInput() {
        return new Scanner(System.in).nextLine();
    }
}
