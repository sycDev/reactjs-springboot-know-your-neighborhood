package com.wou.kyn.validator;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;

import com.wou.kyn.annotation.ValidPassword;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {
	@Override
	public void initialize(ValidPassword constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {
		PasswordValidator validator = new PasswordValidator(Arrays.asList(
				// within 8 to 64 characters   
				new LengthRule(8, 64), 
				// at least 1 uppercase letter
				new CharacterRule(EnglishCharacterData.UpperCase, 1), 
				// at least 1 lowercase letter
				new CharacterRule(EnglishCharacterData.LowerCase, 1), 
				// at least 1 digit number
				new CharacterRule(EnglishCharacterData.Digit, 1),
				// at least 1 special character
				new CharacterRule(EnglishCharacterData.Special, 1),
				// no whitespace
				new WhitespaceRule()
			));

		RuleResult result = validator.validate(new PasswordData(password));

        if (result.isValid()) {
            return true;
        } else {
			context.buildConstraintViolationWithTemplate(String.join(",", validator.getMessages(result)))
					.addConstraintViolation()
					.disableDefaultConstraintViolation();

			return false;
		}
	}
}
