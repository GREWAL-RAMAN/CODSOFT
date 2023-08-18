package application;

import java.net.URL;
import java.util.ResourceBundle;

import bean.Student;
import dao.StudentDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddStudent implements Initializable {

	@FXML
	private TextField nameField;
	@FXML
	private TextField guardianField;
	@FXML
	private TextField contactField;
	@FXML
	private TextField rollField;
	@FXML
	private ComboBox<String> gradeBox;
	@FXML
	private ComboBox<String> yearBox;
	@FXML
	private Button addButton;
	@FXML
	private Label Labelnfo;
	ObservableList<String> yearList = FXCollections.observableArrayList("2020-2021", "2021-2022", "2022-2023",
			"2023-2024", "2024-2025", "2025-2026", "2026-2027", "2027-2028", "2028-2029", "2029-2030", "2030-2031",
			"2031-2032", "2032-2033", "2033-2034", "2034-2035", "2035-2036", "2036-2037", "2037-2038", "2038-2039",
			"2039-2040", "2040-2041", "2041-2042", "2042-2043", "2043-2044", "2044-2045", "2045-2046", "2046-2047",
			"2047-2048", "2048-2049", "2049-2050", "2050-2051");
	ObservableList<String> gradeList = FXCollections.observableArrayList("Grade 1", "Grade 2", "Grade 3", "Grade 4",
			"Grade 5", "Grade 6", "Grade 7", "Grade 8", "Grade 9", "Grade 10", "Grade 11", "Grade 12");

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		gradeBox.setItems(gradeList);
		yearBox.setItems(yearList);

	}

	public void addStudent() {
		int a = checkDetails();
		if (a == 1) {
			return;
		}
		Boolean Check = checkCorrectInput("name");
		if (Check == false) {
			AlertOptions.incorrectInfo("The input submitted for NAME is of wrong format. Please correct it!");
			return;
		}
		Check = checkCorrectInput("guard");
		if (Check == false) {
			AlertOptions.incorrectInfo("The input submitted for GUARDIAN NAME is of wrong format. Please correct it!");
			return;
		}
		Check = checkCorrectInput("Contact");
		if (Check == false) {
			AlertOptions.incorrectInfo("The input submitted for CONTACT is of wrong format. Please correct it!");
			return;
		}
		Check = checkCorrectInput("roll");
		if (Check == false) {
			AlertOptions.incorrectInfo("The input submitted for ROLLNUMBER is of wrong format. Please correct it!");
			return;
		}

		String name = nameField.getText();
		String guard = guardianField.getText();
		long roll = Long.parseLong(rollField.getText());
		Long contact = Long.parseLong(contactField.getText());
		String grade = gradeBox.getSelectionModel().getSelectedItem();
		String year = yearBox.getSelectionModel().getSelectedItem();
		Student student = new Student(name, roll, grade, contact, guard, year);

		int i = AlertOptions.ConfirmBox("Confirm to proceed!");
		if (i == 1) {
			boolean result = StudentDao.addStudent(student, DashboardManage.mySession);
			if (result) {
				Labelnfo.setText(student + " added Sucessfully");
			} else {
				Labelnfo.setText(student + " added Unsuccucessfully");
			}
		}

	}

	private int checkDetails() {
		if ((contactField.getText().isBlank()) || (nameField.getText().isBlank()) || (guardianField.getText().isBlank())
				|| (rollField.getText().isBlank() || gradeBox.getSelectionModel().getSelectedItem().isBlank()
						|| yearBox.getSelectionModel().getSelectedItem().isBlank())) {

			AlertOptions.IncompleteInfo();
			return 1;
		}
		return 0;
	}

	private Boolean checkCorrectInput(String Input) {
		if (Input.equals("Contact")) {
			try {
				 Long.parseLong(contactField.getText());
				return true;
			} catch (NumberFormatException e) {
				System.out.println("Input String cannot be parsed to Long.");
			}
			return false;
		} else if (Input.equals("roll")) {
			try {
			Long.parseLong(rollField.getText());
				return true;
			} catch (NumberFormatException e) {
				System.out.println("Input String cannot be parsed to Integer.");
			}
			return false;
		} else if (Input.equals("name")) {

			if (isString(nameField.getText())) {
				return true;
			} else {
				return false;
			}
		} else if (Input.equals("guard")) {

			if (isString(guardianField.getText())) {
				return true;
			} else {
				return false;
			}
		}

		return false;
	}

	private boolean isString(String input) {

		return input.matches("[a-zA-Z ]+");
	}

}
