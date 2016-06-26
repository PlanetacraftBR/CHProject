package me.hub.API.Util;

public class UtilNumber {

	  public static String getNumber(String name)
	  {
		  String anterior = name;
		  try {
		  double b = Double.parseDouble(name);
		  name =  "" + (int) b;
	    if (name.length() == 4)
	    {
	      String shorts = name.substring(0, name.length() - 3);
	      String rename = shorts + "-M";
	      return rename;
	    }
	    if (name.length() == 5)
	    {
	      String shorts = name.substring(0, name.length() - 3);
	      String rename = shorts + "-M";
	      return rename;
	    }
	    if (name.length() == 6)
	    {
	      String shorts = name.substring(0, name.length() - 3);
	      String rename = shorts + "-M";
	      return rename;
	    }
	    if (name.length() == 7)
	    {
	      String shorts = name.substring(0, name.length() - 6);
	      String rename = shorts + "-Mi";
	      return rename;
	    }
	    if (name.length() == 8)
	    {
	      String shorts = name.substring(0, name.length() - 6);
	      String rename = shorts + "-Mi";
	      return rename;
	    }
	    if (name.length() == 9)
	    {
	      String shorts = name.substring(0, name.length() - 6);
	      String rename = shorts + "-Mi";
	      return rename;
	    }
	    if (name.length() >= 10)
	    {
	      String shorts = name.substring(0, name.length() - 7);
	      String rename = shorts + "-Bi";
	      return rename;
	    }
	    if (name.length() >= 13)
	    {
	      String shorts = name.substring(0, name.length() - 7);
	      String rename = shorts + "-Tri";
	      return rename;
	    }
		  }
		  catch (Exception ex)
		  {
			  name = anterior;
			  if (name == null)
				  return "erro";
			    if (name.length() == 4)
			    {
			      String shorts = name.substring(0, name.length() - 3);
			      String rename = shorts + "-M";
			      return rename;
			    }
			    if (name.length() == 5)
			    {
			      String shorts = name.substring(0, name.length() - 3);
			      String rename = shorts + "-M";
			      return rename;
			    }
			    if (name.length() == 6)
			    {
			      String shorts = name.substring(0, name.length() - 3);
			      String rename = shorts + "-M";
			      return rename;
			    }
			    if (name.length() == 7)
			    {
			      String shorts = name.substring(0, name.length() - 6);
			      String rename = shorts + "-Mi";
			      return rename;
			    }
			    if (name.length() == 8)
			    {
			      String shorts = name.substring(0, name.length() - 6);
			      String rename = shorts + "-Mi";
			      return rename;
			    }
			    if (name.length() == 9)
			    {
			      String shorts = name.substring(0, name.length() - 6);
			      String rename = shorts + "-Mi";
			      return rename;
			    }
			    if (name.length() >= 10)
			    {
			      String shorts = name.substring(0, name.length() - 7);
			      String rename = shorts + "-Bi";
			      return rename;
			    }
			    if (name.length() >= 13)
			    {
			      String shorts = name.substring(0, name.length() - 7);
			      String rename = shorts + "-Tri";
			      return rename;
			    }
			    
		  }
	    return name;
	  }
}
