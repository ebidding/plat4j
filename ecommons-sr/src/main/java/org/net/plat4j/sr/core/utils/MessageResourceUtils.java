package org.net.plat4j.sr.core.utils;

import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.servlet.ServletRequest;

/**
 * 资源文件中文本值获取工具类。
 * 
 * @author yujie
 */
public class MessageResourceUtils {
	protected static LogHelper logger = new LogHelper(MessageResourceUtils.class);

	/**
	 * 获取指定资源文件中指定语言的文本值。如果该资源文件不存在或者要获取的文本键值不在该资源文件中，将抛出 
	 *  MissingResourceException 异常。
	 * 
	 * @param request 当前请求的 request ，将从此参数中获取所要求的语言区域信息。
	 * @param bundleName 资源文件名称，应该和 xxx.native 文件的文件名称一致，不带扩展名。
	 * @param key 资源文件中的键值。
	 * @return 获取的资源文件中的指定语言的文本值。
	 * @exception MissingResourceException 指定名称的资源文件不存在或者资源文件中不存在指定的键值时抛出。
	 */
	public static String getMessage(ServletRequest request, String bundleName,
			String key) {
		Locale locale = request.getLocale();
		return getMessage(locale, bundleName, key, null);
	}

	/**
	 * 尝试获取指定资源文件中指定语言的文本值。如果该资源文件不存在或者要获取的文本键值不在该资源文件中，将返回 null
	 * 而不是抛出 MissingResourceException 异常。
	 * 
	 * @param request 当前请求的 request ，将从此参数中获取所要求的语言区域信息。
	 * @param bundleName 资源文件名称，应该和 xxx.native 文件的文件名称一致，不带扩展名。
	 * @param key 资源文件中的键值。
	 * @return 获取的资源文件中的指定语言的文本值，如果指定名称的资源文件不存在或者资源文件中不存在指定的键值，将返回 null。
	 */
	public static String tryGetMessage(ServletRequest request, String bundleName,
			String key) {
		Locale locale = request.getLocale();
		return tryGetMessage(locale, bundleName, key, null);
	}

	/**
	 * 获取指定资源文件中指定语言的文本值。如果该资源文件不存在或者要获取的文本键值不在该资源文件中，将抛出 
	 *  MissingResourceException 异常。
	 * 
	 * @param locale 要获取的文本值的语言。
	 * @param bundleName 资源文件名称，应该和 xxx.native 文件的文件名称一致，不带扩展名。
	 * @param key 资源文件中的键值。
	 * @return 获取的资源文件中的指定语言的文本值。
	 * @exception MissingResourceException 指定名称的资源文件不存在或者资源文件中不存在指定的键值时抛出。
	 */
	public static String getMessage(Locale locale, String bundleName, String key) {
		return getMessage(locale, bundleName, key, null);
	}

	/**
	 * 尝试获取指定资源文件中指定语言的文本值。如果该资源文件不存在或者要获取的文本键值不在该资源文件中，将返回 null
	 * 而不是抛出 MissingResourceException 异常。
	 * 
	 * @param locale 要获取的文本值的语言。
	 * @param bundleName 资源文件名称，应该和 xxx.native 文件的文件名称一致，不带扩展名。
	 * @param key 资源文件中的键值。
	 * @return 获取的资源文件中的指定语言的文本值，如果指定名称的资源文件不存在或者资源文件中不存在指定的键值，将返回 null。
	 */
	public static String tryGetMessage(Locale locale, String bundleName, String key) {
		return tryGetMessage(locale, bundleName, key, null);
	}

	/**
	 * 获取指定资源文件中指定语言的文本值。如果该资源文件不存在或者要获取的文本键值不在该资源文件中，将抛出 
	 *  MissingResourceException 异常。
	 * 
	 * @param request 当前请求的 request ，将从此参数中获取所要求的语言区域信息。
	 * @param bundleName 资源文件名称，应该和 xxx.native 文件的文件名称一致，不带扩展名。
	 * @param key 资源文件中的键值。
	 * @param args 如果资源文件的原始文本值包括需要格式化的参数，此参数传入参数值的数组，如果没有参数可以传 null。
	 * @return 获取的资源文件中的指定语言的文本值。
	 * @exception MissingResourceException 指定名称的资源文件不存在或者资源文件中不存在指定的键值时抛出。
	 */
	public static String getMessage(ServletRequest request, String bundleName,
			String key, String[] args) {
		Locale locale = request.getLocale();
		return getMessage(locale, bundleName, key, args);
	}

	/**
	 * 尝试获取指定资源文件中指定语言的文本值。如果该资源文件不存在或者要获取的文本键值不在该资源文件中，将返回 null
	 * 而不是抛出 MissingResourceException 异常。
	 * 
	 * @param request 当前请求的 request ，将从此参数中获取所要求的语言区域信息。
	 * @param bundleName 资源文件名称，应该和 xxx.native 文件的文件名称一致，不带扩展名。
	 * @param key 资源文件中的键值。
	 * @param args 如果资源文件的原始文本值包括需要格式化的参数，此参数传入参数值的数组，如果没有参数可以传 null。
	 * @return 获取的资源文件中的指定语言的文本值，如果指定名称的资源文件不存在或者资源文件中不存在指定的键值，将返回 null。
	 */
	public static String tryGetMessage(ServletRequest request, String bundleName,
			String key, String[] args) {
		Locale locale = request.getLocale();
		return tryGetMessage(locale, bundleName, key, args);
	}

	/**
	 * 获取指定资源文件中指定语言的文本值。如果该资源文件不存在或者要获取的文本键值不在该资源文件中，将抛出 
	 *  MissingResourceException 异常。
	 * 
	 * @param locale 要获取的文本值的语言。
	 * @param bundleName 资源文件名称，应该和 xxx.native 文件的文件名称一致，不带扩展名。
	 * @param key 资源文件中的键值。
	 * @param args 如果资源文件的原始文本值包括需要格式化的参数，此参数传入参数值的数组，如果没有参数可以传 null。
	 * @return 获取的资源文件中的指定语言的文本值。
	 * @exception MissingResourceException 指定名称的资源文件不存在或者资源文件中不存在指定的键值时抛出。
	 */
	public static String getMessage(Locale locale, String bundleName,
			String key, String[] args) {
		ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale);
		if(bundle == null) {
			throw new MissingResourceException("Can't find bundle for base name "
                    + bundleName + ", locale " + locale, bundleName + "_" + locale, "");
		}
		String pattern = bundle.getString(key);
		String message = MessageFormat.format(pattern, (Object[]) args);
		try {
			if(message.equals(new String(message.getBytes("ISO-8859-1"), "ISO-8859-1")))
			{
			  message=new String(message.getBytes("ISO-8859-1"),"UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			logger.info("UnsupportedEncodingException："+e.getMessage());
		}
		return "null".equals(message) ? null : message;
	}

	/**
	 * 尝试获取指定资源文件中指定语言的文本值。如果该资源文件不存在或者要获取的文本键值不在该资源文件中，将返回 null
	 * 而不是抛出 MissingResourceException 异常。
	 * 
	 * @param locale 要获取的文本值的语言。
	 * @param bundleName 资源文件名称，应该和 xxx.native 文件的文件名称一致，不带扩展名。
	 * @param key 资源文件中的键值。
	 * @param args 如果资源文件的原始文本值包括需要格式化的参数，此参数传入参数值的数组，如果没有参数可以传 null。
	 * @return 获取的资源文件中的指定语言的文本值，如果指定名称的资源文件不存在或者资源文件中不存在指定的键值，将返回 null。
	 */
	public static String tryGetMessage(Locale locale, String bundleName,
			String key, String[] args) {
		ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale);
		if(bundle != null && bundle.containsKey(key)) {
			String pattern = bundle.getString(key);
			String message= MessageFormat.format(pattern, (Object[]) args);
			try {
				if(message.equals(new String(message.getBytes("ISO-8859-1"), "ISO-8859-1")))
				{
				  message=new String(message.getBytes("ISO-8859-1"),"UTF-8");
				}
			} catch (UnsupportedEncodingException e) {
				logger.info("UnsupportedEncodingException："+e.getMessage());
			}
			return message;
		}
		return null;
	}
}
