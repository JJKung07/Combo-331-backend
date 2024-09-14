package se331.lab.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data

public class Organizer {
    Long id;
    String name;
    String address;
}

