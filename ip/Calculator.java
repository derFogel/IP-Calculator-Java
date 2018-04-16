package ip;

public class Calculator {
    private byte[] ip = new byte[4];
    private byte[] subnet = new byte[4];

    public Calculator() {
        this.ip[3] = 0;
        this.ip[2] = 0;
        this.ip[1] = 0;
        this.ip[0] = 0;
        this.subnet[3] = 0;
        this.subnet[2] = 0;
        this.subnet[1] = 0;
        this.subnet[0] = 0;
    }

    boolean validateIP(String ip) {
        return ip.matches("^[0-9]{1,3}[.][0-9]{1,3}[.][0-9]{1,3}[.][0-9]{1,3}$");
    }

    boolean validateSubnet(String subnet) {
        return subnet.matches("^[0-9]{1,3}[.][0-9]{1,3}[.][0-9]{1,3}[.][0-9]{1,3}$");
    }

    boolean validateCIDR(String cidr) {
        if (cidr.matches("^[0-9]{1,2}$") && Integer.parseInt(cidr) <= 32 && Integer.parseInt(cidr) >= 1) {
            return true;
        }
        return false;
    }

    void setIP(String ip) {
        if (ip == "") return;
        int i = 0;
        for (String part : ip.split("[.]")) {
            this.ip[i] = (byte) Integer.parseInt(part);
            i++;
        }
    }

    void setSubnet(String subnet) {
        if (subnet == "") return;
        int i = 0;
        for (String part : subnet.split("[.]")) {
            this.subnet[i] = (byte) Integer.parseInt(part);
            i++;
        }
    }

    void setCIDR(String cidr) {
        if (cidr == "") return;
        this.subnet[3] = 0;
        this.subnet[2] = 0;
        this.subnet[1] = 0;
        this.subnet[0] = 0;
        int i = 0;
        while (i < Integer.parseInt(cidr)) {
            this.subnet[i / 8] = (byte)(this.subnet[i / 8] | 128 >> i % 8);
            i++;
        }
    }

    String getSubnetFromCIDR(String cidr) {
        byte[] temp = new byte[4];
        int i = 0;
        while (i < Integer.parseInt(cidr)) {
            temp[i / 8] = (byte)(temp[i / 8] | 128 >> i % 8);
            ++i;
        }
        String out = new String();
        i = 0;
        while (i < 4) {
            out += (temp[i] & 0xff);
            if (i < 3) out += ".";
            i++;
        }
        return out;
    }
    
    String getCIDRFromSubnet(String subnet) {
        byte temp[] = new byte[4];
        int i = 0;
        for (String part : subnet.split("[.]")) {
            temp[i] = (byte) Integer.parseInt(part);
            i++;
        }
        int cidr = 0;
        for (i = 0; i < 32; i++) {
            if ((temp[i / 8] & 128) == 128) {
                cidr += 1;
                temp[i / 8] = (byte) (temp[i / 8] << 1);
            }
        }
        
        return Integer.toString(cidr);
    }

    String getNetworkAddress() {
        String out = new String();
        int i = 0;
        i = 0;
        while (i < 4) {
            out += (ip[i] & subnet[i] & 0xff);
            if (i < 3) out += ".";
            i++;
        }
        return out;
    }

    String getGeraeteTeil() {
        String out = new String();
        int i = 0;
        i = 0;
        while (i < 4) {
            out += (ip[i] & ~subnet[i] & 0xff);
            if (i < 3) out += ".";
            i++;
        }
        return out;
    }

    String getMaxIP() {
        String out = new String();
        int i = 0;
        i = 0;
        while (i < 4) {
            out += i < 3 ? ((ip[i] | ~subnet[i]) & 0xff) : ((ip[i] | ~subnet[i]) & 0xff - 1);
            if (i < 3) out += ".";
            i++;
        }
        return out;
    }

    String getBroadcast() {
        String out = new String();
        int i = 0;
        while (i < 4) {
            out += ((ip[i] | ~subnet[i]) & 0xff);
            if (i < 3) out += ".";
            i++;
        }
        return out;
    }

    String getDefaultGateway() {
        String out = new String();
        int i = 0;
        while (i < 4) {
            out += i < 3 ? (ip[i] & subnet[i] & 0xff) : ((ip[i] & subnet[i] & 0xff) + 1);
            if (i < 3) out += ".";
            i++;
        }
        return out;
    }

    String getHosts() {
        int hosts = 1;
        int i = 0;
        while (i < 4) {
            hosts *= (~ subnet[i] & 0xff) + 1;
            i++;
        }
        return Integer.toString(hosts - 2);
    }
}

