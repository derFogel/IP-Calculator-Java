package ip;

import ip.MainClass;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class EventListenerCIDR implements DocumentListener {
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
    	if (MainClass.lastUsed == MainClass.input_subnet) return;
        if (!MainClass.calculator.validateCIDR(MainClass.input_cidr.getText())) {
            MainClass.lblNotValidCIDR.setVisible(true);
        } else {
            MainClass.lblNotValidCIDR.setVisible(false);
        }
		
		MainClass.lastUsed = MainClass.input_cidr;
		System.out.println("cidr");
		if (MainClass.ready()) MainClass.calculate(MainClass.input_ip.getText(), MainClass.input_cidr.getText(), "");
    }
}

