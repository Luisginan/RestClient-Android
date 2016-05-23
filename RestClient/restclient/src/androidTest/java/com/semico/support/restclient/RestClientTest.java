package com.semico.support.restclient;


import junit.framework.TestCase;

/**
 * Created by luis on 2/26/2016.
 * Purpose : Testing RestClient, testing basic function Restful feature
 */
public class RestClientTest extends TestCase {

    //email : luisginan@gmail.com , password : 123456 , use Postman to generate authorization code
    private final String authorization = "bHVpc2dpbmFuQGdtYWlsLmNvbToxMjM0NTY=";
    public void testGet(){
        try{
            String passValue = "http://homecamportal.cloudapp.net/Api/User/Signin?email=luisginan@gmail.com&password=123456";
            IRestClient IRestClient = new RestClient();
            User user = IRestClient.get(User.class, passValue);
            assertEquals("Luis Ginanjar", user.fullName);
        }catch (Exception ex){
            ex.printStackTrace();
        }


    }

    public void testGetList() {
        try {
            String passValue = "http://homecamportal.cloudapp.net/Api/Camera/GetCameras";
            IRestClient restClient = new RestClient();
            restClient.setAuthorization(authorization);
            restClient.getList(User[].class, passValue);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void testPost() throws Exception {
        String url = "http://homecamportal.cloudapp.net/api/Camera/InsertDeviceID";
        DeviceId deviceId = new DeviceId();
        deviceId.UID = String.valueOf(Math.random());
        deviceId.Rowkey = "1234567890199";

        IRestClient IRestClient = new RestClient();
        IRestClient.setAuthorization(authorization);
        IRestClient.post(url, deviceId);
    }

    public void testPut() throws Exception {
        String url = "http://homecamportal.cloudapp.net/api/User/UpdateUniqueAppCode?code=123456";
        IRestClient IRestClient = new RestClient();
        IRestClient.setAuthorization(authorization);
        IRestClient.put(url);
    }

    public void testPut_WithBody() throws Exception {
        String url = "http://homecamportal.cloudapp.net/api/User/UpdateProfile";
        IRestClient IRestClient = new RestClient();
        IRestClient.setAuthorization(authorization);
        User user = new User();
        user.RowKey = "f8ec6df8-74d0-49e8-af77-661dbb1d991e";
        user.fullName = "Luis Ginanjar";
        IRestClient.put(url, user);
    }

    public void testDelete_ErrorSystem() throws Exception {
        try {
            String url = "http://homecamportal.cloudapp.net/api/Camera/DeleteCamera?cameraRK=123456";
            IRestClient IRestClient = new RestClient();
            IRestClient.setAuthorization(authorization);
            IRestClient.delete(url);
            assertFalse(true);
        }
        catch (Exception e)
        {
            assertEquals("java.lang.Exception: Failed : HTTP error code : 404-Camera not found",e.toString());
        }

    }

}