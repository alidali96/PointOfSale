package app;

public enum Brand {

    SAMSUNG, HTC, HAUWEI, SONY, NOKIA;

    public String toString() {
        switch (this) {
            case SAMSUNG: return "Samsung";
            case HTC: return "HTC";
            case HAUWEI: return "Hauwei";
            case SONY: return "Sony";
            case NOKIA: return "Nokia";
        }
        return null;
    }

    // Need that to convert from String (csv) .txt to Brand when initializing a Phone object
    // Still actually not sure why would I need an enum?
    public static Brand toBrand(String brand) {
        switch (brand) {
            case "Samsung": return SAMSUNG;
            case "HTC": return HTC;
            case "Hauwei": return HAUWEI;
            case "Sony": return SONY;
            case "Nokia": return NOKIA;
        }
        return null;
    }
}
