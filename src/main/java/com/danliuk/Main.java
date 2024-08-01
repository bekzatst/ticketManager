package com.danliuk;

import com.danliuk.model.BusTicket;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {

        int x = 0;

        do {
            String input = getInput();
            BusTicket busTicket = new ObjectMapper().readValue(input, BusTicket.class);

            // TODO: ticket validation

            System.out.println(busTicket.toString());
            x++;

        } while (x < 5);
    }

    private static String getInput() {
        return new Scanner(System.in).nextLine();
    }
}
