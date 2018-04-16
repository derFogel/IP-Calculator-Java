package ip;

import ip.MainClass;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class EventListenerIP implements DocumentListener {
    @Override
    public void changedUpdate(DocumentEvent arg0) {
        this.action();
    }

    @Override
    public void insertUpdate(DocumentEvent arg0) {
        this.action();
    }

    @Override
    public void removeUpdate(DocumentEvent arg0) {
        this.action();
    }

    private void action() {
        if (!MainClass.calculator.validateIP(MainClass.input_ip.getText())) {
            MainClass.lblNotValidIP.setVisible(true);
        } else {
            MainClass.lblNotValidIP.setVisible(false);
            MainClass.calculator.setIP(MainClass.input_ip.getText());
        }
        if (MainClass.ready()) MainClass.calculate(MainClass.input_ip.getText(), MainClass.lastUsed != MainClass.input_subnet ? "" : MainClass.lastUsed.getText(), MainClass.lastUsed != MainClass.input_cidr ? "" : MainClass.lastUsed.getText());
    }
}

