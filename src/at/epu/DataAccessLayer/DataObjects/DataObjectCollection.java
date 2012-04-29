package at.epu.DataAccessLayer.DataObjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import at.epu.DataAccessLayer.DataObjects.DataObject.DataObjectState;

public class DataObjectCollection extends ArrayList<DataObject> {
	private static final long serialVersionUID = 7605598554234265898L;

	public Object[][] toDataArray() {
		if(this.size() <= 0) {
			return new Object[0][0];
		}
		
		int rows = this.size();
		int cols = this.get(0).getClass().getDeclaredFields().length + 1;
		
		Object[][] retVal = new Object[rows][cols];
		
		for(int i = 0; i < this.size(); i++) {
			DataObject obj = this.get(i);
			
			Field[] fields = obj.getClass().getDeclaredFields();
			ArrayList<Field> fieldList = new ArrayList<Field>();
			
			Field idField = null;
			
			try {
				idField = obj.getClass().getSuperclass().getDeclaredField("id");
			} catch (SecurityException e2) {
				e2.printStackTrace();
			} catch (NoSuchFieldException e2) {
				e2.printStackTrace();
			}
			
			fieldList.add(idField);
			fieldList.addAll(Arrays.asList(fields));
			
			for(int j = 0; j < fieldList.size(); j++) {
				Field field = fieldList.get(j);
				
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
		}
		
		return retVal;
	}
	
	public DataObjectCollection whereDataObjectState(DataObjectState state) {
		DataObjectCollection retVal = new DataObjectCollection();
		
		for(DataObject obj : this) {
			if( obj.getState() == state ) {
				retVal.add(obj);
			}
		}
		
		return retVal;	
	}
}
