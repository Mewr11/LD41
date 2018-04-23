package creatures;

import javax.swing.JOptionPane;

public class Player extends Creature {
	private int exp;
	private int gold;
	private int level;
	private int maxHealth;
	
	public Player() {
		super(100, 5, 50, 10);
		this.exp = 0;
		this.gold = 0;
		this.level = 1;
		this.maxHealth = 100;
	}
	
	public boolean buy(int price) {
		if(this.gold >= price) {
			this.gold -= price;
			return true;
		} else {
			return false;
		}
	}
	
	public void injure(int damage) {
		this.addHealth(-damage);
		if(this.getHealth() < 0) {
			JOptionPane.showMessageDialog(null, "You have died.");
			System.exit(0);
		}
	}

	public int getExp() {
		return exp;
	}

	public void addExp(int exp) {
		this.exp += exp;
	}

	public int getGold() {
		return gold;
	}

	public void addGold(int gold) {
		this.gold += gold;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	public void addMaxHealth(int maxHealth) {
		this.maxHealth += maxHealth;
		this.addHealth(maxHealth);
	}

	public int getLevel() {
		return level;
	}

	public boolean addLevel() {
		if(this.exp >= this.level) {
			this.exp -= this.level;
			this.level++;
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void addHealth(int health) {
		this.health = Integer.min(this.health + health, this.maxHealth);
	}
	
	@Override
	public String toString() {
		return String.format("♥: %d/%d\n🗡: %d\n⌖: %d\n»: %d\nXP: %d\nGold: %d\nLvl: %d", this.getHealth(),
				this.getMaxHealth(), this.getDamage(), this.getAccuracy(), this.getEvasion(), this.getExp(),
				this.getGold(), this.getLevel());
	}
}
