package reflect.pojo;
import java.lang.reflect.*;
import inout.Reader;


public class PojoReflector {

	public static void main(String[] args) {
		try {
			Class<?> pojoObj=Class.forName("pojopackage.StudentPojo");
			
		//Object student=new pojopackage.StudentPojo();
			Constructor<?> defConstruct=pojoObj.getDeclaredConstructor();
		Object student=defConstruct.newInstance();
		
		Reader read=new Reader();
		String name=read.getString("Enter the name:");
		int age=read.getInt("Enter the age:");
		
		Constructor<?> construct=pojoObj.getDeclaredConstructor(String.class,int.class);
		
		Object student1=construct.newInstance(name, age);
		
		Method nameSetter=student.getClass().getMethod("setName",String.class);
		
		String name2=read.getString("Enter a name:");
		
		nameSetter.invoke(student, name2);
		
		Method ageSetter=student.getClass().getMethod("setAge",int.class);
		
		int age2=read.getInt("Enter the age:");
		
		ageSetter.invoke(student, age2);
		
		read.print("Printing default constructor object:");
		System.out.println(student);
		
		Method nameGetter=student.getClass().getMethod("getName");
		Method ageGetter=student.getClass().getMethod("getAge");
		
		String message=String.format("Using Reflector! Name:%s and Age:%d",nameGetter.invoke(student),ageGetter.invoke(student));
		read.print(message);
		
		read.print("Printing Overloaded constructor object:");
		System.out.println(student1);
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}

}
