package com.rest4j.impl.polymorphic;

import com.rest4j.ApiException;
import com.rest4j.ObjectFactoryChain;
import com.rest4j.json.JSONException;
import com.rest4j.json.JSONObject;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author Joseph Kapizza <joseph@rest4j.com>
 */
public class ObjectFactory implements com.rest4j.ObjectFactory {
	@Nullable
	@Override
	public Object createInstance(@Nonnull String modelName, @Nonnull Class clz, @Nonnull JSONObject object, @Nonnull ObjectFactoryChain next) throws JSONException, ApiException {
		if (modelName.equals("Pet")) {
			if (object.getString("type").equals("bird")) return new Bird();
			return new Cat();
		}
		return next.createInstance(modelName, clz, object);
	}

}
