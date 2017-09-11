package org.net.plat4j.sr.core.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import org.net.plat4j.sr.core.base.BaseException;
import org.net.plat4j.sr.core.utils.IBaseBeanUtils;

import net.plat4j.core.spring.BeanFactory;

@SuppressWarnings({"rawtypes", "unchecked" })
public class JsonUtil {
	private static JsonUtil jsonUtil;
	
	private JsonUtil(){
		
	}
	
	public static JsonUtil getInstance(){
		if(jsonUtil == null){
			jsonUtil = new JsonUtil();
		}
		return jsonUtil;
	}
	
	
	public String map2json(Map map){
		if(map == null){
			return "";
		}
		JSONObject jsonObj = new JSONObject();
		Iterator iter = map.keySet().iterator();
		while(iter.hasNext()){
			Object key = iter.next();
			jsonObj.put(key, map.get(key));
		}
		return jsonObj.toString();
	}
	
	public Map json2Map(String jsonStr,String jsonKey){
		JSONObject jsonObj = JSONObject.fromObject(jsonStr);
		if (JSONUtils.isNull(jsonObj)) {
		      throw new BaseException("Can NOT covert json String:" + jsonStr);
		}
		String json = jsonObj.getString(jsonKey);
		JSONObject jsondata = JSONObject.fromObject(json);
		Map map = new HashMap();
		Iterator iter = jsondata.keys();
		while(iter.hasNext()){
			String key = (String)iter.next();
			map.put(key, jsondata.get(key));
		}
		return map;
	}
	
	public Map json2Map(String jsonStr){
		JSONObject jsonObj = JSONObject.fromObject(jsonStr);
		if (JSONUtils.isNull(jsonObj)) {
		      throw new BaseException("Can NOT covert json String:");
		}
 
		JSONObject jsondata = JSONObject.fromObject(jsonStr);
		Map map = new HashMap();
		Iterator iter = jsondata.keys();
		while(iter.hasNext()){
			String key = (String)iter.next();
			map.put(key, jsondata.get(key));
		}
		return map;
	}
	
	public Object json2Object(String jsonStr,Object obj){
		if(obj==null){
			return null;
		}
		try {
			Map map = JsonUtil.getInstance().json2Map(jsonStr);
			Iterator it = map.keySet().iterator();
			while(it.hasNext()){
				String key = (String)it.next();
				String value = (String)map.get(key);
				IBaseBeanUtils baseBeanUtils = BeanFactory.getBean("IBaseBeanUtils"); 
				baseBeanUtils.setField(obj, key, value);
			}
			return obj;		
		} catch (Exception e) {
			throw new BaseException(e);
		}
	}
	
    public static void main(String[] args){
     String t1 = "{\"cn\":\"1111\",\"en\":\"aaaa\"}";
    System.out.println(JsonUtil.getInstance().json2Map(t1));
    	
    	
      String a="{\"ddVo\":{\"ddId\":null,\"billtypeCode\":\"001\",\"cmemberCode\":\"0001\",\"billtypeTable\":\"ResIn\",\"ddColumn\":\"a\",\"ddName\":\"test\",\"ddValue\":\"1\"}}";
      Map map = JsonUtil.getInstance().json2Map(a, "ddVo");
      String maostr = JsonUtil.getInstance().map2json(map);
      System.out.println(maostr);
      System.out.println(map.toString());
//      Map<String,Object> map= new HashMap<String,Object>();
//      map.put("name", "1");
//      a=buildNormalBinder().toJson(map);
//      //map=buildNonNullBinder().getJsonToObject(a, Map.class);
//      System.out.println(a);
//      Test bean = new Test();
//      Test1 bean2 = new Test1();
//      bean2.setName1("1-1");
//      bean.setName("23");
//      List<Test1> test1list=new ArrayList<Test1>();
//      test1list.add(bean2);
//      bean.setTest1list(test1list);
//      Test bean1 = new Test();
//      bean1.setName("1");
//      List<Test> list = new ArrayList<Test>();
//      list.add(bean);
//      list.add(bean1);
//      String json="{\"name\":\"23\",\"date\":null,\"test1list\":[{\"name1\":\"1-1\",\"date1\":null}]}";
        //String s=null;
        //s=buildNormalBinder().toJson(bean);
//      bean=buildNormalBinder().getJsonToObject(json, bean.getClass());
//      System.out.println(bean.getTest1list().get(0).getName1());
            //list=buildNormalBinder().getMapper().readValue(json,TypeFactory.fromTypeReference(new TypeReference<List<Test>>(){}));
        //list=(List<Test>) buildNormalBinder().getJsonToList(json,new TypeReference<List<Test>>(){});
//      for (Test test : list) {
//          System.out.println(test.getName());
//          if(null!=test.getTest1()){
//          System.out.println(test.getTest1().getName1());
//          }
//      }
//      System.out.println(list);
        //bean=buildNormalBinder().getJsonToObject(json, bean.getClass());
        //System.out.println(bean.getName());
//      Map<String,Object> map =new HashMap<String,Object>();
        //map.put("name", "1");
        //map.put("value", 1);
        //map.put("test", bean);
        //s=buildNormalBinder().toJson(map);
        //System.out.println(s);
//      json="{\"name\":{\"name\":\"1\",\"date\":2},\"value\":1}";
//      map=buildNormalBinder().getJsonToObject(json, Map.class);
//      for(String key:map.keySet()){
//          System.out.println(map.get(key).toString());
//      System.out.println(map.get(key).getClass());
//      }
//      List<String[]> list = new ArrayList<String[]>();
//      for(int i = 0; i <10; i++) {
//          String[] strs = {i + "", i + 10 + ""};
//          list.add(strs);
//      }
//      
//      System.out.println(buildNormalBinder().jsonObject(list));
//      
//      Map<String,String> map = new HashMap<String, String>();
//      for(int i = 0; i <10; i++) {
//          map.put(i + "", i + 10 + "");
//        }
//      
//      System.out.println(buildNormalBinder().getMapToJson(map));
    }
}
