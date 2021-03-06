package br.com.marketlist.api.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.marketlist.api.exception.model.ErrorModel;
import br.com.marketlist.api.response.ResponseError;

@ControllerAdvice
public class ApiExceptionHandler {


	@ExceptionHandler(EntityNotFound.class)
	public ResponseEntity<?> treatEntityNotFoundException(
			EntityNotFound e) {
		ResponseError error = ResponseError.builder()
				.dateHourError(LocalDateTime.now().toString())
				.message(e.getMessage()).build();
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(error);
	}
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<?> treatBusicessException(BusinessException e) {
		ResponseError error = ResponseError.builder()
				.dateHourError(LocalDateTime.now().toString())
				.message(e.getMessage()).build();
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(error);
	}
	
	/**
	 * Method that check against {@code @Valid} Objects passed to controller endpoints
	 *
	 * @param exception
	 * @return a {@code ErrorResponse}
	 * @see com.aroussi.util.validation.ErrorResponse
	 */
	@ExceptionHandler(value=MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<?> handleException(MethodArgumentNotValidException exception) {

	    List<ErrorModel> errorMessages = exception.getBindingResult().getFieldErrors().stream()
	            .map(err -> new ErrorModel(err.getField(), err.getRejectedValue(), err.getDefaultMessage()))
	            .distinct()
	            .collect(Collectors.toList());
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(errorMessages);
	}
	

	/**
	 * Handle unprocessable json data exception
	 * @param msgNotReadable
	 * @return a {@code ErrorResponse}
	 */
	@ExceptionHandler(value=HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	public ErrorModel handleUnprosseasableMsgException(HttpMessageNotReadableException msgNotReadable) {
	       // note that we've added new properties (message, status) to our ErrorResponse model 
			return ErrorModel.builder()
	            .messageError("UNPROCESSABLE INPUT DATA")
	            .fieldName(""+HttpStatus.UNPROCESSABLE_ENTITY.value())
	            .build();
	}
	
}
