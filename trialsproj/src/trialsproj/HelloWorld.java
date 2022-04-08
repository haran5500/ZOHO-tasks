package trialsproj;

class Solution {
 public boolean isValid(String s) {

     int len=s.length();
     int cou=0;
     String checkCh="";
     
     for(int i=0;i<len;i++)
     {
         char ch = s.charAt(i);
			if (ch == '(' || ch == '{' || ch == '[')
         {
             checkCh+=ch;
             cou++;
         }
         
			else {
			    System.out.println("Str:"+checkCh+" count:"+cou);
				if (checkCh.isEmpty()) 
             {
                 return false;
             }
				
             char peek=checkCh.charAt(cou-1);
				if ((ch == ')' && peek == '(') || (ch == ']' && peek == '[') || (ch == '}' && peek == '{')) 
             {
                 checkCh.replace(peek,' ');
                 System.out.println("--->Str:"+checkCh+" PEEK:"+peek+"----|||__");
                 cou--;
             }
				else
             {
                 return false;
             }
			}
		}
		System.out.println(checkCh);
		return cou==0;
     
 }
}

public class HelloWorld {
 public static void main(String[] args) {
     
     System.out.println("Hello!");
Solution sn=new Solution();

System.out.println(sn.isValid("[{}]"));

 }
}