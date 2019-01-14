package model;

import ch.efg.sample.api.IUser;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class User implements IUser, Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String groupId;

    public User() {

    }

    public User(String id, String name, String groupId) {
        this.id = id;
        this.name = name;
        this.groupId = groupId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return new StringBuffer(" Id:")
                .append(this.id).append(" Name:")
                .append(this.name).append(" GroupId:")
                .append(this.groupId).toString();
    }
}
