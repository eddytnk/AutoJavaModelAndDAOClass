
public class UserApprovesLetter{

	//Fields
	private String id;
	private String usersId;
	private String letterId;
	private String createdAt;
	private String updatedAt;

	public void setId(String id){
		this.id = id
	}

	public void setUsersId(String usersId){
		this.usersId = usersId
	}

	public void setLetterId(String letterId){
		this.letterId = letterId
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt
	}

	public String getId(){
		return this.id
	}

	public String getUsersId(){
		return this.usersId
	}

	public String getLetterId(){
		return this.letterId
	}

	public String getCreatedAt(){
		return this.createdAt
	}

	public String getUpdatedAt(){
		return this.updatedAt
	}


}
