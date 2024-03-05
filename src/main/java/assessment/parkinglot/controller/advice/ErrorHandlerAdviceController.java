package assessment.parkinglot.controller.advice;

import assessment.parkinglot.dto.ErrorDto;
import assessment.parkinglot.exception.ParkingLotException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Properties;

@ControllerAdvice
public class ErrorHandlerAdviceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandlerAdviceController.class);

    @Autowired private Properties errorProperties;

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorDto> handleApiException(final Exception ex) {
        final ErrorDto error = new ErrorDto.Builder()
                .withCode("10001")
                .withMessage(ex.getMessage()).build();
        LOGGER.error("Error: {}", ex.getCause());
        ex.printStackTrace();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ParkingLotException.class)
    public ResponseEntity<ErrorDto> handleProductDataBaseException(final ParkingLotException ex) {
        final ErrorDto error = new ErrorDto.Builder()
                .withCode(errorProperties.getProperty(ex.getType().getCode()))
                .withMessage(errorProperties.getProperty(ex.getType().getDescription())).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
