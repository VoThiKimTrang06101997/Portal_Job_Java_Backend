package com.r2s.findInternship.data.entity;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "internship_major")
public class InternshipMajor implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "internship_programme_id", referencedColumnName = "id")
	private InternshipProgramme internshipProgramme;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_id", referencedColumnName = "id")
	private Major major;
	
}
