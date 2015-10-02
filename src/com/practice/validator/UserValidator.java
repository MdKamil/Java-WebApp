package com.practice.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.practice.domain.User;

@Component
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username",
				"required", "Field is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required",
				"Field is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
				"required", "Field is required.");
	}

}
