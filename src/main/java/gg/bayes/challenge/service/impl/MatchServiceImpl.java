package gg.bayes.challenge.service.impl;

import gg.bayes.challenge.repository.HeroDamageRepository;
import gg.bayes.challenge.repository.HeroItemsRepository;
import gg.bayes.challenge.repository.HeroKillsRepository;
import gg.bayes.challenge.repository.HeroSpellsRepository;
import gg.bayes.challenge.repository.MatchIdRepository;
import gg.bayes.challenge.rest.model.HeroDamage;
import gg.bayes.challenge.rest.model.HeroItems;
import gg.bayes.challenge.rest.model.HeroKills;
import gg.bayes.challenge.rest.model.HeroSpells;
import gg.bayes.challenge.rest.model.MatchId;
import gg.bayes.challenge.service.MatchService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MatchServiceImpl implements MatchService {

  private static final Logger LOG = LoggerFactory.getLogger(MatchServiceImpl.class);

  @Autowired
  private HeroKillsRepository heroKillsRepository;
  @Autowired
  private HeroItemsRepository heroItemsRepository;
  @Autowired
  private HeroSpellsRepository heroSpellsRepository;
  @Autowired
  private HeroDamageRepository heroDamageRepository;
  @Autowired
  private MatchIdRepository matchIdRepository;

  @Autowired
  public MatchServiceImpl() {
  }

  @Override
  public Long ingestMatch(String payload) {
    LOG.info("** Data Ingestion start **");

    TransformMatchData transformMatchData = new TransformMatchData();

    HashMap<String, HeroKills> heroKillsMap = new HashMap<>();
    HashMap<String, HeroSpells> heroSpellsMap = new HashMap<>();
    HashMap<String, HeroDamage> heroDamageMap = new HashMap<>();
    List<HeroItems> heroItemsList = new ArrayList<>();

    matchIdRepository.save(new MatchId());
    final Long matchId = matchIdRepository.count();

    try (BufferedReader reader = new BufferedReader(new StringReader(payload))) {
      String line;
      while ((line = reader.readLine()) != null) {
        if (line.contains("killed")) {
          transformMatchData.transformHeroKills(line, heroKillsMap, matchId);
        } else if (line.contains("casts")) {
          transformMatchData.transformHeroSpells(line, heroSpellsMap, matchId);
        } else if (line.contains("buys item")) {
          transformMatchData.transformHeroItems(line, heroItemsList, matchId);
        } else if (line.contains("hits")) {
          transformMatchData.transformHeroDamage(line, heroDamageMap, matchId);
        }
      }
    } catch (IOException exception) {
      LOG.error(exception.toString());
    }
    LOG.info("Data Transformation is successful and ingestion Started");

    heroKillsRepository.saveAll(heroKillsMap.values());
    heroSpellsRepository.saveAll(heroSpellsMap.values());
    heroDamageRepository.saveAll(heroDamageMap.values());
    heroItemsRepository.saveAll(heroItemsList);

    LOG.info("Data Ingestion in Database is successful");

    return matchId;
  }

  @Override
  public List<HeroKills> getMatch(Long matchId) {
    return heroKillsRepository.findAll();
  }

  @Override
  public List<HeroItems> getItems(Long matchId, String heroName) {
    return heroItemsRepository.findByHeroNameAndMatchId(heroName, matchId);
  }

  @Override
  public List<HeroSpells> getSpells(Long matchId, String heroName) {
    return heroSpellsRepository.findByHeroNameAndMatchId(heroName, matchId);
  }

  @Override
  public List<HeroDamage> getDamage(Long matchId, String heroName) {
    return heroDamageRepository.findByHeroNameAndMatchId(heroName, matchId);
  }
}
