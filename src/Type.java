public enum Type {
    PHONE;

    public String toString() {
        switch (this) {
            case PHONE: return "Phone";
        }
        return null;
    }

    public static Type toType(String type) {
        switch (type) {
            case "Phone": return PHONE;
        }
        return null;
    }

}
