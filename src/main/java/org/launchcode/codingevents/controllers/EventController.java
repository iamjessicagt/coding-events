package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {

    @GetMapping
    public String displayAllEvents(Model model){
        model.addAttribute("events", EventData.getAll());
        return "events/index";
    }

    //lives at /events/create
    @GetMapping("create")
    public String renderCreateEventForm(){
        return "events/create";
    }


    //allows controller to use model Event to create new Event class, needs to use annotation @ModelAttribute!
    //now the view have to match with the field names of the model class
    @PostMapping("create")
    public String createEvent(@ModelAttribute Event newEvent){
        EventData.addEvent(newEvent);
        return "redirect:";
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model){
        model.addAttribute("title", "Delete Event");
        model.addAttribute("events", EventData.getAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String deleteEvent(@RequestParam(required = false) int[] eventIds){
        if(eventIds != null) {
            for (int id : eventIds) {
                EventData.remove(id);
            }
        }
        return "redirect:";
    }

    @GetMapping("edit/{eventId}") //lives at /events/edit/eventId
    public String displayEditForm(Model model, @PathVariable int eventId) {
        Event event = EventData.getById(eventId);
        model.addAttribute("event", event);
        if(event != null) {
        model.addAttribute("title", "EDIT EVENT: " + event.getName() + " - ID: " + event.getId());
        }
        return "events/edit";
    }

    @PostMapping("edit")
    public String processEditForm(int eventId, String name, String description) {
        Event event = EventData.getById(eventId);
        event.setName(name);
        event.setDescription(description);
        return "redirect:";
    }
}
