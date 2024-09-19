package se331.lab.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Organizer {
    @Id
        @GeneratedValue
        @EqualsAndHashCode.Exclude
    Long id;
    String name;
    String address;
}

