package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service


@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;



    public void saveEntry(JournalEntry journalentry){
        JournalEntry JournalEntry = null;
        journalEntryRepository.save(JournalEntry);

    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

   //basically:
   //controller --> service -->repository -->databasee

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName) {
        Optional<User> userOpt = userService.findByUserName(userName);
        if (userOpt.isPresent()) { User user = userOpt.get();
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            if (user.getJournalEntries() == null) {
                user.setJournalEntries(new ArrayList<>()); }
            user.getJournalEntries().add(saved);
            //user.setUserName(null);
            userService.saveEntry(user); } }

    public void deleteById(ObjectId id, String userName){
        Optional<User> user = userService.findByUserName(userName);
        user.get().getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user.orElse(null));
        journalEntryRepository.deleteById(id);
    }

    public void saveEntry(JournalEntry myEntry, User user) {

    }
}
