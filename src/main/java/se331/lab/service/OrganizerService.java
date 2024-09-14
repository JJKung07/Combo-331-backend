package se331.lab.service;

import se331.lab.entity.Organizer;

import java.util.List;

public interface OrganizerService {
    Integer getOrganizerSize();
    List<Organizer> getOrganizers(Integer pagesize, Integer page);
    Organizer getOrganizer(Long id);
}
