package com.example.demo.dao;

import com.example.demo.entity.base.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileDao extends JpaRepository<File, Long> { }
