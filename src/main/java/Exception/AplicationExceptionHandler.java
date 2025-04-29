package Exception;

import java.util.Map;
import java.util.HashMap;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
//@HttpHandler
@RestControllerAdvice
public class AplicationExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String , String> handleinvalidAegument(MethodArgumentNotValidException exc){
		Map<String , String> mapero=new HashMap<>();
		exc.getBindingResult().getFieldErrors()
		.forEach(erro->{mapero.put(erro.getField(), erro.getDefaultMessage());
		
		});
		return mapero;
		
	}
	
	

}
