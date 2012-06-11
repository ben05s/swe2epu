package at.epu.DataAccessLayer.DataObjects;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class DataObject {
	int id;
	
	public enum DataObjectState {
		DataObjectStateNew,
		DataObjectStateDeleted,
		DataObjectStateModified,
		DataObjectStateSynchronized;
	}
	
	DataObjectState state;
	
	public int getIndexForFieldName(String fieldName) {
		/** Get field index for fieldName */
		int fieldIndex = 0;
		for(String name : getFieldNames()) {
			if(name.equals(fieldName)) {
				break;
			}
			fieldIndex++;
		}
		
		return fieldIndex;
	}
	
	public boolean isForeignKeyField(int index) {
		ArrayList<String> fieldNames = getFieldNames();
		
		if(fieldNames.get(index).endsWith("_id")) {
			return true;
		}
		
		return false;
	}
	
	ArrayList<Field> getObjectFields() {
		ArrayList<Field> fieldList = null;
		Field[] fields = this.getClass().getDeclaredFields();
		
		fieldList = new ArrayList<Field>();
		
		try {
			fieldList.add(this.getClass().getSuperclass().getDeclaredField("id"));
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		
		fieldList.addAll(Arrays.asList(fields));
		
		return fieldList;
	}
	
	public ArrayList<String> getFieldNames() {
		ArrayList<Field> fieldList = getObjectFields();
		ArrayList<String> fieldNames = new ArrayList<String>();
		
		for(Field field : fieldList) {
			fieldNames.add(field.getName());
		}
		
		return fieldNames;
	}
	
	public ArrayList<Object> getFieldValues() {
		ArrayList<Object> retVal = new ArrayList<Object>();
		ArrayList<Field> fieldList = getObjectFields();
		
		for(int j = 0; j < fieldList.size(); j++) {
			Field field = fieldList.get(j);
			
			String getter = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
			Method getterMethod = null;
			try {
				getterMethod = this.getClass().getMethod(getter, (Class<?>[])null);
			} catch (SecurityException e1) {
				e1.printStackTrace();
			} catch (NoSuchMethodException e1) {
				e1.printStackTrace();
			}
			
			try {
				retVal.add( getterMethod.invoke(this, (Object[])null) );
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		return retVal;
	}
		
	public void setState(DataObjectState newState) {
		state = newState;
	}
	
	public DataObjectState getState() {
		return state;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
 	}
}
