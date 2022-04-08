package enumpack;
import inout.Reader;

public class EnumRunner {

	public static void main(String[] args) {
		
		System.out.println("Color\tColor Code");
		
		//String color=Reader.getString("Enter the color name to get the code:");
		Reader.print("the code for color VIOLET is "+RainbowColors.VIOLET.getCode());
		
		RainbowColors[] colors=RainbowColors.values();
		
		Reader.print("Colors\tcolor code:");
		for(int iter=0;iter<colors.length;iter++)
		{
			Reader.print(colors[iter]+"\t"+colors[iter].getCode());
		}
	}
}

