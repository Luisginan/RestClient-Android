package com.semico.support.restclient;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by luis on 2/12/2016.
 * Purpose :
 */
public class User  implements Serializable {
    public String fullName;
    public String RowKey;
    public boolean active;
    public long role;
    public Date created;
    public String id;

    @Override
    public String toString() {
        return "User{" +
                "fullName='" + fullName + '\'' +
                ", RowKey='" + RowKey + '\'' +
                ", active=" + active +
                ", role=" + role +
                '}';
    }
}
