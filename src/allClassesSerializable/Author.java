package allClassesSerializable;

import java.io.Serializable;

public class Author implements Serializable {

    String name;
    String surname;

    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "{Name='" + name + '\'' +
                ", Surname='" + surname + '\'' +
                '}';
    }
}
