
public class UserApprovesLetterDAO{

	public UserApprovesLetter loadAll(){
		Connection connection = ConnectionManager.getConnection();
		UserApprovesLetter userApprovesLetters = new UserApprovesLetter()
		try{
			Statement stmt = connection.prepareStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM user_approves_letters");
			while(rs.next()){
				userApprovesLetters.id = rs.getInt("id");
				userApprovesLetters.usersId = rs.getInt("users_id");
				userApprovesLetters.letterId = rs.getInt("letter_id");
				userApprovesLetters.createdAt = rs.getString("created_at");
				userApprovesLetters.updatedAt = rs.getString("updated_at");
			}
		}catch(SQLException ex){
			//ex.printStackTrace();
		}
		return userApprovesLetters
	}


}
