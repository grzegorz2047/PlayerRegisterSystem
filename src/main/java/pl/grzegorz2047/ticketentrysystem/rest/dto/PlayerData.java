package pl.grzegorz2047.ticketentrysystem.rest.dto;

public class PlayerData {

    private String username;
    private String token;

    public PlayerData(String token, String username) {
        this.token = token;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }
}
