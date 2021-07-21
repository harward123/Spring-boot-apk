package com.edwardpinzon.aplicacion.dto;

import java.util.Objects;

import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

public class ChangePasswordForm {

	@NotNull
	private long id;
	
	
	@NotBlank(message="Current Password must not be blank")
	private String currentPassword;

	@NotBlank(message="New Password must not be blank")
	private String newPassword;

	@NotBlank(message="Confirm Password must not be blank")
	private String confirmPassword;
	
	public ChangePasswordForm(){}
	public ChangePasswordForm(Long id){this.id = id;}
	
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCurrentPassword() {
		return currentPassword;
	}
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	@Override
	public int hashCode() {
		return Objects.hash(confirmPassword, currentPassword, id, newPassword);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChangePasswordForm other = (ChangePasswordForm) obj;
		return Objects.equals(confirmPassword, other.confirmPassword)
				&& Objects.equals(currentPassword, other.currentPassword) && id == other.id
				&& Objects.equals(newPassword, other.newPassword);
	}
	@Override
	public String toString() {
		return "ChangePasswordForm [id=" + id + ", currentPassword=" + currentPassword + ", newPassword=" + newPassword
				+ ", confirmPassword=" + confirmPassword + "]";
	}
    
	
	
	
}
