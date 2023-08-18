package application;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import bean.Student;
import dao.StudentDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DashboardManage implements Initializable {
	private Stage stage;

	@FXML
	private Button add_button;
	@FXML
	private Button edit_button;
	@FXML
	private Button delete_button;
	@FXML
	private Button filter_button;
	@FXML
	private Button refresh_button;
	@FXML
	private TableView<Student> studentTable;
	@FXML
	private TableColumn<Student, Long> adminCol;
	@FXML
	private TableColumn<Student, String> nameCol;
	@FXML
	private TableColumn<Student, String> guardCol;
	@FXML
	private TableColumn<Student, Long> ContactCol;
	@FXML
	private TableColumn<Student, Long> rollCol;
	@FXML
	private TableColumn<Student, String> yearCol;
	@FXML
	private TableColumn<Student, String> gradeCol;

	@FXML
	private TextField fl_id;
	@FXML
	private TextField fl_name;
	@FXML
	private TextField fl_contact;
	@FXML
	private TextField fl_roll;
	@FXML
	private TextField fl_grade;
	@FXML
	private TextField fl_year;
	@FXML
	private TextField fl_guard;

	public static long studentId = -1;

	ObservableList<Student> studentList = FXCollections.observableArrayList();

	public static SessionFactory factory;
	public static Session mySession;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();
		mySession = factory.openSession();
		adminCol.setCellValueFactory(new PropertyValueFactory<>("Admission_no"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		ContactCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
		guardCol.setCellValueFactory(new PropertyValueFactory<>("Guardian_name"));
		yearCol.setCellValueFactory(new PropertyValueFactory<>("Year"));
		rollCol.setCellValueFactory(new PropertyValueFactory<>("rollNumber"));
		gradeCol.setCellValueFactory(new PropertyValueFactory<>("grade"));
		Refresh();

		studentTable.setItems(studentList);

	}

	public void Refresh() {
		if (studentList.isEmpty()) {
			studentList.addAll(StudentDao.GetAllStudent(mySession));
		} else {
			studentList.clear();
			studentList.addAll(StudentDao.GetAllStudent(mySession));
		}

		fl_id.setText("");
		fl_name.setText("");
		fl_guard.setText("");
		fl_grade.setText("");
		fl_contact.setText("");
		fl_year.setText("");
		fl_roll.setText("");
	}

	public void openNew() throws Exception {
		stage = new Stage();
		Scene b = new Scene(new FXMLLoader(Main.class.getResource("AddStudent.fxml")).load(), 800, 500);
		 InputStream iconStream = getClass().getResourceAsStream("/application/icons/add.png");
	        
	        if (iconStream != null) {
	            Image icon = new Image(iconStream);

	            // Set the application icon for the stage
	            stage.getIcons().add(icon);
	        } else {
	            System.err.println("Icon image not found.");
	        }
	        stage.setTitle("ADD STUDENT");
		stage.setMinHeight(500);
		stage.setMinWidth(800);
		stage.setMaxHeight(500);
		stage.setMaxWidth(800);
		stage.setResizable(false);
		stage.setOnCloseRequest(event -> {

			Refresh();
			System.out.println("add stage is closed");
		});

		stage.setScene(b);
		stage.initModality(Modality.APPLICATION_MODAL);

		stage.show();

	}

	public void openEdit() {

		try {

			int id = studentTable.getSelectionModel().getSelectedIndex();
			if (id >= 0) {
				studentId = adminCol.getCellData(id);

				stage = new Stage();
				Scene b;
				FXMLLoader loader;
				loader = new FXMLLoader(Main.class.getResource("EditStudent.fxml"));
				b = new Scene(loader.load(), 800, 500);
				 InputStream iconStream = getClass().getResourceAsStream("/application/icons/add.png");
			        
			        if (iconStream != null) {
			            Image icon = new Image(iconStream);

			            // Set the application icon for the stage
			            stage.getIcons().add(icon);
			        } else {
			            System.err.println("Icon image not found.");
			        }

			    stage.setTitle("EDIT STUDENT");  
				stage.setMinHeight(500);
				stage.setMinWidth(800);
				stage.setMaxHeight(500);
				stage.setMaxWidth(800);
				stage.setResizable(false);
				stage.setScene(b);
				stage.setOnCloseRequest(event -> {
					studentId = -1;
					Refresh();
					System.out.println("edit stage is closed");
				});
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.show();

			} else {
				AlertOptions.RowNotSelected("UPDATE");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
		}

	}

	public void deleteStudent() {

		int id = studentTable.getSelectionModel().getSelectedIndex();
		if (id >= 0) {
			long sid = adminCol.getCellData(id);
			int i = AlertOptions.ConfirmBox("Confirm to proceed!");
			if (i == 1) {
				StudentDao.RemoveStudentById(mySession, sid);
			}
			Refresh();
		} else {
			AlertOptions.RowNotSelected("DELETE");
		}

	}

	public void filterStudent() {

		String filter = " WHERE 1=1";
		if (!fl_id.getText().equals("")) {
			long idValue = Long.parseLong(fl_id.getText());
			filter += " AND Admission_no = " + idValue + " ";
		}
		if (!fl_contact.getText().equals("")) {
			long contactValue = Long.parseLong(fl_contact.getText());
			filter += " AND contact=" + contactValue + " ";
		}
		if (!fl_name.getText().equals("")) {
			filter += " AND name LIKE '%" + fl_name.getText() + "%'";
		}
		if (!fl_guard.getText().equals("")) {
			filter += " AND Guardian_name LIKE '%" + fl_guard.getText() + "%'";
		}
		if (!fl_grade.getText().equals("")) {
			filter += " AND grade  LIKE '%" + fl_grade.getText() + "%'";
		}
		if (!fl_roll.getText().equals("")) {
			long rollValue = Long.parseLong(fl_roll.getText());
			filter += " AND rollNumber=" + rollValue + " ";
		}
		if (!fl_year.getText().equals("")) {
			filter += " AND Year LIKE '%" + fl_year.getText() + "%'";
		}

		studentList.clear();
		studentList.addAll(StudentDao.GetFilterStudent(mySession, filter));

	}

}
