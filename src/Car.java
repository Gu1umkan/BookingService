import java.math.BigDecimal;

public enum Car {
    FERRARI(BigDecimal.valueOf(1000), Driver.AYDAR, true),
    HONDA(BigDecimal.valueOf(500), Driver.DAVRON, false),
    MRS(BigDecimal.valueOf(400), Driver.BEKTURGAN, true),
    TOYOTA(BigDecimal.valueOf(100), Driver.ISLAM, true),
    KIA(BigDecimal.valueOf(340), Driver.ELDAR, true),
    AUDI(BigDecimal.valueOf(800), Driver.ULUKMAN, true),
    BMW(BigDecimal.valueOf(300), Driver.ISABEK, true);
    private BigDecimal price;
    private Driver driver;

    Car(BigDecimal balance, Driver driver, boolean book) {
        this.price = balance;
        this.driver = driver;
        this.book = book;
    }

    private boolean book;

    public boolean isBook() {
        return book;
    }

    public void setBook(boolean book) {
        this.book = book;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Driver getDriver() {
        return driver;
    }

    @Override
    public String toString() {
        return
                "\nводитель: " + driver.name() + driver

                ;
    }
}
