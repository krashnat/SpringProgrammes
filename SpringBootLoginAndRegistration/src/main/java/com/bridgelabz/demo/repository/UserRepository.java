package com.bridgelabz.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.demo.model.RegistrationInformation;

public interface UserRepository extends JpaRepository<RegistrationInformation, Long> {

}
