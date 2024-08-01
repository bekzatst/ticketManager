package com.danliuk.validation;

import com.danliuk.model.BusTicket;
import lombok.Getter;

import java.time.LocalDate;

public class TicketValidator {
    @Getter
    private static int invalidDate = 0;
    @Getter
    private static int invalidType = 0;
    @Getter
    private static int invalidPrice = 0;
    @Getter
    private static int totalInvalid = 0;

    public static void validateTicket(BusTicket busTicket){
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
    }

}
