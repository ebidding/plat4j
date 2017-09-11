package org.net.plat4j.common.sortUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import org.net.plat4j.sr.core.utils.LogHelper;

public class SortItemByFieldnameUtil {
	
	protected static LogHelper logger = new LogHelper(SortItemByFieldnameUtil.class);
	/**
	 * 对list 进行重新排序
	 * 
	 * @param list 排序列表 
	 * @param clazz 列表对象类型
	 * @param fieldName 排序字段名
	 * @return 
	 */
	public  static <T> List<T> sortModel(List<T> list,Class<T> clazz,String fieldName,String splitFlag) {
		Field field = checkFields(clazz,fieldName);
		if(field==null)
			return list;
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.size() - i - 1; j++) {
				T itemModel = list.get(j);
				T itemModel2 = list.get(j + 1);
				int compare = 0;
				try {
					compare = compare(itemModel, itemModel2,clazz,field,splitFlag);
				} catch (Exception e) {
					logger.error(e);
				}
				if (compare > 0) {
					// 当第一个大于第二个的时候 交换第一个和第二个的位置 目的小的在前
					list.set(j + 1, itemModel);
					list.set(j, itemModel2);
				}

			}
		}
		return list;
	}
	/**
	 * 确认clazz中是否含有string类型的名为fieldName的属性
	 * @param clazz
	 * @param fieldName
	 * @return
	 * yes
	 */
	private static <T> Field checkFields(Class<T> clazz,String fieldName){
        Class localClazz = clazz;
        for(;localClazz!=Object.class;localClazz=localClazz.getSuperclass()){
              try {
                    Field field = localClazz.getDeclaredField(fieldName);
                    if(field.getName().equals(fieldName)&&String.class.equals(field.getType()))
                          return field;
              } catch (NoSuchFieldException | SecurityException e) {
              }
        }
        return null;
  }

	/**
	 * 获取名为fieldName的属性的get方法
	 * @param fieldName
	 * @return
	 * yes
	 */
	private static String getMethodName(String fildeName) throws Exception{  
        byte[] items = fildeName.getBytes();  
        items[0] = (byte) ((char) items[0] - 'a' + 'A');  
        return "get" +new String(items);  
    }  
	/**
	 * <li>根据seq 比较第一个对象和第二个对象的</li> o1>o2返回 >0 o1=o2返回 =0 o1<o2返回 <0
	 * 
	 * @param o1
	 * @param o2
	 * @param clazz  
	 * @param field
	 * @return
	 * yes
	 * @throws Exception 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	private static <T> int compare(T o1, T o2,Class<T> clazz,Field field,String splitFlag) throws NoSuchMethodException, SecurityException, Exception {

		String[] i1_Split = null;// 第一个"."的集合
		String[] i2_Split = null;// 第二个"."的集合
		String i1Seq;
		String i2Seq;
		Method m = (Method) clazz.getMethod(getMethodName(field.getName()));  
		String field_o1 = (String) m.invoke(o1);
		String field_o2 = (String) m.invoke(o2);
		int flag = 0;
		if (field_o1.indexOf(splitFlag) != -1) {
			// 当第一个对象存在 “.”的形式 进行分割
			char tag = splitFlag.charAt(0);
			//i1_Split = field_o1.split("\\.");
			i1_Split = field_o1.split("\\"+splitFlag);
			if (field_o2.indexOf(splitFlag) != -1) {
				// 当第二个对象也存在 “.”的形式 进行分割
				i2_Split = field_o2.split("\\"+splitFlag);
				// 获取最小的length
				int i1Length = i1_Split.length;
				int i2Length = i2_Split.length;
				int tempFlag=0;
				int length = 0;
				if (i1Length > i2Length) {
					//当第一个的长度大于第二个的长度的时候
					//诸如第一个是 1.1.1 和 第二个是1.1的时候 
					//那么 应当交换位置
					length = i2Length;
					tempFlag=1;
				} else {
					//当第一个的长度小于第二个的长度的时候
					//诸如第一个是 1.1和 第二个是1.1.1的时候 
					//那么 应当不交换位置
					length = i1Length;
					tempFlag=-1;
				}
				Boolean temp=null;
				for (int i = 0; i < length; i++) {
					i1Seq = i1_Split[i];
					i2Seq = i2_Split[i];
					Integer i1S = Integer.valueOf(i1Seq);
					Integer i2S = Integer.valueOf(i2Seq);
					flag = i1S.compareTo(i2S);
					if (flag != 0) {
						return flag;
					}else{
						temp=true;
					}
				}
				if(temp){
					flag=tempFlag;
				}
				
			} else {
				// 当第二个对象不存在“.”的形式时
				i2Seq = field_o2;
				for (int i = 0; i < i1_Split.length; i++) {
					// 当第二个对象为2 而第一个对象是形如“2.1” 或者“2.1.1”的格式时
					// 应当交换位置 则 应该返回>0
					if (i2Seq.equals(i1_Split[0]))
						return 1;
					i1Seq = i1_Split[i];
					Integer i1S = Integer.valueOf(i1Seq);
					Integer i2S = Integer.valueOf(i2Seq);
					flag = i1S.compareTo(i2S);
					if (flag != 0)
						return flag;
				}
			}

		} else {
			i1Seq = field_o1;
			// 第一个对象不存在“.”的形式
			if (field_o2.indexOf(splitFlag) != -1) {
				i2_Split = field_o2.split("\\"+splitFlag);
				for (int i = 0; i < i2_Split.length; i++) {
					// 当第一个对象2 而第二个对象是形如“2.1” 或者“2.1.1”的格式时
					// 不交换位置 则返回-1
					if (i1Seq.equals(i2_Split[0]))
						return -1;
					i2Seq = i2_Split[i];
					Integer i1S = Integer.valueOf(i1Seq);
					Integer i2S = Integer.valueOf(i2Seq);
					flag = i1S.compareTo(i2S);
					if (flag != 0)
						return flag;
				}

			} else {
				i2Seq = field_o2;
				Integer i1S = Integer.valueOf(i1Seq);
				Integer i2S = Integer.valueOf(i2Seq);
				flag = i1S.compareTo(i2S);

			}
		}
		return flag;
	}

}
