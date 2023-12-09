package it.unisa.diem.SEGroup9;

import it.unisa.diem.actions.MessageAction.AlertDisplayer;

/**
 * The AlertController class is responsible for managing alerts in a Java application.
 * It provides methods to initialize the alert system and display alerts.
 */
public class AlertController {
    private static Boolean initialized = false;
    private static AlertDisplayer alertDisplayer;

    /**
     * Initializes the alert system by setting the alertDisplayer field to the provided AlertDisplayer object.
     * @param alert The AlertDisplayer object used to display alerts.
     */
    public static void initialize(AlertDisplayer alert) {
        if (!initialized) {
            // Initialize the alert necessary implementations
            alertDisplayer = alert;
            initialized = true;
        } else {
            System.err.println("Alert already initialized");
        }
    }

    /**
     * Displays an alert with the specified title, header, and message.
     * It uses the alertDisplayer object to display the alert.
     * @param title The title of the alert.
     * @param header The header of the alert.
     * @param message The message of the alert.
     */
    public static void displayAlert(String title, String header, String message) {
        alertDisplayer.displayAlert(title, header, message);
    }
    public static void displayAlertWarning(String title, String header, String message) {
        alertDisplayer.displayAlertWarning(title, header, message);
    }
}
