package cn.com.collection;

public class TestMyHash<K,V> {

    Node2[] table; //位桶数组 bucket array
    int size;  //存放的键值对的个数

    public TestMyHash() {
        table = new Node2[16]; //长度为2的整数次幂
    }
    public void put(K key,V value) {
        //定义新的节点对象
        Node2 node2 =new Node2();
        node2.hash = myHash(key.hashCode(),table.length);
        node2.key = key;
        node2.value = value;
        node2.next = null;
        Node2 tmp = table[node2.hash];
        Node2 lastnode = null;
        int flag = 0;
        if (tmp == null) {
            //数组元素为空，把这个新节点直接放进去
            table[node2.hash] = node2;
            size++;
        }else {
            //数组元素不为空，遍历此处对应的链表
            while(tmp != null) {
                //判断key如果重复，则覆盖,其它的值保持不变
                if (tmp.key.equals(key)) {
                    flag = 1;
                    tmp.value = value;
                    break;
                }else {
                    //不重复，遍历下一个
                    lastnode = tmp;
                    tmp = tmp.next;
                }
            }
            if (flag == 0) { // 没有重复 则说明flag没有被改变
                lastnode.next = node2;
                size++;
            }
        }
    }
    public V get(Object K) {
        int hash = myHash(K.hashCode(),table.length);
        V value = null;
        if (table[hash] != null) {
            //如果数组的位置不为空，则把第一个节点取出来
            Node2 tmp = table[hash];
            while(tmp != null) {
                if (tmp.key.equals(K)) {
                    value  = (V) tmp.value;
                    break;
                }else {
                    tmp = tmp.next;
                }
            }
        }
        return value;
    }
    @Override
    public String toString() {
        StringBuilder sb  = new StringBuilder("{");
        for (int i = 0; i <table.length ; i++) {
            Node2 tmp = table[i];
            while(tmp != null) {
                sb.append(tmp.key+":"+tmp.value+",");
                tmp = tmp.next;
            }
        }
        sb.setCharAt(sb.length()-1,'}');
        return sb.toString();
    }

    public static void main(String[] args) {
        TestMyHash<Integer,String> t = new TestMyHash<>();
        t.put(10,"小明");
        t.put(20,"小军");
        t.put(30,"小红");
        t.put(40,"小亮");
        System.out.println(t);
        System.out.println(t.get(10));

        //找到hash值相同的值 69,53,37  ---- 5
//        for (int i = 10; i <100 ; i++) {
//            System.out.println(i+"----"+myHash(i,16));
//        }
    }

    public static int myHash(int hashCode,int length) {
        //System.out.println("myhash:"+(hashCode&(length-1)));
        return hashCode&(length-1);
    }
}
