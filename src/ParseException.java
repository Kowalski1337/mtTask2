public class ParseException extends Exception {
    ParseException(String what){
        super(what);
    }

    ParseException(String what, int where) {
        super(what + " at position: " + Integer.toString(where));
    }
}
