package by.it.academy.hw1_messenger.messenger.model;

public class Pageble {
    private int page;
    private int size;

    public Pageble() {

    }

    public Pageble(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public static Pageble of(int page, int size) {
        Pageble pageble = new Pageble();
        pageble.setSize(size);
        pageble.setPage(page);
        return pageble;
    }
}
