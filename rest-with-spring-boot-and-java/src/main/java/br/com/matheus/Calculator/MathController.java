package br.com.matheus.Calculator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.matheus.Calculator.Services.MathService;
import br.com.matheus.Exceptions.ResourceNotFoundException;

@RestController
@RequestMapping("/api")
public class MathController {

	private MathService mathService = new MathService();
	
	@GetMapping(value = "/sum/{numberOne}/{numberTwo}")
	public Double sum(@PathVariable(value = "numberOne") String numberOne, 
			@PathVariable(value = "numberTwo") String numberTwo) throws Exception
	{
		if(!mathService.isNumeric(numberOne) || !mathService.isNumeric(numberTwo)) 
		{
			throw new ResourceNotFoundException("Por favor digite valores numéricos");
		}
		
		return mathService.sum(numberOne, numberTwo);
	}
	
	@GetMapping(value = "/subtraction/{numberOne}/{numberTwo}")
	public Double subtraction(@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo) throws Exception 
	{		
		if(!mathService.isNumeric(numberOne) || !mathService.isNumeric(numberTwo)) 
		{
			throw new ResourceNotFoundException("Por favor digite valores numéricos");
		}
		
		return mathService.subtraction(numberOne, numberTwo);
	}
	
	@GetMapping(value = "/multiplication/{numberOne}/{numberTwo}")
	public Double multiplication (@PathVariable(value="numberOne") String numberOne,
			@PathVariable(value="numberTwo") String numberTwo) throws Exception 
	{
		if(!mathService.isNumeric(numberOne) || !mathService.isNumeric(numberTwo))
		{
			throw new ResourceNotFoundException("Por favor digite valores numéricos");
		}
		
		return mathService.multiplication(numberOne, numberTwo);
	}

	@GetMapping(value = "/division/{numberOne}/{numberTwo}")
	public Double division(@PathVariable(value = "numberOne") String numberOne,
		@PathVariable(value = "numberTwo") String numberTwo) throws Exception 
	{
		if(!mathService.isNumeric(numberOne) || !mathService.isNumeric(numberTwo)) 
		{
			throw new ResourceNotFoundException("Por favor digite valores numéricos");
		}
	
		return mathService.division(numberOne, numberTwo);
	}	
	
	@GetMapping(value = "mean/{numberOne}/{numberTwo}")
	public Double mean(@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo) throws Exception 
	{
		if(!mathService.isNumeric(numberOne) || !mathService.isNumeric(numberTwo)) 
		{
			throw new ResourceNotFoundException("Por favor digte valores numéricos");
		}
		
		return mathService.mean(numberOne, numberTwo); 
	}
	
	@GetMapping(value = "/squareRoot/{numberOne}")
	public Double squareRoot(@PathVariable(value = "numberOne") String numberOne) throws Exception 
	{
		if(!mathService.isNumeric(numberOne))
		{
			throw new ResourceNotFoundException("Por favor digte valor numérico");
		}
		
		return mathService.squareRoot(numberOne);
	}
}
