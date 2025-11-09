package org.example.eventsystemlab05.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Event {

    private  int ID;
    private String description;
    private  int capacity;
    @JsonFormat(pattern="yyyy-MM-dd")
private LocalDate startDate ;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate endDate;



}
