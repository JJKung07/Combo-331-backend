package se331.lab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se331.lab.entity.Event;
import se331.lab.entity.Organizer;
import se331.lab.service.OrganizerService;

import java.util.List;

import static org.springframework.beans.support.PagedListHolder.DEFAULT_PAGE_SIZE;

@Controller
@RequiredArgsConstructor
public class OrganizerController {
    final OrganizerService organizerService;

    @GetMapping("organizers")
    public ResponseEntity<?> getOrganizerLists(@RequestParam(value = "_limit", required = false) Integer perPage,
                                               @RequestParam(value = "_page", required = false) Integer page) {
        // Ensure perPage has a default value if null (if needed)
        perPage = perPage != null ? perPage : DEFAULT_PAGE_SIZE;

        Page<Organizer> pageOutput = organizerService.getOrganizers(perPage, page);
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(pageOutput.getContent(), responseHeader, HttpStatus.OK);
    }

    @GetMapping("organizers/{id}")
    public ResponseEntity<?> getOrganizer(@PathVariable("id") Long id) {
        Organizer output = organizerService.getOrganizer(id);
        if (output != null) {
            return ResponseEntity.ok(output);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Organizer not found for id: " + id);
        }
    }

    @PostMapping("/organizers")
    public ResponseEntity<?> addEvent(@RequestBody Organizer organizer){
        Organizer output = organizerService.save(organizer);
        return ResponseEntity.ok(output);
    }
}
