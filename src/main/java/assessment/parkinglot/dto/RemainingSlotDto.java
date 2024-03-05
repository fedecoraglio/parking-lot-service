package assessment.parkinglot.dto;

public class RemainingSlotDto {
    private String slotType;
    private Long remaining;

    public RemainingSlotDto() {
    }

    public RemainingSlotDto(String slotType, Long remaining) {
        this.slotType = slotType;
        this.remaining = remaining;
    }

    public String getSlotType() {
        return slotType;
    }

    public void setSlotType(String slotType) {
        this.slotType = slotType;
    }

    public Long getRemaining() {
        return remaining;
    }

    public void setRemaining(Long remaining) {
        this.remaining = remaining;
    }
}
