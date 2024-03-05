package assessment.parkinglot.exception;

public class ParkingLotException extends RuntimeException {

    private final Type type;

    public ParkingLotException(final Type type, Throwable t) {
        super(t);
        this.type = type;
    }

    public ParkingLotException(final Type type) {
        this(type, null);
    }


    public enum Type {
        VEHICLE_TYPE_NOT_FOUND("vehicle.type.is.not.found"),
        SLOT_NOT_AVAILABLE("slot.not.available"),
        SLOT_VEHICLE_ERROR_SAVING("slot.vehicle.error.saving"),
        VEHICLE_TYPE_IS_REQUIRED("vehicle.type.is.required"),
        VEHICLE_NOT_FOUND("vehicle.not.found"),
        VIM_IS_REQUIRED("vim.is.required"),
        VEHICLE_WAS_ALREADY_RELEASED("vehicle_was_already_released"),
        SLOT_TYPE_NOT_FOUND("slot.type.not.found");


        private final String prefixKey;

        Type(final String prefixKey) {
            this.prefixKey = prefixKey;
        }

        public String getCode() {
            return prefixKey + ".code";
        }

        public String getDescription() {
            return prefixKey + ".description";
        }
    }

    public Type getType() {
        return type;
    }
}
