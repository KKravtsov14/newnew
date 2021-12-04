/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package doctor.ui;

import java.util.Calendar;

import org.hibernate.validator.constraints.NotEmpty;


public class Doctor {

	private Long id;

	private String email;

	private String password;

	@NotEmpty(message = "Last Name is required.")
	private String lastName;

	@NotEmpty(message = "First Name is required.")
	private String firstName;

	private String secondName;

	private String specialization;

	private String workingDays;

	@NotEmpty(message = "Working Hours is required.")
	private String workingHours;

	private String photo;

	public Long getId() {
		return this.id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getSecondName() {
		return this.secondName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getEmail() {return this.email;}

	public String getPassword() {
		return this.password;
	}

	public String getSpecialization() {
		return this.specialization;
	}

	public String getWorkingDays() {
		return this.workingDays;
	}

	public String getWorkingHours() {
		return this.workingHours;
	}

	public String getPhoto() {
		return this.photo;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public void setWorkingDays(String workingDays) {
		this.workingDays = workingDays;
	}

	public void setWorkingHours(String workingHours) {
		this.workingHours = workingHours;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}


	@Override
	public String toString() {
		return this.firstName + " "+ this.lastName;
	}

}
