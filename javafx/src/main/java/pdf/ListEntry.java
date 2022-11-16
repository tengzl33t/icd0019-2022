package pdf;

public record ListEntry(String title, String url) {

    @Override
    public String toString() {
        return title;
    }
}
