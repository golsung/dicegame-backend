package kr.insang.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.insang.model.Score;


public interface  ScoreRepository extends JpaRepository<Score, Integer> {

	Optional<Score> findByName(String name);
	
}
