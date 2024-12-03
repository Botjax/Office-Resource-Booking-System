package com.capstone.officeresourcebooking.services;

import com.capstone.officeresourcebooking.models.Room;
import com.capstone.officeresourcebooking.repositories.ResourceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResourceService {
    private final ResourceRepository resourceRepository;

    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public List<Room> getAllResources() {
        List<String> resources = this.resourceRepository.findAllResourceNames();
        List<Room> rooms = new ArrayList<>();
        int i = 0;
        for (String resourceName : resources) {
            i++;
            Room room = new Room(i, resourceName);
            rooms.add(room);
        }
        return rooms;
    }

    public int findResourceByName(String name) {
        return resourceRepository.findResourceIdByName(name).get();
    }

    public String getResourceNameById(Long resourceId) {
        return resourceRepository.findResourceNameById(resourceId);
    }
}
