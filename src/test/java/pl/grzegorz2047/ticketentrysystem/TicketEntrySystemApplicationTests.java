package pl.grzegorz2047.ticketentrysystem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.grzegorz2047.ticketentrysystem.rest.PlayerTicketController;
import pl.grzegorz2047.ticketentrysystem.rest.dto.PlayerData;

@SpringBootTest
class TicketEntrySystemApplicationTests {

	@Autowired
	PlayerTicketController controller;

	@Test
	void contextLoads() {
		controller.requestTicket(new PlayerData("abx","grzegorz2047"));
	}

}
