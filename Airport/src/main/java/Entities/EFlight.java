/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor

public class EFlight {
    private int idFlight;
    private String origin;
    private String destiny;
    private String departureTime;
    private String expectedDepartureTime;
    private String arrivalTime;
    private String expectedArrivalTime;
    private String airline;
    private String binnacle;
    private String status;
    private int airplane;
}
