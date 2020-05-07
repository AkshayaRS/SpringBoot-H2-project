package com.javaspringbooth2.projectcrudapi.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="SALES_CUSTOMER")
public class CustomerEntity {
	
		@Id
		@Column(name="EMAIL_ID")
		private String emailId;
		
		@Column(name="NAME")
		private String name;
		
		@Column(name="PASSWORD")
		private String password;

		@Column(name="PHONE_NUMBER")
		private String phoneNumber;
		
		@Column(name="ADDRESS")
		private String address;

		@OneToMany(cascade=CascadeType.ALL)
		@JoinColumn(name="CUSTOMER_EMAIL_ID")
		private List<CartEntity> customerCarts;

		public String getEmailId() {
			return emailId;
		}

		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public List<CartEntity> getcustomerCarts() {
			return customerCarts;
		}

		public void setCustomerCarts(List<CartEntity> customerCarts) {
			this.customerCarts = customerCarts;
		}


}
