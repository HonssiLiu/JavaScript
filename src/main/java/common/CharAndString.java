package common;

public class CharAndString {
	
	public static void main(String[] args) {
		test();
		
		//String sentence = "peter piper picked a peck of pickled peppers";
		//System.out.println(sentenceFirstCap(sentence));
		
   }
	
	public static void test(){
		//char对应的封装类是Character 
        char c1 = 'a';
        Character c = c1; //自动装箱
        c1 = c;//自动拆箱
        
        //Character常见方法
        System.out.println(Character.isLetter('a'));//判断是否为字母
        System.out.println(Character.isDigit('a')); //判断是否为数字
        System.out.println(Character.isWhitespace(' ')); //是否是空白
        System.out.println(Character.isUpperCase('a')); //是否是大写
        System.out.println(Character.isLowerCase('a')); //是否是小写
         
        System.out.println(Character.toUpperCase('a')); //转换为大写
        System.out.println(Character.toLowerCase('A')); //转换为小写
 
        //字符转换为字符串
        String a2 = Character.toString('a'); 
  
        //通过字符数组创建一个字符串对象
        char[] cs1 = new char[]{'崔','斯','特'};
        String hero = new String(cs1);
        
        //字符串长度
        System.out.println(hero.length());
        String unknowHero = "";    
        //可以有长度为0的字符串,既空字符串
        System.out.println(unknowHero.length());
        
        //获取指定位置的字符
        char c11 = hero.charAt(1);     
        System.out.println(c11);
        
        //字符串转换为字符数组
        String str = "abc123";
        char[] cs = str.toCharArray(); 
        
        //截取子字符串
        String sentence = "盖伦在进行了连续8次击杀后,得了超神的称号";
        String subString1 = sentence.substring(3); //截取从第3个开始的字符串 （基0）    
        System.out.println(subString1);
        String subString2 = sentence.substring(3,5); //截取从第3个开始的字符串 （基0）,到5-1的位置的字符串 ,左闭右开
        System.out.println(subString2);
        
        //根据分隔符进行分隔
        String subSentences[] = sentence.split(",");
        for (String sub : subSentences) {
            System.out.println(sub);
        }
        
        //去掉首尾空格
        String sentence1 = "    盖伦在进行了连续8次击杀后,得了超神的称号     ";
        System.out.println(sentence1);
        System.out.println(sentence1.trim());
        
        //全部变成大、小写 
        String sentence11 = "GaRen";
        System.out.println(sentence11.toLowerCase());
        System.out.println(sentence11.toUpperCase());
        
        //判断字符或者子字符串出现的位置、是否包含子字符串
        String sentence111 = "盖伦,在进行了连续8次击杀后,获得了超神 的称号";
        System.out.println(sentence111.indexOf('8')); //字符第一次出现的位置
        System.out.println(sentence111.indexOf("超神")); //字符串第一次出现的位置
        System.out.println(sentence111.lastIndexOf("了")); //字符串最后出现的位置     
        System.out.println(sentence111.indexOf(',',5)); //从位置5开始，出现的第一次,的位置
        System.out.println(sentence111.contains("击杀")); //是否包含字符串"击杀"
        
        //替换
        String temp = sentence111.replaceAll("击杀", "被击杀"); //替换所有的
        temp = temp.replaceAll("超神", "超鬼");    
        System.out.println(temp);       
        temp = sentence111.replaceFirst(",","");//只替换第一个       
        System.out.println(temp);
        
        //字符串比较
        String str1 = "the light";         
        String str3 = str1.toUpperCase();
        System.out.println(str1.equals(str3));//完全一样返回true
        System.out.println(str1.equalsIgnoreCase(str3));//忽略大小写的比较，返回true
        
        //是否以子字符串开始或者结束
        str1 = "the light";
        String start = "the";
        String end = "Ight";
        System.out.println(str1.startsWith(start));//以...开始
        System.out.println(str1.endsWith(end));//以...结束
        
        /**StringBuffer是可变长的字符串**/
        //append追加  delete 删除   insert 插入  reverse 反转
        str1 = "let there ";
        StringBuffer sb = new StringBuffer(str1); //根据str1创建一个StringBuffer对象
        sb.append("be light"); //在最后追加
        System.out.println(sb);
        sb.delete(4, 10);//删除4-10之间的字符
        System.out.println(sb);
        sb.insert(4, "there ");//在4这个位置插入 there
        System.out.println(sb);
        sb.reverse(); //反转
        System.out.println(sb);
	}
	
	//每个单词的首字母都转换为大写
	public static String sentenceFirstCap(String sentence){		
        String words[] = sentence.split(" ");
        String capSentence = "";
        for (String word : words) {
        	char[] cs = word.toCharArray();
        	cs[0] = Character.toUpperCase(cs[0]);
        	String capword = new String(cs);
        	capSentence = capSentence+" "+capword;
        }
        return capSentence.trim();
	}
	

}
