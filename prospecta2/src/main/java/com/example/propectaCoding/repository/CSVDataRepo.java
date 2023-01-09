package com.example.propectaCoding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.propectaCoding.model.CSVData;

@Repository
public interface CSVDataRepo extends JpaRepository<CSVData, Integer>{

}
