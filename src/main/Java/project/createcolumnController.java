package project;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import Code.Facade;
import Code.Task;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

import javafx.fxml.Initializable;

public class createcolumnController implements Initializable {
    @FXML
    private TextField txt_columnname;

    @FXML
    private Button btn_deny;

    @FXML
    private Button btn_accept;

    @FXML
    private void buttonClickedConfirm(){
        String columnName = txt_columnname.getText();
    }

    @FXML
    private void buttonDeny(){
        txt_columnname.clear();
    }


    

}
