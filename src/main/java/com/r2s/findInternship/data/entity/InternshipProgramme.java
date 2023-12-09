package com.r2s.findInternship.data.entity;

import java.util.ArrayList;
import java.util.Date;
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
@Table(name = "internship_programme")
public class InternshipProgramme extends Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "university_id")
	private University university;
	
	@Column(name = "title")
	private String title;

	@OneToMany(mappedBy = "internshipProgramme", fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<InternshipMajor> internshipMajors = new ArrayList<>();

	@OneToMany(mappedBy = "internshipProgramme", fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<InternshipPosition> internshipPositions = new ArrayList<>();
	
	@OneToMany(mappedBy = "internshipProgramme", fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<InternshipSchedule> internshipSchedules = new ArrayList<>();

	@Column(name = "amount")
	private long amount;

	@Column(name = "students",length = 1000)
	private String students;

	@Column(name = "description", length = 5000)
	private String description;

	@Column(name = "requirement", length = 1000)
	private String requirement;

	@Column(name = "other_info", length = 1000)
	private String otherInfo;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "province_id")
//	private Province province;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id")
	private Status status;

}
