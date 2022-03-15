package by.it.academy.hw1_messenger.messenger.model;

public class Pageble {
    private int size;
    private int page;

    public Pageble(int size, int page) {
        this.size = size;
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
