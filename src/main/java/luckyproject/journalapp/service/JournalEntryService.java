package luckyproject.journalapp.service;

import luckyproject.journalapp.entity.JournalEntry;
import luckyproject.journalapp.entity.User;
import luckyproject.journalapp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    public void saveEntry(JournalEntry journalEntry, String userName) {
        try {
            User user = userService.findByUsername(userName);

            journalEntry.setDate(LocalDateTime.now());

            JournalEntry savedEntry = journalEntryRepository.save(journalEntry);

            user.getJournalEntries().add(savedEntry);

            userService.saveUser(user);

        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occurred while saving journal entry", e);
        }
    }

    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    public boolean deleteById(ObjectId id, String userName) {
        boolean removed = false;
        try {
            User user = userService.findByUsername(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
            }return removed;
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occurred while deleting journal entry", e);
        }
    }
}