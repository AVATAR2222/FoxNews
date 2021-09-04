package ir.news.core;

import java.io.Serializable;

public class Source implements Serializable {

    public String name,id;

    public Source(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
