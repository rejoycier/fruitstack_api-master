package fruitstack;

import fruitstack.cukes.BaseTest;

public enum FruitStackEndPoints {
    CURRENT(BaseTest.BASE_URI + "/current?access_key={access_key}&query={query}");

    private final String url;

    FruitStackEndPoints(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}


