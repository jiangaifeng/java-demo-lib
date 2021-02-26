package cn.jiangaifeng.bean;

public class TestBean {
    int id;
    String name;

    ChildBean level;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ChildBean getLevel() {
        return level;
    }

    public void setLevel(ChildBean level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                '}';
    }
}
