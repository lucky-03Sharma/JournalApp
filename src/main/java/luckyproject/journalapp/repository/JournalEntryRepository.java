package luckyproject.journalapp.repository;

import luckyproject.journalapp.entity.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, Object>{
}
