package domain;

import java.util.List;
public class Vector {

    private List<Integer> contentOfVector;
    private int length;

    public Vector(List<Integer> content) {
        this.contentOfVector = content;
        this.length = content.size();
    }

    public int get(int index){
        return contentOfVector.get(index);
    }

    public int getLength() {
        return length;
    }
}
