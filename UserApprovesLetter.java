
public class UserApprovesLetter{

	//Fields
	private int id;
	private int usersId;
	private int letterId;
	private String createdAt;
	private String updatedAt;

	public void setId(int id){
		this.id = id
	}

	public void setUsersId(int usersId){
		this.usersId = usersId
	}

	public void setLetterId(int letterId){
		this.letterId = letterId
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt
	}

	public int getId(){
		return this.id
	}

	public int getUsersId(){
		return this.usersId
	}

	public int getLetterId(){
		return this.letterId
	}

	public String getCreatedAt(){
		return this.createdAt
	}

	public String getUpdatedAt(){
		return this.updatedAt
	}


}
