package gg.bayes.challenge.service;

import gg.bayes.challenge.rest.model.HeroDamage;
import gg.bayes.challenge.rest.model.HeroItems;
import gg.bayes.challenge.rest.model.HeroKills;
import gg.bayes.challenge.rest.model.HeroSpells;
import java.util.List;

public interface MatchService {

  Long ingestMatch(final String payload);

  List<HeroKills> getMatch(final Long matchId);

  List<HeroItems> getItems(final Long matchId, final String heroName);

  List<HeroSpells> getSpells(final Long matchId, final String heroName);

  List<HeroDamage> getDamage(final Long matchId, final String heroName);

}
