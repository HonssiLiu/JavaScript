package common;

public class Num2String {

	public static void main(String[] args) {
        int i = 548;
        
        //***** int 与 Integer对象 **************
        //把一个基本类型的变量,转换为Integer对象 
        Integer it = new Integer(i);
        //把一个Integer对象，转换为一个基本类型的int
		int i2 = it.intValue();
        //自动转换，叫装箱
        Integer it2 = i;
        //自动转换，就叫拆箱
        int i3 = it;
        
        //int的最大值 
        System.out.println(Integer.MAX_VALUE);
        //int的最小值       
        System.out.println(Integer.MIN_VALUE);
        
        //***** 数字与字符串 **************
        //方法1使用String类的静态方法valueOf 
        String str = String.valueOf(i);    
        //方法2先把基本类型装箱为对象，然后调用对象的toString
        Integer it3 = i;
        String str2 = it3.toString();
        //字符串转数字,调用Integer的静态方法parseInt。
        int i4= Integer.parseInt(str2);
	}

}
