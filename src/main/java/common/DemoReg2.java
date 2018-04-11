package common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DemoReg2 {
	
	//(第xxxxx届，中间的x最多出现5个) 或者 (2018年,数字至少出现2位)
	private static final String REGEX = "(第(.{0,5})届)|(\\d{2,}年)|(年会)|(会议)|(交流会)|(研讨会)|(\\d{1,}th)|(3rd)|(2nd)|(1st)";

    private static final String INPUT = "第二十八届中国地球物理学年会, 17年中国地球物理学年会， 1st conference...,  2nd conference..., 3rd conference..., 31th conference...";
 
    public static void main( String args[] ){
    	//replace();
    	String org = " abc & cdf, abc &cdf, abc& cfd, abc&cdf,&abc,";
    	org = org.replaceAll(" *\\& *", " and ");
    	System.out.println(org);
   }
    
   public static void find(){
	   Matcher m = Pattern.compile(REGEX).matcher(INPUT); // 获取 matcher 对象
     while(m.find()) {
       System.out.println("start(): "+m.start());
       System.out.println("end(): "+m.end());
    }
   }
   
   public static void replace(){
	   Matcher m = Pattern.compile(REGEX).matcher(INPUT); // 获取 matcher 对象
	   System.out.println(m.replaceAll(""));
	   
   }
}
