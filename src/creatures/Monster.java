package creatures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Monster extends Creature {
	private int gold;
	private int exp;
	private String name;
	
	public Monster(int gold, int exp, int health, int damage, int accuracy, int evasion, String name) {
		super(health, damage, accuracy, evasion);
		this.gold = gold;
		this.exp = exp;
		this.name = name;
	}

	public int getExp() {
		return exp;
	}

	public int getGold() {
		return gold;
	}
	
	public String getName() {
		return name;
	}

	public static List<Monster> getMonsterList() {
		List<Monster> monsterList = new ArrayList<Monster>();
		// Path file = Paths.get("src/resources/monsters.desc");
		// Charset charset = Charset.forName("US-ASCII");
		
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(
				ClassLoader.getSystemClassLoader().getResourceAsStream("resources/monsters.desc")))) {
			String line = null;
			String[] split = new String[7];
			while((line = reader.readLine()) != null) {
				split = line.split("\\|");
				monsterList.add(new Monster(Integer.parseInt(split[1]), Integer.parseInt(split[2]),
						Integer.parseInt(split[3]), Integer.parseInt(split[4]), Integer.parseInt(split[5]),
						Integer.parseInt(split[6]), split[0]));
			}
		} catch(IOException e) {
			System.err.format("IOException: %s\n", e);
		}
		
		return monsterList;
	}
}
