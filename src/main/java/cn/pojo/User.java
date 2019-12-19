package cn.pojo;

import org.springframework.stereotype.Component;

@Component("user")
public class User {
	private Integer id;
	private String tel;
	private String password;
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", tel=" + tel + ", password=" + password + ", name=" + name + "]";
	}

}
