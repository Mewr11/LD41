package creatures;

import java.util.Random;

public class Creature {
	protected int health;
	private int damage;
	private int accuracy;
	private int evasion;
	private Random r;
	
	public Creature(int health, int damage, int accuracy, int evasion) {
		this.health = health;
		this.damage = damage;
		this.accuracy = accuracy;
		this.evasion = evasion;
		this.r = new Random();
	}
	
	public static Creature fight(Player a, Monster b) {
		int bhealth = b.getHealth();
		while(a.getHealth() > 0 && bhealth > 0) {
			if(b.rollAccuracy() > a.rollEvasion()) {
				a.injure(b.getDamage());
			}
			if(a.rollAccuracy() > b.rollEvasion()) {
				bhealth -= a.getDamage();
			}
		}
		if(a.getHealth() > 0) {
			return a;
		} else if(bhealth > 0) {
			return b;
		} else {
			return null;
		}
	}
	
	public int rollEvasion() {
		return this.r.nextInt(this.evasion + 1);
	}
	
	public int rollAccuracy() {
		return this.r.nextInt(this.accuracy + 1);
	}

	public int getEvasion() {
		return evasion;
	}

	public void addEvasion(int evasion) {
		this.evasion += evasion;
	}

	public int getAccuracy() {
		return accuracy;
	}

	public void addAccuracy(int accuracy) {
		this.accuracy += accuracy;
	}

	public int getDamage() {
		return damage;
	}

	public void addDamage(int damage) {
		this.damage += damage;
	}

	public int getHealth() {
		return health;
	}

	public void addHealth(int health) {
		this.health += health;
	}
	
	@Override
	public String toString() {
		return String.format("♥: %d\n🗡: %d\n⌖: %d\n»: %d", this.health, this.damage,
				this.accuracy, this.evasion);
	}
}
