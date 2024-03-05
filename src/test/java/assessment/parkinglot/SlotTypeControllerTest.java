package assessment.parkinglot;

import assessment.parkinglot.controller.SlotTypeController;
import assessment.parkinglot.dto.SlotTypeDto;
import assessment.parkinglot.service.SlotTypeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import org.springframework.http.ResponseEntity;

public class SlotTypeControllerTest {

    private final SlotTypeService mockSlotTypeService = mock(SlotTypeService.class);
    @Test
    public void testFindAll() {
        final List<SlotTypeDto> expected = new ArrayList<>(2);
        expected.add(new SlotTypeDto(1L, "Regular", "regular"));
        expected.add(new SlotTypeDto(2L, "Compact", "compact"));

        when(mockSlotTypeService.findAllSlotTypes()).thenReturn(expected);

        final SlotTypeController slotTypeController = new SlotTypeController(mockSlotTypeService);
        final ResponseEntity<List<SlotTypeDto>> result = slotTypeController.findAllSlotTypes();
        Assertions.assertArrayEquals(expected.toArray(), result.getBody().toArray());
    }
}
