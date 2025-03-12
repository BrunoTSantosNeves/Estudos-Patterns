// Version: 1.0
// Adapter Pattern

/*
 * class OldSystem {
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

 */

/*
 * public class Main {
    public static void main(String[] args) {
        OldSystem oldSystem = new OldSystem();
        NewSystem newSystem = new Adapter(oldSystem);
        System.out.println(newSystem.getdata());
    }
}
 */

 // Version: 2.1
// Adapter Pattern 

// Classe Legada (Retorna XML)
/*class SistemaAntigo {
    public String getXMLData() {
        return "<data>Old System Data</data>";
    }
}

// Interface esperada pelo novo sistema (Retorna JSON)
interface JsonDataProvider {
    Object getData();
}

// Conversor de XML para JSON
class ToJsonConverter {
    public static String convert(String xmlData) {
        return "{\"name\": \"Old System Data (converted)\" }";
    }
}

// Adapter
class Adaptador implements JsonDataProvider {
    private final SistemaAntigo sistemaAntigo;
    
    public Adaptador(SistemaAntigo sistemaAntigo) {
        this.sistemaAntigo = sistemaAntigo;
    }

    @Override
    public Object getData() {
        String xmlData = sistemaAntigo.getXMLData();
        return ToJsonConverter.convert(xmlData);
    }
}


 * public class Main {
    public static void main(String[] args) {
        SistemaAntigo sistemaAntigo = new SistemaAntigo();
        JsonDataProvider adapter = new Adaptador(sistemaAntigo);
    
        System.out.println(adapter.getData());
       
    }
}

 */


// Version: 2.2
// Adapter Pattern 

// Classe Legada (Retorna XML)
class SistemaAntigo {
    public String getXMLData() {
        return "<data>Old System Data</data>";
    }
}

// Interface para conversão de dados (Flexível para qualquer tipo de conversão)
interface DataConverter {
    String convert(String data);
}

// Conversor de XML para JSON
class ToJsonConverter implements DataConverter {
    @Override
    public String convert(String xmlData) {
        return "{\"name\": \"Old System Data (converted)\" }";
    }
}

// Adapter que aceita qualquer conversor
class Adaptador {
    private final SistemaAntigo sistemaAntigo;
    private final DataConverter converter;
    
    public Adaptador(SistemaAntigo sistemaAntigo, DataConverter converter) {
        this.sistemaAntigo = sistemaAntigo;
        this.converter = converter;
    }

    public String getData() { // Agora retorna diretamente a String convertida
        String xmlData = sistemaAntigo.getXMLData();
        return converter.convert(xmlData);
    }
}

// Classe Principal
public class Main {
    public static void main(String[] args) {
        SistemaAntigo sistemaAntigo = new SistemaAntigo();
        DataConverter converter = new ToJsonConverter(); 
        Adaptador adapter = new Adaptador(sistemaAntigo, converter);

        System.out.println(adapter.getData()); 
    }
}
