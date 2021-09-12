package fullstack.reservation.service;

import fullstack.reservation.domain.Board;
import fullstack.reservation.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Board register(Board board) {
        return boardRepository.save(board);
    }

    public Board retrieveOne(Long id) {
        return boardRepository.findById(id).orElse(null);
    }

    public List<Board> retrieveAll() {
        return boardRepository.findAll();
    }
}
