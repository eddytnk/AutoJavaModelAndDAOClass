
public class UserApprovesLetterDAO{

	public ArrayList<UserApprovesLetter> loadAll(){
		Connection connection = ConnectionManager.getConnection();
		ArrayList<UserApprovesLetter> list = new ArrayList()
		try{
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM user_approves_letters");
			ResultSet rs = stmt.execute();
			while(rs.next()){
				UserApprovesLetter userApprovesLetters = new UserApprovesLetter()
				userApprovesLetters.setId(rs.getInt("id"));
				userApprovesLetters.setUsersId(rs.getInt("users_id"));
				userApprovesLetters.setLetterId(rs.getInt("letter_id"));
				userApprovesLetters.setCreatedAt(rs.getString("created_at"));
				userApprovesLetters.setUpdatedAt(rs.getString("updated_at"));
				list.add(userApprovesLetters)
			}
		}catch(SQLException ex){
			//ex.printStackTrace();
		}
		return list
	}


	public boolean insert(UserApprovesLetter userApprovesLetters){
		Connection connection = ConnectionManager.getConnection();
		boolean insert = false;
		try{
			PreparedStatement  stmt = connection.prepareStatement("INSERT INTO user_approves_letters(users_id,letter_id,created_at,updated_at) VALUES (?,?,?,?)");
			stmt.setInt(1,userApprovesLetters.getUsersId());
			stmt.setInt(2,userApprovesLetters.getLetterId());
			stmt.setString(3,userApprovesLetters.getCreatedAt());
			stmt.setString(4,userApprovesLetters.getUpdatedAt());
			stmt.execute();
			insert = true
		}catch(SQLException ex){
			//ex.printStackTrace();
			insert = false
		}
		return insert
	}


}
