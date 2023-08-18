module WordCounter {
	requires javafx.controls;
	requires javafx.fxml;
	
	opens application to javafx.graphics, javafx.fxml;
	 opens bean to javafx.base; 
	
}
