package pl.grzegorz2047.ticketentrysystem.database.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.grzegorz2047.ticketentrysystem.database.Ticket;

import java.util.Optional;

public interface TicketRepository extends MongoRepository<Ticket, String> {
    public Optional<Ticket> findByUsername(String username);
}
