public class NotValidAutosarFileException extends Exception {
    public NotValidAutosarFileException() {
        super("Input file is not a valid Autosar file (.arxml)");
    }
}
public class EmptyAutosarFileException extends Exception {
    public EmptyAutosarFileException(String message) {
        super(message);
    }
}
