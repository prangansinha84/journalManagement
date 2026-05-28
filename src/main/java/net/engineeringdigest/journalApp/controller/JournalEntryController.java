package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("/{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName) {

        Optional<User> userOpt = userService.findByUserName(userName);

        if (userOpt.isPresent()) {
            User user = userOpt.get();

            List<JournalEntry> all = user.getJournalEntries();

            if (all != null && !all.isEmpty()) {
                return new ResponseEntity<>(all, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //posts clients data
    @PostMapping("/{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry,
                                                    @PathVariable String userName) {
        try {
            Optional<User> userOpt = userService.findByUserName(userName);

            if (userOpt.isPresent()) {
                User user = userOpt.get();

                journalEntryService.saveEntry(myEntry,
                        userName);

                return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable ObjectId myId) {
        Optional<JournalEntry> JournalEntry = journalEntryService.findById(myId);
        if (JournalEntry.isPresent()) {
            return new ResponseEntity<>(JournalEntry.get(), HttpStatus.OK).getBody();
        }

        return null;
    }

    @DeleteMapping("id/{userName}/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId, @PathVariable String userName) {
        journalEntryService.deleteById(myId, userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{userName}/{myId}")
    public ResponseEntity<?> updateJournalById(@PathVariable ObjectId id,
                                               @RequestBody JournalEntry newEntry,
                                               @PathVariable String userName) {

        JournalEntry old = journalEntryService.findById(id).orElse(null);

        if (old != null) {
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("")
                    ? newEntry.getTitle()
                    : old.getTitle());

            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("")
                    ? newEntry.getContent()
                    : old.getContent());

            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

    /*@PutMapping("/id/{id}")
    public JournalEntry updateJournalById(@PathVariable ObjectId id, @RequestBody JournalEntry myEntry) {
        //post mapping ke @reqmapping ka variable hai -> myEntry
        // setId() is used during a PUT request (update) to ensure the entity being updated matches the ID passed in the URL path

        //below we are storing key value
        //IMPortant: bass Id daalne pe saare parameters will come and you can modify them there.
        //now here we  return the updated value. basically put operation done. when we get id, we get its values
        return null;
    }

//QUESTION: why is it ki at some places we use TodoEntry and in some places boolean se ho ja rha hai?*/



