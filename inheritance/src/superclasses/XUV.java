package superclasses;

//import inout.Reader;

public class XUV extends Car{
	private int seats;
	private int airbags;

	private String model;
	private String color;
	
	private static String inpStr="From XUV";
	
	public XUV()
	{
		super(inpStr);
	}
	
	public void setSeat(int count)
	{
		seats=count;
	}

	public int getSeat()
	{
		return seats;
	}

	public void setAirbag(int count)
	{
		airbags=count;
	}

	public int getAirbag()
	{
		return airbags;
	}

	public void setModel(String modelInfo)
	{
		model=modelInfo;
	}

	public String getModel()
	{
		return model;
	}

	public void setColor(String colorInfo)
	{
		color=colorInfo;
	}

	public String getColor()
	{
		return color;
	}

	}

