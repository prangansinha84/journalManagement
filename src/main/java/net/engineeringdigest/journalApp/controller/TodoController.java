package net.engineeringdigest.journalApp.controller;

//controller me entity upload krte hai
//import com.sun.tools.javac.comp.Todo;
import net.engineeringdigest.journalApp.entity.TodoEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/todo")

public class TodoController {

    private Map<Long, TodoEntry> TodoEntries = new HashMap<>(); //long: used in programming and database systems

    @GetMapping("/def")
    public List <TodoEntry> getAll(){
        return new ArrayList<>(TodoEntries.values());
    }

    @PostMapping //creating new entries here from client
    public boolean createEntry(@RequestBody TodoEntry te ){
        TodoEntries.put(te.getId(), te);
        return true;
    }

    @GetMapping("id/{myId}")
    //this myId inside bracket comes from the URL path itself
    public TodoEntry getTodoEntryById(@PathVariable Long id){
        return TodoEntries.get(id);
    }

    @DeleteMapping("/id/{delid}")
        public TodoEntry deleteElementfromtodo(@PathVariable Long delid){
            return TodoEntries.remove(delid);
        }


    @PutMapping("/id/{id}")
        public TodoEntry changeElement(@PathVariable long id, @RequestBody TodoEntry te) {

        te.setId(id);
        TodoEntries.put(id, te);
        return TodoEntries.get(id);

    }
}













