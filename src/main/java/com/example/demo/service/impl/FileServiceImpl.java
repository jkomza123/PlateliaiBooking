package com.example.demo.service.impl;

import com.example.demo.dao.FileDao;
import com.example.demo.entity.base.File;
import com.example.demo.service.FileService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    private final FileDao fileDao;

    public FileServiceImpl(FileDao fileDao) {
        this.fileDao = fileDao;
    }

    public List<File> findAll() {
        return fileDao.findAll();
    }

    public File findById(Long id) {
        return fileDao.findById(id).orElse(null);
    }

    @Transactional
    public File save(File file) {
        return fileDao.save(file);
    }

    @Transactional
    public void delete(Long id) {
        fileDao.deleteById(id);
    }
}
