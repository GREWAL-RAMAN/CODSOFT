package application;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertOptions {

    public static void  incorrectInfo(String Error)
    {
        Alert a=new Alert(Alert.AlertType.ERROR);
        a.setTitle("ERROR");
        a.setHeaderText("");
        a.setContentText(Error);
        a.show();
    }
    public static void MessageShow(String Info)
    {
        Alert b=new Alert(Alert.AlertType.INFORMATION);
        b.setContentText(Info);
        b.show();
    }
    public static int  ConfirmBox(String Info)
    {
        Alert c=new Alert(Alert.AlertType.CONFIRMATION);
        c.setContentText(Info);
       Optional<ButtonType> result=  c.showAndWait();
       if((result.isPresent())&&(ButtonType.OK==result.get()))
        return 1;
       else
           return 0;
    }
    public static void RowNotSelected(String operation)
    {
        Alert d=new Alert(Alert.AlertType.ERROR);
        d.setContentText("Please Select the Row for "+operation+" operation");
        d.show();
    }
    public static void IncompleteInfo(String text)
    {
        Alert d=new Alert(Alert.AlertType.ERROR);
        d.setContentText(text);
        d.show();
    }

}
