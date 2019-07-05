package com.haothink.designpattern.iterator;

/**
 * @author wanghao
 * @date 2019年06月05日 15:27
 * description:
 */
public class Container implements ICollection {


    private Object[] objs = new Object[10];
    private int index=0;


    public void add(Object obj){
        if(objs.length==index){
            Object[] newObjs = new Object[index+index/2];
            System.arraycopy(objs, 0, newObjs, 0, objs.length);
            objs = newObjs;
        }
        objs[index] = obj;
        index++;
    }


    public int size(){
        return index;
    }


    public IIterator iiterator() {

        return new Itr();
    }

    private class Itr implements IIterator {
        int cursor;


        public Object next() {

            return objs[cursor++];
        }

        public boolean hasNext() {
            return cursor != size();
        }
    }
}
