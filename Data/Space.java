package Data;

import java.util.ArrayList;

import Controller.Controller;

// represents a space which can be divided along the x or y axis 
// along the median of all objects inside
// the division will stop when the maxDepth is reached or further division will not 
// reduce the number of objects inside the resulting space

public class Space {
    private Space left;
    private Space right;

    private int depth;
    public static final int maxDepth = 8;

    public Space(int depth) {
        this.depth = depth;
    }

    public Space() {
        this.depth = 0;
    }

    // used to calculate the median on the x or y axis of the objects
    public double getMedian(ArrayList<Circle> list, boolean isX) {
        int tot = 0;
        int i;
        for (i = 0; i < list.size(); i++) {
            tot += list.get(i).getPos().get(isX);
        }
        return (double) tot / i;
    }

    // divides the space into sub spaces and divides the arrayList in those spaces
    // this will form a tree structure
    // when isX is true the x axis will be used for division
    // when false the y axis
    public void divide(ArrayList<Circle> ar, boolean isX) {
        if (depth >= Space.maxDepth) {
            makeLeaf(ar);
            return;
        }
        double med = getMedian(ar, isX);
        ArrayList<Circle> l = new ArrayList<Circle>();
        ArrayList<Circle> r = new ArrayList<Circle>();
        for (Circle c : ar) {
            if (c.left(med, isX)) {
                l.add(c);
            } else if (c.right(med, isX)) {
                r.add(c);
            } else {
                l.add(c);
                r.add(c);
            }
        }
        if (isUsefull(ar.size(), l.size(), r.size())) {
            left = new Space(depth + 1);
            right = new Space(depth + 1);
            left.divide(l, !isX);
            right.divide(r, !isX);
        } else {
            makeLeaf(ar);
        }

    }

    // used when no further divisions are possible without going to deep or useless
    // divisions
    public void makeLeaf(ArrayList<Circle> ar) {
        left = new SpaceLeaf(ar);
        right = new SpaceLeaf();
    }

    public void collide(Controller c) {
        left.collide(c);
        right.collide(c);
    }

    // checks if the division is useful or not
    public boolean isUsefull(int totLen, int lLen, int rLen) {
        if (lLen >= totLen || rLen >= totLen) {
            return false;
        }
        return true;
    }

    // used for testing and debugging
    public String toString() {
        String s = "S:{";
        s += left.toString() + ", ";
        s += right.toString() + "}";
        return s;
    }

    // test routine
    public static void main(String[] args) {
        ArrayList<Circle> a = new ArrayList<Circle>();
        a.add(new Circle(0, 0, 1));
        a.add(new Circle(2, 1, 1));
        a.add(new Circle(2, 2, 1));
        Space s = new Space();
        s.divide(a, true);
        System.out.println(s);
    }
}