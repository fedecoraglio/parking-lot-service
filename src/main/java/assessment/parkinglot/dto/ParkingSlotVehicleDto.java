package assessment.parkinglot.dto;

public class ParkingSlotVehicleDto {

    private String vehicleVim;
    private String vehicleTypeId;

    public String getVehicleVim() {
        return vehicleVim;
    }

    public void setVehicleVim(String vehicleVim) {
        this.vehicleVim = vehicleVim;
    }

    public String getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(String vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }
}
