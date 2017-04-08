package phone.gps.obj;

public class User {

	private int id;
	private String email;
	private String password;
	private String token;

	public User() {
		super();
	}

	public User(int id, String email, String password, String token) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.token = token;
	}

	public User(String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.token = token;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isUserValid(){
		if(this.getId()==0){
			return false;
		}
		return true;
	}

}
