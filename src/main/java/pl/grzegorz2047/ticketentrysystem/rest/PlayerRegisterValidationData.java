package pl.grzegorz2047.ticketentrysystem.rest;

public class PlayerRegisterValidationData {
    private final String privKey;
    private final String token;

    public PlayerRegisterValidationData(String privKey, String token) {
        this.privKey = privKey;
        this.token = token;
    }
}
