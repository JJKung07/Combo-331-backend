package se331.lab.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import se331.lab.Repository.EventRepository;
import se331.lab.Repository.OrganizerRepository;
import se331.lab.entity.Event;
import se331.lab.entity.Organizer;

@Component
@RequiredArgsConstructor
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    final EventRepository eventRepository;
    final OrganizerRepository organizerRepository;
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        eventRepository.save(Event.builder()
                .category("Academic")
                .title("Midterm Exam")
                .description("A time for taking the exam")
                .location("CAMT Building")
                .date("3 Sept")
                .time("3.00 - 4.00 pm")
                .petAllowed(false)
                .organizer("CAMT").build());
        eventRepository.save(Event.builder()
                .category("Academic")
                .title("Commencement Day")
                .description("A time for celebration")
                .location("CMU comvention hall")
                .date("21 Jan")
                .time("8.00 am - 4.00 pm")
                .petAllowed(false)
                .organizer("CAMT").build());
        eventRepository.save(Event.builder()
                .category("Culture")
                .title("Songkran")
                .description("Let's play water")
                .location("Chiang Mai Moat")
                .date("13th April")
                .time("10.00 Am - 6.00 Pm")
                .petAllowed(true)
                .organizer("Chiang Mai Municipality").build());

        organizerRepository.save(Organizer.builder()
                .id(1L)
                .name("Kat Laydee")
                .address("123 Cat Street")
                .build());
        organizerRepository.save(Organizer.builder()
                .id(2L)
                .name("Fern Pollin")
                .address("456 Garden Avenue")
                .build());
        organizerRepository.save(Organizer.builder()
                .id(3L)
                .name("Carey Wales")
                .address("789 Playa Del Carmen")
                .build());
        organizerRepository.save(Organizer.builder()
                .id(4L)
                .name("Dawg Dahd")
                .address("1001 Woof Town")
                .build());
        organizerRepository.save(Organizer.builder()
                .id(5L)
                .name("Kahn Opiner")
                .address("1002 Tin City")
                .build());
        organizerRepository.save(Organizer.builder()
                .id(6L)
                .name("Brody Kill")
                .address("1003 Highway 50")
                .build());
    }
}
