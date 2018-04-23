package userinterface;

import creatures.Player;
import items.Item;

import java.util.List;

import creatures.Monster;
import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Mainframe extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		// Create our main game objects
		
		Player player = new Player();
		List<Monster> monsterList = Monster.getMonsterList();
		List<Item> itemList = Item.getItemList();
		
		// Set up our main window
		
		TabPane mainWindow = new TabPane();
		mainWindow.setMinHeight(580);
		mainWindow.setMaxHeight(580);
		
		// Default spacings and sizes
		
		Insets padding = new Insets(5, 5, 5, 5);
		int btnHeight = 100;
		int btnWidth = 250;
		int pitWidth = 525;
		
		// Tabs
		
		Tab fightTab = new Tab("Fight!");
		fightTab.setClosable(false);
		Tab shopTab = new Tab("Shop!");
		shopTab.setClosable(false);
		Tab levelTab = new Tab("Level Up!");
		levelTab.setClosable(false);
		Tab systemTab = new Tab("Menu");
		systemTab.setClosable(false);
		
		mainWindow.getTabs().add(fightTab);
		mainWindow.getTabs().add(shopTab);
		mainWindow.getTabs().add(levelTab);
		// mainWindow.getTabs().add(systemTab); // System tab not implemented
		
		// Fight Tab
		
		BorderPane fightContainer = new BorderPane();
		fightContainer.setPadding(padding);
		FlowPane monsterPit = new FlowPane();
		monsterPit.setVgap(5);
		monsterPit.setHgap(5);
		
		Text playerStatsFights = new Text(player.toString());
		fightTab.setOnSelectionChanged((Event t) -> {
			playerStatsFights.setText(player.toString());
		});
		for(Monster m: monsterList) {
			MonsterButton mb = new MonsterButton(m, player, playerStatsFights);
			mb.setMinSize(btnWidth, btnHeight);
			mb.setMaxSize(btnWidth, btnHeight);
			mb.setPrefSize(btnWidth, btnHeight);
			monsterPit.getChildren().add(mb);
		}
		
		fightContainer.setRight(playerStatsFights);
		fightContainer.setTop(new Text("Click a monster to fight it!"));
		fightContainer.setCenter(monsterPit);
		fightTab.setContent(fightContainer);
		
		// Shop Tab
		
		BorderPane shopContainer = new BorderPane();
		shopContainer.setPadding(padding);
		FlowPane marketContainer = new FlowPane();
		marketContainer.setMinWidth(pitWidth);
		marketContainer.setMaxWidth(pitWidth);
		marketContainer.setPrefWidth(pitWidth);
		marketContainer.setVgap(5);
		marketContainer.setHgap(5);
		
		Text playerStatsShop = new Text(player.toString());
		shopTab.setOnSelectionChanged((Event t) -> {
			playerStatsShop.setText(player.toString());
		});
		
		Button potionButton = new Button("Potion\nRestore 10 ♥\n10 Gold");
		potionButton.setOnMouseClicked((Event t) -> {
			if(player.buy(10)) {
				player.addHealth(10);
			}
			playerStatsShop.setText(player.toString());
		});
		potionButton.setMinSize(btnWidth, btnHeight);
		potionButton.setMaxSize(btnWidth, btnHeight);
		potionButton.setPrefSize(btnWidth, btnHeight);
		potionButton.setTextAlignment(TextAlignment.CENTER);
		Button superPotionButton = new Button("Super Potion\nRestore 50 ♥\n40 Gold");
		superPotionButton.setOnMouseClicked((Event t) -> {
			if(player.buy(10)) {
				player.addHealth(10);
			}
			playerStatsShop.setText(player.toString());
		});
		superPotionButton.setMinSize(btnWidth, btnHeight);
		superPotionButton.setMaxSize(btnWidth, btnHeight);
		superPotionButton.setPrefSize(btnWidth, btnHeight);
		superPotionButton.setTextAlignment(TextAlignment.CENTER);
		
		marketContainer.getChildren().addAll(potionButton, superPotionButton);
		for(Item i: itemList) {
			ItemButton ib = new ItemButton(i, player, playerStatsShop);
			ib.setMinSize(btnWidth, btnHeight);
			ib.setMaxSize(btnWidth, btnHeight);
			ib.setPrefSize(btnWidth, btnHeight);
			ib.setTextAlignment(TextAlignment.CENTER);
			marketContainer.getChildren().add(ib);
		}
		shopContainer.setRight(playerStatsShop);
		shopContainer.setCenter(marketContainer);
		shopTab.setContent(shopContainer);
		
		// Level Tab
		
		BorderPane levelContainer = new BorderPane();
		GridPane levelPit = new GridPane();
		levelPit.setHgap(5);
		levelPit.setVgap(5);
		levelPit.setPadding(padding);
		
		Text playerStatsLevel = new Text(player.toString());
		levelTab.setOnSelectionChanged((Event t) -> {
			playerStatsLevel.setText(player.toString());
		});
		Button levelUpHealth = new Button("♥ ▲");
		levelUpHealth.setOnMouseClicked((Event t) -> {
			if(player.addLevel()) {
				player.addMaxHealth(1);
				playerStatsLevel.setText(player.toString());
			}
		});
		Button levelUpDamage = new Button("🗡 ▲");
		levelUpDamage.setOnMouseClicked((Event t) -> {
			if(player.addLevel()) {
				player.addDamage(1);
				playerStatsLevel.setText(player.toString());
			}
		});
		Button levelUpAccuracy = new Button("⌖  ▲");
		levelUpAccuracy.setOnMouseClicked((Event t) -> {
			if(player.addLevel()) {
				player.addAccuracy(1);
				playerStatsLevel.setText(player.toString());
			}
		});
		Button levelUpEvasion = new Button("» ▲");
		levelUpEvasion.setOnMouseClicked((Event t) -> {
			if(player.addLevel()) {
				player.addEvasion(1);
				playerStatsLevel.setText(player.toString());
			}
		});
		
		Button[] levelUpButtons = {levelUpHealth, levelUpDamage, levelUpAccuracy, levelUpEvasion};
		for(Button b : levelUpButtons) {
			b.setMinSize(btnWidth, btnHeight);
			b.setMaxSize(btnWidth, btnHeight);
			b.setPrefSize(btnWidth, btnHeight);
			b.setStyle("-fx-font-size: 28pt;");
		}
		
		levelPit.add(levelUpHealth, 0, 0);
		levelPit.add(levelUpDamage, 0, 1);
		levelPit.add(levelUpAccuracy, 1, 0);
		levelPit.add(levelUpEvasion, 1, 1);
		levelContainer.setRight(playerStatsLevel);
		levelContainer.setCenter(levelPit);
		levelTab.setContent(levelContainer);
		
		// Finish up
		
		stage.setTitle("NumberCrunch!");
		Scene scene = new Scene(mainWindow);
		scene.getStylesheets().add("userinterface/styles.css");
		stage.setScene(scene);
		stage.show();
	}

}
