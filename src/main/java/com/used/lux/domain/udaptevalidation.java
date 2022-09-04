package com.used.lux.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;

@Getter
public class udaptevalidation {
	
	@NotBlank(message = "�� ��й�ȣ�� �Է��� �ּ���")
	@Size(min=4,max=12, message = "4�̻� 12���Ϸ� �Է����ּ���")
	private String password;
	
    @NotBlank(message = "�̸��� �ּҸ� �Է����ּ���.")
    @Email(message = "�ùٸ� �̸��� �ּҸ� �Է����ּ���.")
    private String email;
    
    @NotBlank(message = "�޴��� ��ȣ�� �Է����ּ���.")
    @Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})", message = "�ùٸ� �޴��� ��ȣ�� �Է����ּ���.")
    private String phone;
}
