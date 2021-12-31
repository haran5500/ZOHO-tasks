package patterns;

public enum SingletonClass {

	INSTANCE;
	 
    public String str;
 
    private SingletonClass()
    {
        str = "Hello I am a Singleton value using ENUM!";
    }
}
