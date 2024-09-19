package se331.lab.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import se331.lab.Repository.OrganizerRepository;
import se331.lab.entity.Event;
import se331.lab.entity.Organizer;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Profile("db")
public class OrganizerDaoDbImpl implements OrganizerDao {
    final OrganizerRepository organizerRepository;

    @Override
    public Integer getOrganizerSize() {
        return Math.toIntExact(organizerRepository.count());
    }

    @Override
    public Page<Organizer> getOrganizers(Integer pageSize, Integer page) {
        return organizerRepository.findAll(PageRequest.of(page != null ? page - 1 : 0, pageSize));
    }

    @Override
    public Organizer getOrganizer(Long id) {
        return organizerRepository.findById(id).orElse(null);
    }

    @Override
    public Organizer save(Organizer organizer) {
        return organizerRepository.save(organizer);
    }
}
