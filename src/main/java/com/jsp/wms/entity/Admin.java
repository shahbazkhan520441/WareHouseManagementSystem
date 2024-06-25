package com.jsp.wms.entity;

import java.util.List;

import com.jsp.wms.enums.AdminPrivileges;
import com.jsp.wms.enums.AdminType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer adminId;
private String adminName;
private String adminEmail;
private String adminPassword;
@Enumerated(EnumType.STRING)//it will save the value of enum in datbase in string format rather then indexvalue of enumtype
private AdminType adminType;



}
