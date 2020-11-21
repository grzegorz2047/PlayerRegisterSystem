package pl.grzegorz2047.ticketentrysystem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import pl.grzegorz2047.ticketentrysystem.database.Ticket;
import pl.grzegorz2047.ticketentrysystem.rest.PlayerTicketController;
import pl.grzegorz2047.ticketentrysystem.rest.dto.PlayerData;

@SpringBootTest
class TicketEntrySystemApplicationTests {

	@Autowired
	PlayerTicketController controller;

	@Test
	void contextLoads() {
//		ResponseEntity<Ticket> ticketResponseEntity = controller.requestTicket(new PlayerData("abx", "grzegorz2047"), null);

	}

}
