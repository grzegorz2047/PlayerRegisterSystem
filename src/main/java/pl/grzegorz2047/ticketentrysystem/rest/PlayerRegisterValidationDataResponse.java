package pl.grzegorz2047.ticketentrysystem.rest;

import java.util.Date;

public class PlayerRegisterValidationDataResponse {

    private boolean success;
    private Date challenge_ts;
    private String hostname;

    public boolean isSuccess() {
        return success;
    }

    public Date getChallengeTs() {
        return challenge_ts;
    }

    public String getHostname() {
        return hostname;
    }
}
