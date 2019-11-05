public enum Type {
    PHONE, LAPTOP, DESKTOP;

    public String toString() {
        switch (this) {
            case PHONE: return "Phone";
            case LAPTOP: return "Laptop";
            case DESKTOP: return "Desktop";
        }
        return null;
    }

    public static Type toType(String type) {
        switch (type) {
            case "Phone": return PHONE;
            case "Laptop": return LAPTOP;
            case "Desktop": return DESKTOP;
        }
        return null;
    }

}
