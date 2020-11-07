/*
 * Main Assignment
 * Author: Claudia Gonzalez
 * Student Number: 2020085
 */
package ls.model;

/**
 *
 * @author claudialuizagonzalezferrufino
 */
public class Sort {
    private String id;
    private String field;

    public Sort(String id, String field) {
        this.id = id;
        this.field = field;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return "Sort{" + "id=" + id + ", field=" + field + '}';
    }
    
}
