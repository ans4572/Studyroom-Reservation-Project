package fullstack.reservation.service;

import fullstack.reservation.domain.Manager;
import fullstack.reservation.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepository managerRepository;

    @Transactional
    public Manager join(Manager manager) {
        return managerRepository.save(manager);
    }

    public Manager retrieveOne(Long id) {
        return managerRepository.findById(id).orElse(null);
    }

    public List<Manager> retrieveAll() {
        return managerRepository.findAll();
    }
}
