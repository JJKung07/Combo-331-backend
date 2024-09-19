package se331.lab.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import se331.lab.Repository.EventRepository;
import se331.lab.entity.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Profile("db")
public class EventDaoDbImpl implements EventDao {
    final EventRepository eventRepository;

    @Override
    public Integer getEventSize() {
        return Math.toIntExact(eventRepository.count());
    }

    @Override
    public Page<Event> getEvents(Integer pageSize, Integer page) {
        // Provide default values if pageSize or page are null
        int size = Optional.ofNullable(pageSize).orElse(10); // Default to 10 items per page
        int pageNumber = Optional.ofNullable(page).orElse(1) - 1; // Default to page 0 if null
        Pageable pageable = PageRequest.of(pageNumber, size);
        return eventRepository.findAll(pageable);
    }

    @Override
    public Event getEvent(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    @Override
    public Event save(Event event) {
        return eventRepository.save(event);
    }
}
