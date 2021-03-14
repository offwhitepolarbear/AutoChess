package com.kihwangkwon.domain.db;

import com.kihwangkwon.domain.Item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Champion {
	private String Season;
	private String version;
	private String name;
	private String nameKorean;
	private int cost;
	private int level;
	private Trait[] trait;
	private int health;
	private int mana;
	private int startMana;
	private int range;
	private int attackDamage;
	private int attackSpeed;
	private int criticalStrikeChance;
	private int criticalDamage;
	private int dodgeChance;
	private int spellPower;
	private int armor;
	private int magicResist;
	private String Skill;
	private String SkillExplanation;
	private String SkillExplanationKorean;
	private int shield;
	private Item[] items;
}
