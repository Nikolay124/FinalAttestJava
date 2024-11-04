import java.util.Objects;

public class Laptop {
    private String model;
    private int ram;
    private int hddSize;
    private String os;
    private String color;

    public Laptop(String model, int ram, int hddSize, String os, String color) {
        this.model = model;
        this.ram = ram;
        this.hddSize = hddSize;
        this.os = os;
        this.color = color;
    }

    // Геттеры для всех полей
    public String getModel() { return model; }
    public int getRam() { return ram; }
    public int getHddSize() { return hddSize; }
    public String getOs() { return os; }
    public String getColor() { return color; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Laptop)) return false;
        Laptop laptop = (Laptop) o;
        return ram == laptop.ram &&
                hddSize == laptop.hddSize &&
                Objects.equals(model, laptop.model) &&
                Objects.equals(os, laptop.os) &&
                Objects.equals(color, laptop.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, ram, hddSize, os, color);
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "model='" + model + '\'' +
                ", ram=" + ram +
                ", hddSize=" + hddSize +
                ", os='" + os + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}