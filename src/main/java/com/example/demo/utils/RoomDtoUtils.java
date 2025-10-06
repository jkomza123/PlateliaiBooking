package com.example.demo.utils;

import com.example.demo.dto.RoomDetails;
import com.example.demo.entity.Image;
import com.example.demo.entity.Room;

import java.util.List;
import java.util.stream.Collectors;

public class RoomDtoUtils {

    private RoomDtoUtils() {
    }

    public static RoomDetails mapRoomToDetails(Room room) {
        if (room == null) return null;

        RoomDetails dto = new RoomDetails();
        dto.setName(room.getName());
        dto.setDescription(room.getDescription());
        dto.setPhotoUrlList(mapFileListToUrls(room.getPhotos()));

        return dto;
    }

    private static List<String> mapFileListToUrls(List<Image> photos) {
        if (photos == null || photos.isEmpty()) return List.of();
        return photos.stream()
                .map(Image::getUrl)
                .collect(Collectors.toList());
    }
}