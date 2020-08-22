package kr.co.dinner41.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.co.dinner41.command.UserInsertCommand;

public class UserInsertValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UserInsertCommand.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserInsertCommand user=(UserInsertCommand)target;
		String email=user.getEmail();
		String password=user.getPassword();
		String passwordConfirm=user.getPasswordConfirm();
		String name=user.getName();
		String address=user.getAddress();
		String subAddress=user.getSubAddress();
		String phone1=user.getPhone1();
		String phone2=user.getPhone2();
		String phone3=user.getPhone3();
		String type=user.getType();
		if(email.trim().equals("")) {
			errors.rejectValue("email", "eamil:required","이메일을 입력해주세요");
		}
		if(password.trim().equals("")) {
			errors.rejectValue("password", "password:required","비밀번호를 입력해주세요");
		}
		if(passwordConfirm.trim().equals("")) {
			errors.rejectValue("passwordConform", "passwordConfirm:required","비밀번호확인을 입력해주세요");
		}
		if(name.trim().equals("")) {
			errors.rejectValue("name", "name:required","이름을 입력해주세요");
		}
		if(address.trim().equals("")) {
			errors.rejectValue("address", "address:required","주소를  입력해주세요");
		}
		if(subAddress.trim().equals("")) {
			errors.rejectValue("subAddress", "subAddress:required","비밀번호확인을 입력해주세요");
		}
		if(phone1.trim().equals("")) {
			errors.rejectValue("phone1", "phone1:required","전화 번호 앞 세자리를 입력해주세요");
		}
		if(phone2.trim().equals("")) {
			errors.rejectValue("phone2", "phone2:required","전화번호 중간 네자리를 입력해주세요");
		}
		if(phone3.trim().equals("")) {
			errors.rejectValue("phone3", "phone3:required","전화번호 마지막 네자리를 입력해주세요");
		}
		if(type.equals("")) {
			errors.rejectValue("type", "type:required", "회원 유형을 입력하세요");
		}
	}

}
