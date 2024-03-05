package assessment.parkinglot;

import assessment.parkinglot.converter.SlotConverter;
import assessment.parkinglot.converter.SlotVehicleConverter;
import assessment.parkinglot.dto.ParkingSlotVehicleDto;
import assessment.parkinglot.entity.Slot;
import assessment.parkinglot.entity.SlotType;
import assessment.parkinglot.entity.Vehicle;
import assessment.parkinglot.entity.VehicleType;
import assessment.parkinglot.exception.ParkingLotException;
import assessment.parkinglot.repository.*;
import assessment.parkinglot.service.ParkingSlotVehicleService;
import assessment.parkinglot.service.impl.ParkingSlotVehicleServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

public class ParkingSlotVehicleServiceTest {
    private final SlotRepository mockSlotRepository = mock(SlotRepository.class);
    private final VehicleRepository mockVehicleRepository = mock(VehicleRepository.class);
    private final VehicleTypeRepository mockVehicleTypeRepository = mock(VehicleTypeRepository.class);
    private final SlotTypeRepository mockSlotTypeRepository = mock(SlotTypeRepository.class);
    private final SlotVehicleRepository mockSlotVehicleRepository= mock(SlotVehicleRepository.class);
    private final SlotVehicleConverter mockSlotVehicleConverter = mock(SlotVehicleConverter.class);
    private final ParkingSlotVehicleService service = new ParkingSlotVehicleServiceImpl(
            mockSlotRepository, mockVehicleRepository, mockVehicleTypeRepository, mockSlotTypeRepository, mockSlotVehicleRepository,
            mockSlotVehicleConverter
    );
    private static final String VIM = "ABC123FA";
   @Test
    public void shouldThrowAnExceptionWhenVehicleTypeIsNotValid() {
        Assertions.assertThrows(ParkingLotException.class, () -> {
            Optional<VehicleType> optionalVehicleType = mock(Optional.class);
            when(optionalVehicleType.isPresent()).thenReturn(false);
            when(optionalVehicleType.isEmpty()).thenReturn(true);
            when(optionalVehicleType.get()).thenReturn(this.createVehicleType());
            when(mockVehicleTypeRepository.findById(Mockito.anyLong())).thenReturn(optionalVehicleType);
            service.parkVehicle(this.createParkingSlotVehicleDto(VIM, "1"));
        });
    }

    private ParkingSlotVehicleDto createParkingSlotVehicleDto(final String vim, final String typeId) {
        return new ParkingSlotVehicleDto(vim, typeId);
    }

    private VehicleType createVehicleType() {
        final VehicleType vehicleType = new VehicleType();
        vehicleType.setId(1L);
        vehicleType.setName("vans");
        vehicleType.setAlias(VIM);
        return vehicleType;
    }
}
