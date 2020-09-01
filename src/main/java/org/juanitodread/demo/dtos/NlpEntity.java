package org.juanitodread.demo.dtos;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

public class NlpEntity implements Serializable {
    private UUID uuid;
    private String name;
    private String type;
    private boolean enabled;
    private Object definition;
    private Timestamp created;
    private Timestamp updated;


    public NlpEntity() {
    }

    public NlpEntity(UUID uuid, String name, String type, boolean enabled, Object definition) {
        this.uuid = uuid;
        this.name = name;
        this.type = type;
        this.enabled = enabled;
        this.definition = definition;
    }

    public NlpEntity(UUID uuid, String name, String type, boolean enabled, Object definition, Timestamp created, Timestamp updated) {
        this.uuid = uuid;
        this.name = name;
        this.type = type;
        this.enabled = enabled;
        this.definition = definition;
        this.created = created;
        this.updated = updated;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Object getDefinition() {
        return definition;
    }

    public void setDefinition(Object definition) {
        this.definition = definition;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "NlpEntity{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", enabled=" + enabled +
                ", definition='" + definition + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
