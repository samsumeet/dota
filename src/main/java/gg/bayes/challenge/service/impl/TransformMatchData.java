package gg.bayes.challenge.service.impl;

import gg.bayes.challenge.rest.model.HeroDamage;
import gg.bayes.challenge.rest.model.HeroItems;
import gg.bayes.challenge.rest.model.HeroKills;
import gg.bayes.challenge.rest.model.HeroSpells;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.List;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TransformMatchData {



  public HashMap<String, HeroKills> transformHeroKills(String line,
      HashMap<String, HeroKills> heroKillsMap, final Long matchId)
      throws IndexOutOfBoundsException {
    String heroName = line.split("killed by")[1].trim()
        .replace("npc_dota_hero_", "");

    if (heroKillsMap.containsKey(heroName)) {
      HeroKills heroKills = heroKillsMap.get(heroName);
      heroKills.setKills(heroKills.getKills() + 1);
    } else {
      HeroKills heroKills = new HeroKills();
      heroKills.setMatchId(matchId);
      heroKills.setKills(0);
      heroKills.setHero(heroName);
      heroKillsMap.put(heroName, heroKills);
    }

    return heroKillsMap;
  }

  public HashMap<String, HeroSpells> transformHeroSpells(String line,
      HashMap<String, HeroSpells> heroSpellsMap, long matchId) {

    String heroName = line.substring(line.indexOf("]") + 1,
        line.indexOf("casts")).trim()
        .replace("npc_dota_hero_", "");

    String spell = line.substring(line.indexOf("ability") + "ability".length(),
        line.lastIndexOf("on")).trim();

    if (heroSpellsMap.containsKey(spell)) {
      HeroSpells heroSpell = heroSpellsMap.get(spell);
      heroSpell.setCasts(heroSpell.getCasts() + 1);
    } else {
      HeroSpells heroSpell = new HeroSpells();
      heroSpell.setMatchId(matchId);
      heroSpell.setSpell(spell);
      heroSpell.setCasts(1);
      heroSpell.setHeroName(heroName);
      heroSpellsMap.put(spell, heroSpell);
    }
    return heroSpellsMap;
  }

  public List<HeroItems> transformHeroItems(String line,
      List<HeroItems> heroItemsList, final Long matchId) {

    String heroName = line.substring(line.indexOf("]") + 1,
        line.indexOf("buys")).trim()
        .replace("npc_dota_hero_", "");

    String heroItems = line.split("buys item")[1].trim()
        .replace("item_", "");

    String date = line.substring(line.indexOf("[") + 1, line.indexOf("]"));

    HeroItems items = new HeroItems();
    items.setHeroName(heroName);
    items.setItem(heroItems);
    items.setTimestamp(LocalTime.parse(date).getLong(ChronoField.MILLI_OF_DAY));
    items.setMatchId(matchId);
    heroItemsList.add(items);

    return heroItemsList;
  }

  public HashMap<String, HeroDamage> transformHeroDamage(String line,
      HashMap<String, HeroDamage> heroDamageMap, final Long matchId) {
    String heroName = line.substring(line.indexOf("]") + 1,
        line.lastIndexOf("hits")).trim()
        .replace("npc_dota_hero_", "");

    String target = line.substring(line.indexOf("hits") + "hits".length(),
        line.lastIndexOf("with")).trim()
        .replace("npc_dota_hero_", "");

    Integer totalDamage = Integer.parseInt(line.substring(line.lastIndexOf("for") + "for".length(),
        line.lastIndexOf("damage")).trim());

    if (heroDamageMap.containsKey(target)) {
      HeroDamage heroDamage = heroDamageMap.get(target);
      heroDamage.setDamageInstances(heroDamage.getDamageInstances() + 1);
      heroDamage.setTotalDamage(heroDamage.getTotalDamage() + totalDamage);
    } else {
      HeroDamage heroDamage = new HeroDamage();
      heroDamage.setTarget(target);
      heroDamage.setHeroName(heroName);
      heroDamage.setDamageInstances(1);
      heroDamage.setTotalDamage(totalDamage);
      heroDamage.setMatchId(matchId);
      heroDamageMap.put(target, heroDamage);
    }
    System.out.println(target);
    return heroDamageMap;
  }

}
