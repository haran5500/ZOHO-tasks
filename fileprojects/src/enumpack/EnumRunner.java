package enumpack;
import inout.Reader;

public class EnumRunner {

	public static void main(String[] args) {
		
		
		Reader read=new Reader();
		System.out.println("Color\tColor Code");
		
		//String color=read.getString("Enter the color name to get the code:");
		read.print("the code for color VIOLET is "+RainbowColors.VIOLET.getCode());
		
		RainbowColors[] colors=RainbowColors.values();
		
		read.print("Colors\tcolor code:");
		for(int iter=0;iter<colors.length;iter++)
		{
			read.print(colors[iter]+"\t"+colors[iter].getCode());
		}
	}
}

