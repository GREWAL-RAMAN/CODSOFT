package application;


import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import bean.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

public class WordCounter implements Initializable {
	@FXML
	public TextArea textInput;
	
	@FXML
	public Button Submit;
	@FXML
	public Button Refresh; 
	@FXML
	public Label wordCount;
	@FXML
	public Label uniquewordCount;
	@FXML
	public TableView<Word> wordTable;
	@FXML 
	TableColumn<Word,String> uniqueCol;
	ObservableList<Word> ListUniqueWord = FXCollections.observableArrayList();

	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	 uniqueCol.setCellValueFactory(new PropertyValueFactory<>("value"));
	}

	public void WordCount()
	{
		ListUniqueWord.clear();
        String text = textInput.getText();
       text= text.trim();

        if (text.isEmpty()) {
           AlertOptions.IncompleteInfo("No input provided.");
            return;
        }

        String[] words = splitTextIntoWords(text);
        int totalWordCount = words.length;

        if (totalWordCount == 0) {
        	AlertOptions.IncompleteInfo("No words found in the input. Exiting.");
            return;
        }
        wordCount.setText(""+totalWordCount);
        int uniqueWordCount = getUniqueWordCount(words);
        uniquewordCount.setText("" +uniqueWordCount);
        ListUniqueWord.addAll(getUniqueWords(words));
       
         wordTable.setItems(ListUniqueWord);

	}
	
	public void Refresh()
	{
		textInput.setText("");
		wordCount.setText("");
		uniquewordCount.setText("");
		ListUniqueWord.clear();
		wordTable.setItems(ListUniqueWord);
	}
	
	private static String[] splitTextIntoWords(String text) {
        // Split the text into words using space and punctuation as delimiters
        return text.split("[\\s.,;!?]+");
    }
	private static ObservableList<Word> getUniqueWords(String[] words) {
	    Set<String> uniqueWords = new HashSet<>(Arrays.asList(words));
	    ObservableList<Word> uniqueWordList = FXCollections.observableArrayList();
	    
	    for (String word : uniqueWords) {
	        uniqueWordList.add(new Word(word));
	    }
	  
	    return uniqueWordList;
	}

	    private static int getUniqueWordCount(String[] words) {
	        // Use a set to keep track of unique words
	        Set<String> uniqueWords = new HashSet<>(Arrays.asList(words));
	        return uniqueWords.size();
	    }

	 
}
