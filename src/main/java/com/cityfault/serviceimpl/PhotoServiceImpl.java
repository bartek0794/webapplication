package com.cityfault.serviceimpl;

import com.cityfault.model.Photo;
import com.cityfault.repository.PhotoRepository;
import com.cityfault.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoServiceImpl implements PhotoService {
    @Autowired
    private PhotoRepository repository;

    public void savePhoto(Photo department) {
        repository.save(department);
    }
    public Photo getPhotoById(int id) {
        return repository.findByPhotoId(id);
    }
}
