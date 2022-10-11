package com.mkallu.web.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String phone;
	private String address;
	private String idProof;
	
	@Override
	public String toString() {
		return name + "~" + phone + "~" + address + "~" + idProof;
	}
	
}
