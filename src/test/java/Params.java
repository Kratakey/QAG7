public enum Params {

    SELENIDE("Селенид"), GRADLE("Градл"), REASON("Причина");

    private final String text;

    Params(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
