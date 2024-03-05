package assessment.parkinglot.dto;

import java.util.Date;

public class SlotVehicleDto {
    private Long id;
    private SlotDto slotDto;
    private VehicleDto vehicleDto;
    private Date releaseDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SlotDto getSlotDto() {
        return slotDto;
    }

    public void setSlotDto(SlotDto slotDto) {
        this.slotDto = slotDto;
    }

    public VehicleDto getVehicleDto() {
        return vehicleDto;
    }

    public void setVehicleDto(VehicleDto vehicleDto) {
        this.vehicleDto = vehicleDto;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}
