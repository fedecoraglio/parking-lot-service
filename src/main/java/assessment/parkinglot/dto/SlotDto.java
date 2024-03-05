package assessment.parkinglot.dto;

import assessment.parkinglot.entity.SlotType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

public class SlotDto {
    private Long id;
    private String name;
    private SlotTypeDto slotType;
    private Boolean active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SlotTypeDto getSlotType() {
        return slotType;
    }

    public void setSlotType(SlotTypeDto slotType) {
        this.slotType = slotType;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
