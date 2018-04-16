package ip;

import ip.Calculator;
import ip.EventListenerCIDR;
import ip.EventListenerIP;
import ip.EventListenerSubNet;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;

public class MainClass {
    private JFrame frame;
    static JTextField input_ip;
    static JTextField input_cidr;
    static JTextField input_subnet;
    static JTextField output_device;
    static JTextField output_network;
    static JTextField output_gateway;
    static JTextField output_broadcast;
    static JTextField output_max_ip;
    static JTextField output_hosts;
    static JLabel lblNotValidIP;
    static JLabel lblNotValidCIDR;
    static JLabel lblNotValidSubnet;
    static Calculator calculator;
    static DocumentListener ipListener;
    static DocumentListener subnetListener;
    static DocumentListener cidrListener;
	static JTextField lastUsed = null;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){

            @Override
            public void run() {
                try {
                    MainClass window = new MainClass();
                    window.frame.setVisible(true);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MainClass() {
    	ipListener = new EventListenerIP();
        subnetListener = new EventListenerSubNet();
        cidrListener = new EventListenerCIDR();
        initialize();
        this.frame.setTitle("IP-Calc");
    }

    private void initialize() {
        this.frame = new JFrame();
        this.frame.setBounds(100, 100, 330, 295);
        this.frame.setDefaultCloseOperation(3);
        this.frame.getContentPane().setLayout(null);
        
        JLabel lblIpadresse = new JLabel("IP-Adresse");
        lblIpadresse.setFont(new Font("Tahoma", 0, 11));
        lblIpadresse.setBounds(10, 3, 53, 14);
        this.frame.getContentPane().add(lblIpadresse);
        
        input_ip = new JTextField();
        input_ip.setBounds(98, 0, 100, 20);
        this.frame.getContentPane().add(input_ip);
        input_ip.setColumns(15);
        input_ip.getDocument().addDocumentListener(ipListener);
        
        lblNotValidIP = new JLabel("Keine IP-Adresse");
        lblNotValidIP.setFont(new Font("Tahoma", 0, 11));
        lblNotValidIP.setBounds(205, 3, 158, 14);
        lblNotValidIP.setForeground(Color.RED);
        this.frame.getContentPane().add(lblNotValidIP);
        
        JLabel lblCidr = new JLabel("CIDR-Suffix");
        lblCidr.setFont(new Font("Tahoma", 0, 11));
        lblCidr.setBounds(10, 28, 57, 14);
        this.frame.getContentPane().add(lblCidr);
        
        input_cidr = new JTextField();
        input_cidr.setBounds(98, 25, 100, 20);
        input_cidr.setColumns(15);
        this.frame.getContentPane().add(input_cidr);
        input_cidr.getDocument().addDocumentListener(cidrListener);
        
        lblNotValidCIDR = new JLabel("Kein CIDR-Suffix");
        lblNotValidCIDR.setFont(new Font("Tahoma", 0, 11));
        lblNotValidCIDR.setBounds(205, 28, 157, 14);
        lblNotValidCIDR.setForeground(Color.RED);
        this.frame.getContentPane().add(lblNotValidCIDR);
        
        JLabel lblSubnetzmaske = new JLabel("Subnetzmaske");
        lblSubnetzmaske.setFont(new Font("Tahoma", 0, 11));
        lblSubnetzmaske.setBounds(10, 53, 69, 14);
        this.frame.getContentPane().add(lblSubnetzmaske);
        
        input_subnet = new JTextField();
        input_subnet.setBounds(98, 50, 100, 20);
        input_subnet.setColumns(10);
        this.frame.getContentPane().add(input_subnet);
        input_subnet.getDocument().addDocumentListener(subnetListener);
        
        lblNotValidSubnet = new JLabel("Keine Subnetzmaske");
        lblNotValidSubnet.setFont(new Font("Tahoma", 0, 11));
        lblNotValidSubnet.setBounds(205, 53, 166, 14);
        lblNotValidSubnet.setForeground(Color.RED);
        this.frame.getContentPane().add(lblNotValidSubnet);
        
        JLabel lblDevice = new JLabel("Ger\u00e4teteil");
        lblDevice.setFont(new Font("Tahoma", 0, 11));
        lblDevice.setBounds(10, 108, 47, 14);
        this.frame.getContentPane().add(lblDevice);
        
        output_device = new JTextField();
        output_device.setBounds(98, 105, 100, 20);
        output_device.setEditable(false);
        output_device.setColumns(10);
        this.frame.getContentPane().add(output_device);
        
        JLabel lblNetzwerkadresse = new JLabel("Netzwerkadresse");
        lblNetzwerkadresse.setFont(new Font("Tahoma", 0, 11));
        lblNetzwerkadresse.setBounds(10, 133, 83, 14);
        this.frame.getContentPane().add(lblNetzwerkadresse);
        
        output_network = new JTextField();
        output_network.setBounds(98, 130, 100, 20);
        output_network.setEditable(false);
        output_network.setColumns(10);
        this.frame.getContentPane().add(output_network);
        
        JLabel lblGateWay = new JLabel("Default-Gateway");
        lblGateWay.setFont(new Font("Tahoma", 0, 11));
        lblGateWay.setBounds(10, 158, 82, 14);
        this.frame.getContentPane().add(lblGateWay);
        
        output_gateway = new JTextField();
        output_gateway.setBounds(98, 155, 100, 20);
        output_gateway.setEditable(false);
        output_gateway.setColumns(10);
        this.frame.getContentPane().add(output_gateway);
        
        JLabel lblBroadcast = new JLabel("Broadcast");
        lblBroadcast.setFont(new Font("Tahoma", 0, 11));
        lblBroadcast.setBounds(10, 183, 48, 14);
        this.frame.getContentPane().add(lblBroadcast);
        
        output_broadcast = new JTextField();
        output_broadcast.setBounds(98, 180, 100, 20);
        output_broadcast.setEditable(false);
        output_broadcast.setColumns(10);
        this.frame.getContentPane().add(output_broadcast);
        
        JLabel lblMaxip = new JLabel("Max-IP");
        lblMaxip.setFont(new Font("Tahoma", 0, 11));
        lblMaxip.setBounds(10, 208, 34, 14);
        this.frame.getContentPane().add(lblMaxip);
        
        output_max_ip = new JTextField();
        output_max_ip.setBounds(98, 205, 100, 20);
        output_max_ip.setEditable(false);
        output_max_ip.setColumns(10);
        this.frame.getContentPane().add(output_max_ip);
        
        JLabel lblHosts = new JLabel("Hosts");
        lblHosts.setFont(new Font("Tahoma", 0, 11));
        lblHosts.setBounds(10, 233, 27, 14);
        this.frame.getContentPane().add(lblHosts);
        
        output_hosts = new JTextField();
        output_hosts.setBounds(98, 230, 100, 20);
        output_hosts.setEditable(false);
        output_hosts.setColumns(10);
        this.frame.getContentPane().add(output_hosts);
        
        calculator = new Calculator();
    }
    
    static boolean ready() {
    	return !lblNotValidCIDR.isVisible() && (!lblNotValidCIDR.isVisible() || !lblNotValidSubnet.isVisible());
    }

    static void calculate(String ip, String cidr, String subnet) {
		System.out.println(ip + " " + subnet + " " + cidr);
		if (subnet != "") {
			calculator.setSubnet(subnet);
			input_cidr.setText(calculator.getCIDRFromSubnet(subnet));
		} else if (cidr != "") {
			calculator.setCIDR(cidr);
			input_subnet.setText(calculator.getSubnetFromCIDR(cidr));
		}
		
		lastUsed = null;
		
        output_device.setText(calculator.getGeraeteTeil());
        output_network.setText(calculator.getNetworkAddress());
        output_broadcast.setText(calculator.getBroadcast());
        output_max_ip.setText(calculator.getMaxIP());
        output_gateway.setText(calculator.getDefaultGateway());
        output_hosts.setText(calculator.getHosts());
    }

}

