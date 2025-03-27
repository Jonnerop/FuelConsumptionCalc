package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.*;

import java.text.DecimalFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class HelloController {
    @FXML private Label lblDistance, lblFuel, lblResult, lblTime;
    @FXML private TextField tfDistance, tfFuel;
    @FXML private Button btnCalculate;

    private ResourceBundle rb;

    public void initialize() {
        setLanguage(new Locale("en", "US"));
    }

    private void setLanguage(Locale locale) {
        lblResult.setText("");
        try {
            rb = ResourceBundle.getBundle("messages", locale);

            lblDistance.setText(rb.getString("distance"));
            lblFuel.setText(rb.getString("fuel"));
            btnCalculate.setText(rb.getString("calculate"));

            boolean isRTL = locale.getLanguage().equals("fa");
            NodeOrientation orientation = isRTL ? NodeOrientation.RIGHT_TO_LEFT : NodeOrientation.LEFT_TO_RIGHT;

            lblDistance.setNodeOrientation(orientation);
            lblFuel.setNodeOrientation(orientation);
            tfDistance.setNodeOrientation(orientation);
            tfFuel.setNodeOrientation(orientation);
            lblResult.setNodeOrientation(orientation);
            btnCalculate.setNodeOrientation(orientation);
            lblTime.setNodeOrientation(orientation);

            showCurrentTime(locale);
        } catch (MissingResourceException e) {
            lblResult.setText("Resource bundle missing.");
        }
    }

    private void showCurrentTime(Locale locale) {
        LocalTime now = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss", locale);
        lblTime.setText(rb.getString("time") + " " + now.format(formatter));
    }

    @FXML
    public void onCalculateClick(ActionEvent event) {
        try {
            double distance = Double.parseDouble(tfDistance.getText());
            double fuel = Double.parseDouble(tfFuel.getText());
            if (distance <= 0 || fuel <= 0) throw new NumberFormatException();

            double consumption = (fuel / distance) * 100;
            String consumptionStr = new DecimalFormat("#0.00").format(consumption);
            String resultPattern = rb.getString("result");
            String formattedResult = resultPattern.replace("{0}", consumptionStr);
            lblResult.setText(formattedResult);
        } catch (NumberFormatException e) {
            lblResult.setText(rb.getString("error"));
        }
    }

    @FXML public void onENClick(ActionEvent event) { setLanguage(new Locale("en", "US")); }
    @FXML public void onFRClick(ActionEvent event) { setLanguage(new Locale("fr", "FR")); }
    @FXML public void onJPClick(ActionEvent event) { setLanguage(new Locale("ja", "JP")); }
    @FXML public void onIRClick(ActionEvent event) { setLanguage(new Locale("fa", "IR")); }
}
