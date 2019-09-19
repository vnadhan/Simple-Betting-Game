package app.service;
import org.springframework.data.repository.CrudRepository;

import app.model.GameRound;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends CrudRepository<GameRound, Integer> {
}
