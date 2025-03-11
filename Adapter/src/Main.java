// Version: 1.0
// Adapter Pattern

class OldSystem {
    public String getXMLData(){
        return "<data>Old System Data</data>";
    }
}

interface NewSystem {
    Object getdata();
}

class Adapter implements NewSystem {
    private OldSystem oldSystem;

    public Adapter(OldSystem oldSystem) {
        this.oldSystem = oldSystem;
    }

    @Override
    public Object getdata() {
        String xmlData = oldSystem.getXMLData();
        return xmlData;
    }
}

public class Main {
    public static void main(String[] args) {
        OldSystem oldSystem = new OldSystem();
        NewSystem newSystem = new Adapter(oldSystem);
        System.out.println(newSystem.getdata());
    }
}
