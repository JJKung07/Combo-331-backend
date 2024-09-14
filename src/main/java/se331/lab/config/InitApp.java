package se331.lab.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import se331.lab.Repository.EventRepository;
import se331.lab.entity.Event;

@Component
@RequiredArgsConstructor
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    final EventRepository eventRepository;
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
    }
}
