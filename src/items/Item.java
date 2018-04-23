package items;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Item {
	public enum ItemType {
		HEALTH, DAMAGE, ACCURACY, EVASION
	}
	
	public final ItemType itemType;
	public final int modifier;
	public final String name;
	public final int price;
	
	public Item(char type, int modifier, int price, String name) {
		this.modifier = modifier;
		this.name = name;
		this.price = price;
		switch (type) {
			case 'h':
				this.itemType = ItemType.HEALTH;
				break;
			case 'd':
				this.itemType = ItemType.DAMAGE;
				break;
			case 'a':
				this.itemType = ItemType.ACCURACY;
				break;
			case 'e':
				this.itemType = ItemType.EVASION;
				break;
			default:
				this.itemType = null;
				break;
		}
	}

	public static List<Item> getItemList() {
		List<Item> itemList = new ArrayList<Item>();
		// Charset charset = Charset.forName("US-ASCII");
		// Path file = Paths.get("src/resources/items.desc");
		
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(
				ClassLoader.getSystemClassLoader().getResourceAsStream("resources/items.desc")))) {
			String line = null;
			String[] split = new String[4];
			while((line = reader.readLine()) != null) {
				split = line.split("\\|");
				itemList.add(
						new Item(split[1].charAt(0), Integer.parseInt(split[2]), Integer.parseInt(split[3]), split[0]));
			}
		} catch(IOException e) {
			System.err.format("IOException: %s\n", e);
		}
		
		return itemList;
	}
	
	@Override
	public String toString() {
		String c;
		switch (this.itemType) {
			case HEALTH:
				c = "♥";
				break;
			case DAMAGE:
				c = "🗡";
				break;
			case ACCURACY:
				c = "⌖";
				break;
			case EVASION:
				c = "»";
				break;
			default:
				c = "";
				break;
		}
		return String.format("%s\n%s %d\n%d Gold", this.name, c, this.modifier, this.price);
	}
}
