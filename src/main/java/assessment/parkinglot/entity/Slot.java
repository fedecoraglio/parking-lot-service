package assessment.parkinglot.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name="slots")
public class Slot {

    @Id
    @GeneratedValue
    private Long id;
    @Column()
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="slot_type_id")
    private SlotType slotType;
    @Column()
    private Boolean active;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at", length = 23)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_at", length = 23)
    private Date updatedAt;

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

    public SlotType getSlotType() {
        return slotType;
    }

    public void setSlotType(SlotType slotType) {
        this.slotType = slotType;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
