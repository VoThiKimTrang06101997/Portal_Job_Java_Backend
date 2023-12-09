package com.r2s.findInternship.data.entity;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "company")
public class Company extends Auditable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@Column(name = "logo")
	private String logo;

	@Column(name = "description", length = 5000)
	private String description;

	@Column(name = "website")
	private String website;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

	@Column(name = "tax")
	private String tax;

	@Column(name = "location")
	private String location;

	@Column(name = "personnel_size")
	private int personnelSize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    private Status status;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private List<CompanyRate> companyRates = new ArrayList<>();

}
