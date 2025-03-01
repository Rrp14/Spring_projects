package com.vector.crudapp;

import com.vector.crudapp.dao.StudentDAO;
import com.vector.crudapp.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

@SpringBootApplication
@ComponentScan(basePackages = "com.vector.crudapp")
public class CrudappApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudappApplication.class, args);
	}

}
