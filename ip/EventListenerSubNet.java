package ip;

import ip.MainClass;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class EventListenerSubNet implements DocumentListener {
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
    	if (MainClass.lastUsed == MainClass.input_cidr) return;
        if (!MainClass.calculator.validateSubnet(MainClass.input_subnet.getText())) {
            MainClass.lblNotValidSubnet.setVisible(true);
        } else {
            MainClass.lblNotValidSubnet.setVisible(false);
        }
		
		MainClass.lastUsed = MainClass.input_subnet;
		System.out.println("subnet");
		if (MainClass.ready()) MainClass.calculate(MainClass.input_ip.getText(), "", MainClass.input_subnet.getText());
    }
}

