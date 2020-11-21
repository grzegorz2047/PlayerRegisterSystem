package pl.grzegorz2047.ticketentrysystem.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.cache.interceptor.SimpleCacheErrorHandler;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.grzegorz2047.ticketentrysystem.database.Ticket;
import pl.grzegorz2047.ticketentrysystem.database.repository.TicketRepository;
import pl.grzegorz2047.ticketentrysystem.rest.dto.PlayerData;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ExecutionException;

@RestController()
@RequestMapping("/ticket")
public class PlayerTicketController {

    @Value("${service.cors-origin}")
    private final String dedicatedUrl = "http://register.mc-walls.pl";
    @Value("${google.priv-key}")

    private String privKey;

    @Value("${google.url}")
    private String googleUrl;

    @Autowired
    private TicketRepository repository;
    private SimpleCache simpleCache;

    @CrossOrigin(origins = dedicatedUrl)
    @PostMapping("/create")
    public ResponseEntity<Ticket> requestTicket(@RequestBody PlayerData data, HttpServletRequest request) {

        String username = data.getUsername();
        String token = data.getToken();
        if(request == null) {
            return ResponseEntity.badRequest().build();
        }
        if (username.isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        if (token.isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        if (repository.findByUsername(username).isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        String remoteAddr = request.getRemoteAddr();
        System.out.println(remoteAddr);
        boolean canAttempt = simpleCache.canAttempt(remoteAddr);
        if(!canAttempt) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
        }
        try {
            if (!isUserValidated(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (JSONException e) {
            System.out.println(data.getUsername() + " " + data.getToken());
            e.getStackTrace();
        }
        Ticket ticket = new Ticket();
        ticket.setUsername(username);
        repository.save(ticket);
        simpleCache.attempt(remoteAddr);
        return ResponseEntity.ok().build();
    }

    private boolean isUserValidated(String token) throws JSONException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String url = UriComponentsBuilder
                .fromUriString(googleUrl).queryParam("secret", privKey).queryParam("response", token).build().encode().toUriString();
        HttpEntity<String> request = new HttpEntity<>("", headers);
        PlayerRegisterValidationDataResponse result = restTemplate.postForObject(url, request, PlayerRegisterValidationDataResponse.class);
        System.out.println("============ " + result);
        return result.isSuccess();
    }
}
