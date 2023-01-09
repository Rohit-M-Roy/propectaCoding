package com.example.propectaCoding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.propectaCoding.model.Entry;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Integer>{

}
