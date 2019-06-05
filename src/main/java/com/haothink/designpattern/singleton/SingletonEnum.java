package com.haothink.designpattern.singleton;

/**java1.5发行版之后比较推荐的一种方式
 * 绝对防止实例化，即使在面对复杂的序列化或者反射攻击
 * */
public enum SingletonEnum {
     INSTANCE;


}
