package fullstack.reservation.service;

import fullstack.reservation.domain.Item;
import fullstack.reservation.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Item register(Item item) {
        return itemRepository.save(item);
    }

    public Item retrieveOne(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    public List<Item> retrieveAll() {
        return itemRepository.findAll();
    }
}
