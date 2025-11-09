package org.example.eventsystemlab05.Controller;

import org.example.eventsystemlab05.Api.ApiResponse;
import org.example.eventsystemlab05.Model.Event;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/event")
public class EventController {
    ArrayList<Event> events = new ArrayList<>();


    @GetMapping("/get")
    public ArrayList<Event> getEvents(){
        return events;
    }


@PostMapping("/add")
    public ApiResponse addEvents(@RequestBody Event event){
        events.add(event);
        return  new ApiResponse("Event is added successfully");
    }
@PutMapping("/update/{index}")
    public  ApiResponse updateEvent(@PathVariable int index ,@RequestBody Event event){
        Event eve = events.get(index);
           eve.setCapacity(event.getCapacity());
           eve.setDescription(event.getDescription());
           eve.setStartDate(event.getStartDate());
           eve.setEndDate(event.getEndDate());

return new ApiResponse("event has been updated");
    }

@DeleteMapping("/delete/{index}")
public ApiResponse deleteEvent(@PathVariable int index){
events.remove(index);
return  new ApiResponse("Event deleted successfully ");
}

@PutMapping("/change-capacity/{index}/{capacity}")
public  ApiResponse changeCapacity(@PathVariable int index ,@PathVariable int capacity){
        Event event = events.get(index);
       if (capacity>0){
           event.setCapacity(capacity);
           return new ApiResponse("Capacity of Event: "+event.getDescription() +" is updated to be:"+ event.getCapacity());
       }else {
           return new ApiResponse("the Capacity must be greater than zero");
       }
}
@GetMapping("/search-event/{id}")
public  ApiResponse searchEvent(@PathVariable int id){
        for (Event e : events){
            if(e.getID()==id){
                return new ApiResponse("Event found: "+e.toString());
            }

        }
    return  new ApiResponse("Event not found!");



}









}
