package net.engineeringdigest.journalApp.service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service


@Component
public class UserService {

    @Autowired
    private UserRepository Userrepository;

    public void saveEntry(User user){
       Userrepository.save(user);
    }

    public List<User> getAll(){
        return Userrepository.findAll();
    }

   //basically:
   //controller --> service -->repository -->databasee

    public Optional<User> findById(@NonNull ObjectId id){
        return Userrepository.findById(id);
    }

    public void deleteById(ObjectId id){
        Userrepository.deleteById(id);
    }

    public Optional<User> findByUserName(String username){
        return Userrepository.findByUserName(username);
    }
}
