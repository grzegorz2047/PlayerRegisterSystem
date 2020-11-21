package pl.grzegorz2047.ticketentrysystem.database;

import org.springframework.data.mongodb.core.mapping.MongoId;

public class Ticket {

    @MongoId private String id;
    private String username;
    private String uuid;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
