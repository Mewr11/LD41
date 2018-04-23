package userinterface;

import creatures.Creature;
import creatures.Monster;
import creatures.Player;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class MonsterButton extends Button {
	private Monster monster;
	private Player player;

	public MonsterButton(Monster monster, Player player, Text updateText) {
		this.monster = monster;
		this.player = player;
		this.setText(String.format("%s\n♥ %d  🗡 %d  ⌖ %d  » %d\nXP %d  Gold %d", this.monster.getName(), this.monster.getHealth(),
				this.monster.getDamage(), this.monster.getAccuracy(), this.monster.getEvasion(), this.monster.getExp(),
				this.monster.getGold()));
		this.setOnMouseClicked((MouseEvent e) -> {
			if(Creature.fight(this.player, this.monster) == this.player) {
				this.player.addExp(this.monster.getExp());
				this.player.addGold(this.monster.getGold());
				updateText.setText(player.toString());
			}
		});
	}

}
