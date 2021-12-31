package runnerclasses;
import superclasses.*;

import java.text.MessageFormat;

import inout.Reader;

public class Runable {

	
	private void getSwiftProperties()
	{
		Swift swiftObj=new Swift();
		
		Reader.print("Enter the details of the Swift car!");
		String model=Reader.getString("Enter the Model type:") ;
		String color=Reader.getString("Enter the color:");
		
		int seats=Reader.getInt("Enter no of seats:");
		int airbags=Reader.getInt("Enter no of airbags:");
		
		swiftObj.setModel(model);
		swiftObj.setColor(color);
		swiftObj.setSeat(seats);
		swiftObj.setAirbag(airbags);
		
		model=swiftObj.getModel();
		color=swiftObj.getColor();
		seats=swiftObj.getSeat();
		airbags=swiftObj.getAirbag();
		
		String message=MessageFormat.format("The details of the Swift car:-\nModel:{0}\nColor:{1}\nSeats:{2}\nAirbags:{3}", model,color,seats,airbags);
		
		Reader.print(message);
	}
	
	private void getScrossProperties()
	{
		SCross sigma=new SCross();
		
		Reader.print("Enter the details of the SCross car!");
		
		String model=Reader.getString("Enter the Model type:") ;
		String color=Reader.getString("Enter the color:");
		String engineNo=Reader.getString("Enter the Engine Number:");
		String type=Reader.getString("Enter the type of class:");
		
		int seats=Reader.getInt("Enter no of seats:");
		int airbags=Reader.getInt("Enter no of airbags:");
		int year=Reader.getInt("Enter the Year of Make:");
		
		sigma.setModel(model);
		sigma.setColor(color);
		sigma.setEngineNumber(engineNo);
		sigma.setVehicleType(type);
		
		sigma.setSeat(seats);
		sigma.setAirbag(airbags);
		sigma.setYear(year);
		
		model=sigma.getModel();
		color=sigma.getColor();
		
		engineNo=sigma.getEngineNumber();
		type=sigma.getVehicleType();
		
		seats=sigma.getSeat();
		airbags=sigma.getAirbag();
		year=sigma.getYear();
		
		String message=MessageFormat.format("The details of the SCross car:-\nModel:{0}\nColor:{1}\nEngine Number:{2}\nClass Type:{3}\nSeats:{4}\nAirbags:{5}\nYear of Make:{6}", model,color,engineNo,type,seats,airbags,year);
		
		Reader.print(message);
	}
	
	private void printTypes(Car carObj)
	{
		if(carObj instanceof Swift)
		{
			Reader.print("Model:Swift --- Type:Hatch");
		}
		else if(carObj instanceof SCross)
		{
			Reader.print("Model:SCross --- Type:Sedan");
		}
		else if(carObj instanceof XUV)
		{
			Reader.print("Model:XUV --- Type:SUV");
		}
		
	}


	public static void main(String[] args) {
		
		Runable run=new Runable();
		
		Reader.print("Welcome to Inheritance Programs!");
		Reader.print("Options:\n1. Methods of Swift\n2. All Methods of SCross\n3. Instance Identifying with print\n4. Invoking with class instance\n5. Override maintenance in SCross\n6. Calling default with XUV");
		
		int option=Reader.getInt("Enter your option:");
		
		switch(option)
		{
		case 1:
		{
			run.getSwiftProperties();
			break;
		}
		case 2:
			
		{
			run.getScrossProperties();
			break;
		}
		case 3:
		{
			Swift swiftObj=new Swift();
			run.printTypes(swiftObj);
			
			SCross delta=new SCross();
			run.printTypes(delta);
			
			XUV classic=new XUV();
			run.printTypes(classic);

			break;
		}
		case 4:
		{
			Swift vdi=new Swift();
			run.printTypes(vdi);
			
			Car carObj=new Swift();
			run.printTypes(carObj);
			
			Car zeta=new SCross();
			run.printTypes(zeta);
			
			Car royal=new XUV();
			run.printTypes(royal);
			
			break;
		}
		case 5:
		{
			
			SCross sigma=new SCross();
			sigma.maintenance();
			
			Car zeta=new SCross();
			zeta.maintenance();
			
			Car cab=new Car();
			cab.maintenance();
			
			Swift vxi=new Swift();
			vxi.maintenance();
			break;
		}
		case 6:
		{
			XUV classic=new XUV();
			
//			XUV royal=new XUV("Hello");
			break;
		}
		default:
		{
			Reader.print("No such option!");
			break;
		}
		}
	}

}
