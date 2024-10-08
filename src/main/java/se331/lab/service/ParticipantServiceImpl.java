package se331.lab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import se331.lab.entity.Participant;
import se331.lab.repository.ParticipantRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {
    final ParticipantRepository participantRepository;

    @Override
    public List<Participant> getAllParticipant() {
        return participantRepository.findAll(); // Retrieves all participants
    }

    @Override
    public Page<Participant> getParticipant(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return participantRepository.findAll(pageable); // Pagination support
    }
}
