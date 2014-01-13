package Courier.CourierService.Helpers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class ArraySerializer {
public static byte[] write(Object o)
{
	ByteArrayOutputStream bos = new ByteArrayOutputStream();
	byte[] bytes;
	ObjectOutput out = null;
	try {
	  out = new ObjectOutputStream(bos);   
	  out.writeObject(o);
	  bytes = bos.toByteArray();
	  return bytes;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
	  try {
	    if (out != null) {
	      out.close();
	    }
	  } catch (IOException ex) {
	    // ignore close exception
	  }
	  try {
	    bos.close();
	  } catch (IOException ex) {
	    // ignore close exception
	  }
	}
	return null;
}

public static Object read(byte[] array)
{
	ByteArrayInputStream bis = new ByteArrayInputStream(array);
	ObjectInput in = null;
	try {
	  in = new ObjectInputStream(bis);
	  Object o = in.readObject();
	  return o;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
	  try {
	    bis.close();
	  } catch (IOException ex) {
	    // ignore close exception
	  }
	  try {
	    if (in != null) {
	      in.close();
	    }
	  } catch (IOException ex) {
	    // ignore close exception
	  }
	}
	return null;
}
}

