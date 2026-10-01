package com.example.demo.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity 
@Data 
@Table(name = "students") 
@NoArgsConstructor 
@AllArgsConstructor 
public class Student {
@Id 
@GeneratedValue(strategy=GenerationType.IDENTITY)
public  Integer Id;
@Column(nullable = false)
public String name;
public String course;
@Column(nullable = false, unique = true)
public String email;

}
