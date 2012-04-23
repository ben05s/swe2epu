package at.epu.DataAccessLayer.DataObjects;

import java.util.ArrayList;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DataObjectCollection extends ArrayList<DataObject> {
	private static final long serialVersionUID = 7605598554234265898L;

	public Object[][] toDataArray() {
		if(this.size() <= 0) {
			return new Object[0][0];
		}
		
		int rows = this.size();
		int cols = this.get(0).getClass().getDeclaredFields().length;
		
		Object[][] retVal = new Object[rows][cols];
		
		for(int i = 0; i < this.size(); i++) {
			DataObject obj = this.get(i);
			
			for(int j = 0; j < obj.getClass().getDeclaredFields().length; j++) {
				Field field = obj.getClass().getDeclaredFields()[j];
				
				String getter = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
				Method getterMethod = null;
				try {
					getterMethod = obj.getClass().getMethod(getter, (Class<?>[])null);
				} catch (SecurityException e1) {
					e1.printStackTrace();
				} catch (NoSuchMethodException e1) {
					e1.printStackTrace();
				}
				
				try {
					retVal[i][j] = getterMethod.invoke(obj, (Object[])null);
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			i++;
		}
		
		return retVal;
	}
}
