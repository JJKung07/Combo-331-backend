package se331.lab.service;

import org.springframework.data.domain.Page;
import se331.lab.entity.Event;
import se331.lab.entity.Organizer;

import java.util.List;

public interface OrganizerService {
    Integer getOrganizerSize();
    Page<Organizer> getOrganizers(Integer pagesize, Integer page);
    Organizer getOrganizer(Long id);
    Organizer save(Organizer event);
}
