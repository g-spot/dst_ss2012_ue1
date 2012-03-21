package dst1.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CPUsValidator implements ConstraintValidator<CPUs, Integer> {

	private int min;
	private int max;
	
	@Override
	public void initialize(CPUs arg0) {
		min = arg0.min();
		max = arg0.max();
	}

	@Override
	public boolean isValid(Integer arg0, ConstraintValidatorContext arg1) {		
		if(arg0 < min || arg0 > max)
			return false;
		return true;
	}

}
