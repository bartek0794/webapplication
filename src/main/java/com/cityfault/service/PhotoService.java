package com.cityfault.service;

import com.cityfault.model.Photo;

public interface PhotoService {
    void savePhoto(Photo photo);
    Photo getPhotoById(int id);
}
