package com.r2s.findInternship.data.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "user")
public class User extends Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "email")
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "gender")
	private Boolean gender;

	@Column(name = "avatar")
	private String avatar;

	@Column(name = "phone")
	private String phone;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "birthday")
	private Date birthDay;

	@Column(name = "location")
	private String location;

	@Column(name = "mail_receive")
	private boolean mailReceive; 

	@JsonIgnore
	@Column(name = "auth_provider")
	private String authProvider;

	@JsonIgnore
	@Column(name = "password_forgot_token")
	private String passwordForgotToken;

	@JsonIgnore
	@Column(name = "token_active")
	private String tokenActive;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
	private Role role;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", nullable = false)
	private Status status;

}

