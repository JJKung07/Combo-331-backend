package se331.lab.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import se331.lab.Repository.EventRepository;
import se331.lab.entity.Event;

import java.util.ArrayList;
import java.util.List;

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
    public List<Event> getEvents(Integer pageSize, Integer page) {
        List<Event> events = eventRepository.findAll();
        pageSize = pageSize == null ? events.size() : pageSize;
        page = page == null ? 1 : page;
        int firstIndex = (page - 1) * pageSize;
        int lastIndex = Math.min(firstIndex + pageSize, events.size());

        if (firstIndex >= events.size()) {
            return new ArrayList<>(); // Return an empty list if page index is out of range
        }

        return new ArrayList<>(events.subList(firstIndex, lastIndex));
    }

    @Override
    public Event getEvent(Long id) {
        return eventRepository.findById(id).orElse(null);
    }
}
