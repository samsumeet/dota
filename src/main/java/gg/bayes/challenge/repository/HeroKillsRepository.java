package gg.bayes.challenge.repository;

import gg.bayes.challenge.rest.model.HeroKills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroKillsRepository extends JpaRepository<HeroKills, String> {

}
