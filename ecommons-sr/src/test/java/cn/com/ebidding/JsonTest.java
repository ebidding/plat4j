/*
	Copyright (C) 2017 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: WGY
	Version: 1.0
	Created Time: 2017年6月29日 下午4:13:35
	
	Revision History:
	Version          Date               Author			Comments
	1.0         	2017年6月29日下午4:13:35		WGY			Create file
=========================================================================
*/

package cn.com.ebidding;

import cn.com.ebidding.Model.PersonModel;
import net.sf.json.JSONObject;

/**
 * @author WGY
 *
 */
public class JsonTest {
	public static void main(String[] args) {
		PersonModel model = new PersonModel();
//		model.setAge(1);
//		model.setName("222");
		JSONObject json = JSONObject.fromObject(model );
		System.out.println(json.toString());
	}
}
