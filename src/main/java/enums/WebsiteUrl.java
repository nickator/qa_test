package enums;

public enum WebsiteUrl {
    LOGIN("http://localhost:8087/login"),
    GOOGLE("https://www.google.com/");

    private final String url;

    WebsiteUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }
}