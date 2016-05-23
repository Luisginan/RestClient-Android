package com.semico.support.restclient;

import junit.framework.Assert;

import org.junit.Test;

import java.lang.reflect.Field;

/**
 * Created by Luis Ginanjar on 30/04/2016.
 * Purpose :
 */
public class ReflectionTest {
    @Test
    public void testReflection() throws IllegalAccessException, InstantiationException, NoSuchFieldException {
        Class<User> theClass = User.class;
        User user = theClass.newInstance();
        Field field = user.getClass().getField("fullName");
        field.set(user, "Luis Ginanjar");
        Assert.assertEquals(user.fullName, "Luis Ginanjar");
    }
}
