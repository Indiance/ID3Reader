package id3reader;
import java.io.File;
import java.io.IOException;

import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.farng.mp3.id3.AbstractID3v2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Button uploadButton = new Button("Upload Song");
		VBox root = new VBox(uploadButton);
		Scene scene = new Scene(root, 300, 250);
		primaryStage.setScene(scene);
		primaryStage.show();
		uploadButton.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Select File");
			File selectedFile = fileChooser.showOpenDialog(primaryStage);
			if (selectedFile != null) {
				root.getChildren().removeIf(node -> node instanceof Label);
				try {
					MP3File mp3file = new MP3File(selectedFile);
					AbstractID3v2 id3v2 = mp3file.getID3v2Tag();
					root.getChildren().add(new Label("Artist: " + id3v2.getLeadArtist()));
					root.getChildren().add(new Label(("Album: " + id3v2.getAlbumTitle())));
					root.getChildren().add(new Label(("Genre: " + id3v2.getSongGenre())));
				} catch (IOException | TagException error) {
					error.printStackTrace();
				}
			}
		});
	}
	public static void main(String[] args) {
		launch(args);
	}
	
}
