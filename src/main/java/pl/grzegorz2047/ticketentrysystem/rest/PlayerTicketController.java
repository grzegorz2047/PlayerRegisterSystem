package pl.grzegorz2047.ticketentrysystem.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pl.grzegorz2047.ticketentrysystem.database.Ticket;
import pl.grzegorz2047.ticketentrysystem.database.repository.TicketRepository;
import pl.grzegorz2047.ticketentrysystem.rest.dto.PlayerData;

@RestController()
@RequestMapping("/ticket")
public class PlayerTicketController {

    @Value("${google.priv-key}")
    private String privKey;

    @Value("${google.url}")
    private String googleUrl;

    @Autowired
    private TicketRepository repository;

    @CrossOrigin(origins = "http://register.mc-walls.pl")
    @PostMapping("/create")
    public ResponseEntity<Ticket> requestTicket(@RequestBody PlayerData data) {

        String username = data.getUsername();
        String token = data.getToken();
        if (username.isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        if (token.isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        if (repository.findByUsername(username).isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        try {
            if (!isUserValidated(token)) {
                return ResponseEntity.badRequest().build();
            }
        } catch (JSONException e) {
            return ResponseEntity.badRequest().build();
        }
        Ticket ticket = new Ticket();
        ticket.setUsername(username);
        repository.save(ticket);
        return ResponseEntity.ok().build();
    }

    private boolean isUserValidated(String token) throws JSONException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject playerValidationData = new JSONObject();
        playerValidationData.put("secret", privKey);
        playerValidationData.put("token", token);
        HttpEntity<String> request =
                new HttpEntity<>(playerValidationData.toString(), headers);
        String result = restTemplate.postForObject(googleUrl, request, String.class);
        if (result == null) {
            return false;
        }
        return !result.isBlank();
    }
}
