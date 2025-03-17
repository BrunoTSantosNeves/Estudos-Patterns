class OldPrinter {
    public void printUpper(String message) {
        System.out.println(message.toUpperCase());
    }
}

interface Printer {
    void print(String message);
}

class PrinterAdapter implements Printer {
    private final OldPrinter oldPrinter;

    public PrinterAdapter(OldPrinter oldPrinter) {
        this.oldPrinter = oldPrinter;
    }

    @Override
    public void print(String message) {
        oldPrinter.printUpper(message);
    }
}

public class NewAdapter {
    public static void main(String[] args) {
        OldPrinter oldPrinter = new OldPrinter();
        Printer adapter = new PrinterAdapter(oldPrinter);

        adapter.print("Hello, Adapter Pattern!"); 
    }
}
