package cn.com.collection;

import java.util.HashMap;
//测试的是HashSet
public class TestSet {
    //HashSet其实就是一个简化版的HashMap
    HashMap map;
    private static final Object PRESENT = new Object();
    public TestSet() {
        map = new HashMap();
    }



    public int size() {
        return map.size();
    }
    public void add(Object o) {
        map.put(o,PRESENT);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Object o:map.keySet()) {
            sb.append(o+",");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        TestSet t = new TestSet();
        t.add(1);
        t.add(2);
        t.add(3);

        System.out.println(t);
    }
}
