package com.semico.support.restclient;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by luis on 2/26/2016.
 * Purpose : testing json mapper, test every class data here before using it in real process...
 */
public class JsonMapperTest {

    IJsonMapper jsonMapper = new GsonMapper();

    @Test
    public void testUserToJson() throws IOException {
        User user = new User();
        user.active = true;
        user.role = 9;
        user.fullName = "Luis Ginanjar";
        user.RowKey = "994348";
        user.id = "0001";
        String result = jsonMapper.write(user);
        String expectedUser = "{\"fullName\":\"Luis Ginanjar\",\"RowKey\":\"994348\",\"active\":true,\"role\":9,\"id\":\"0001\"}";
        assertEquals(expectedUser, result);
    }

    @Test
    public void testListUserToJson() throws IOException {
        User user1 = new User();
        user1.active = true;
        user1.role = 9;
        user1.fullName = "Luis Ginanjar";
        user1.RowKey = "994348";
        user1.id = "0001";

        User user2 = new User();
        user2.active = true;
        user2.role = 9;
        user2.fullName = "Jati Kusworo";
        user2.RowKey = "112345";
        user2.id = "0001";

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        String result = jsonMapper.writeList(userList);
        String expectation = "[{\"fullName\":\"Luis Ginanjar\",\"RowKey\":\"994348\",\"active\":true,\"role\":9,\"id\":\"0001\"},{\"fullName\":\"Jati Kusworo\",\"RowKey\":\"112345\",\"active\":true,\"role\":9,\"id\":\"0001\"}]";
        assertEquals(expectation, result);
    }

    @Test
    public void testJsonToUser() throws IOException {
        String JsonString = "{\"RowKey\":\"994348\"," +
                "\"active\":true," +
                "\"fullName\":\"Luis Ginanjar\"," +
                "\"role\":9," +
                "\"id\":\"0001\"}";
        User userExpectation = new User();
        userExpectation.active = true;
        userExpectation.role = 9;
        userExpectation.fullName = "Luis Ginanjar";
        userExpectation.RowKey = "994348";
        userExpectation.id = "0001";

        User userActual = jsonMapper.read(JsonString, User.class);
        assertEquals(userExpectation.fullName, userActual.fullName);
        assertEquals(userExpectation.RowKey, userActual.RowKey);
        assertEquals(userExpectation.id, userActual.id);
        assertEquals(userExpectation.active, userActual.active);
        assertEquals(userExpectation.role, userActual.role);
    }

    @Test
    public void testJsonToListUser() throws IOException {
        String jsonString = "[{\"RowKey\":\"994348\",\"fullName\":\"Luis Ginanjar\",\"role\":9,\"active\":true,\"id\":\"0001\"}," +
                "{\"RowKey\":\"112345\",\"fullName\":\"Jati Kusworo\",\"role\":9,\"active\":true,\"id\":\"0001\"}]";
        List<User> userExpectation = new ArrayList<>();

        User user1 = new User();
        user1.active = true;
        user1.role = 9;
        user1.fullName = "Luis Ginanjar";
        user1.RowKey = "994348";
        user1.id = "0001";
        userExpectation.add(user1);

        User user2 = new User();
        user2.active = true;
        user2.role = 9;
        user2.fullName = "Jati Kusworo";
        user2.RowKey = "112345";
        user2.id = "0001";
        userExpectation.add(user2);

        List<User> userActual = jsonMapper.readList(jsonString, User[].class);
        assertEquals(userActual.toString(), userExpectation.toString());
    }
}