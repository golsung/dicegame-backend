package kr.insang.gamecontroller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import kr.insang.diceService.Not_Exists_Exception;
import kr.insang.diceService.Not_Yet_Exception;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ResponseStatus(value=HttpStatus.CONFLICT, reason="should not happen")
	@ExceptionHandler(Not_Yet_Exception.class)
	public void handleCourseAlreadyExistsException(HttpServletRequest request, Exception ex){
	
		logger.info("anyone should win:: URL="+request.getRequestURL());

	}
	
	@ResponseStatus(value=HttpStatus.CONFLICT, reason="should not happen")
	@ExceptionHandler(Not_Exists_Exception.class)
	public void handleNotExistsException(HttpServletRequest request, Exception ex){
	
		logger.info("not exist score record:: URL="+request.getRequestURL());

	}

	
}