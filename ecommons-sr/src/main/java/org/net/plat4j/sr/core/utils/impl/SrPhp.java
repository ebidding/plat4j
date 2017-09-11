package org.net.plat4j.sr.core.utils.impl;

/**
 * PHP mock
 * @author chenshiming
 *
 */
public class SrPhp {
	/**
	 * Php implode
	 * @param sep
	 * @param args
	 * @return
	 */
/*	public static String implode(String sep , String[] args){
		if(args==null){
			return null;
		}
		if(args.length==1){
			return args[0];
		}
		StringBuffer sb = new StringBuffer();
		for(int i=0,j=args.length-1;i<j;i++){
			sb.append(args[i]).append(sep);
		}
		sb.append(args[args.length-1]);
		return sb.toString();
	}*/
	public static String implode(String sep , Object[] args){
		if(args==null){
			return null;
		}
		if(args.length==0){
			return "";
		}		
		if(args.length==1){
			return (String)args[0];
		}
		StringBuffer sb = new StringBuffer();
		for(int i=0,j=args.length-1;i<j;i++){
			sb.append(args[i]).append(sep);
		}
		sb.append(args[args.length-1]);
		return sb.toString();
	}	
	
	public static String ucfirst(String str){
		if(str==null||str.length()==0){
			return str;
		}
		return str.substring(0,1).toUpperCase()+str.substring(1);
	}
	
	public static boolean is_array(Object key,Object[] objs){
		if(objs==null||key==null){
			return false;
		}
		for(int i=0,j=objs.length;i<j;i++){
			if(objs[i].equals(key)){		
				return true;
			}
		}
		return false;
	}
}
