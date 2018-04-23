package userinterface;

import creatures.Player;
import items.Item;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class ItemButton extends Button {
	private Item item;
	private Player player;
	
	public ItemButton(Item item, Player player, Text playerStats) {
		this.item = item;
		this.player = player;
		this.setText(item.toString());
		this.setOnMouseClicked((MouseEvent t) -> {
			if(this.player.buy(this.item.price)) {
				switch(this.item.itemType) {
					case HEALTH:
						this.player.addMaxHealth(this.item.modifier);
						break;
					case DAMAGE:
						this.player.addDamage(this.item.modifier);
						break;
					case ACCURACY:
						this.player.addAccuracy(this.item.modifier);
						break;
					case EVASION:
						this.player.addEvasion(this.item.modifier);
						break;
					default:
						break;
				}
				this.setDisable(true);
				playerStats.setText(this.player.toString());
			}
		});
	}
}
