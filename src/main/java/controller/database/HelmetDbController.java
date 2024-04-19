package controller.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.LoginUserModel;
import model.PasswordEncryptionWithAes;
import model.UserModel;
import util.StringUtil;


public class HelmetDbController {
    private static final String url = "jdbc:mysql://localhost:3306/topguard_helmets";
    private static final String user = "root";
    private static final String pass = "";

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, pass);
    }
    
    public ArrayList<UserModel> getAllStudentsInfo() {
    	try (Connection con = getConnection()){
    		PreparedStatement st = con.prepareStatement(StringUtil.GET_REGISTER_USER_INFO);
    		ResultSet pr = st.executeQuery();
    		
    		ArrayList<UserModel> userModels = new ArrayList<>();
    		while (pr.next()) {
    			UserModel userModel = new UserModel();
        		
    			userModel.setUser_name(pr.getString("user_name"));
    			userModel.setFull_name(pr.getString("full_name"));
    			userModel.setEmail(pr.getString("email"));
    			userModel.setPhone_number(pr.getString("Phone_number"));
    			userModel.setDob(pr.getDate("dob").toLocalDate());
    			userModel.setAddress(pr.getString("address"));
    			userModel.setGender(pr.getString("gender"));
    			userModel.setImageUrlFromDB(pr.getString("user_image"));
        	}
        	return userModels;
    	} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}	
    }
    public boolean isUsernameExists(String username) {
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement("SELECT COUNT(*) FROM user WHERE user_name = ?")) {
            st.setString(1, username);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace(); 
        }
        return false;
    }
    
    public boolean isEmailExists(String email) {
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement("SELECT COUNT(*) FROM user WHERE email = ?")) {
            st.setString(1, email);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean isPhoneNumberExists(String phoneNumber) {
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement("SELECT COUNT(*) FROM user WHERE phone_number = ?")) {
            st.setString(1, phoneNumber);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public int addNewUser(UserModel helmetModel) {
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(StringUtil.INSERT_NEW_USER)) {
            st.setString(1, helmetModel.getUser_name());
            st.setString(2, helmetModel.getFull_name());
            st.setString(3, helmetModel.getEmail());
            st.setString(4, helmetModel.getPhone_number());
            st.setDate(5, Date.valueOf(helmetModel.getDob()));
            st.setString(6, helmetModel.getAddress());
            st.setString(7, PasswordEncryptionWithAes.encrypt(helmetModel.getUser_name(), helmetModel.getPassword()));
            st.setString(8, helmetModel.getGender());
            st.setString(9, helmetModel.getUserImageUrl());
            st.setString(10, helmetModel.getRole());
            
            int result = st.executeUpdate();
            return result > 0 ? 1 : 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error message: " + e.getMessage()); // Log the SQL exception message
            return -1;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    
    public int getStudentLoginInfo(LoginUserModel loginUserModel) {
        try (Connection con = getConnection()) {
            PreparedStatement ps = getConnection()
                    .prepareStatement(StringUtil.GET_REGISTER_USER_INFO);

            // Set the username in the first parameter of the prepared statement
            ps.setString(1, loginUserModel.getUser_name());
            
            
            ResultSet pr = ps.executeQuery();
            if (pr.next()) {
               
                String userDb = pr.getString("user_name");

                String encryptedPwd = pr.getString(StringUtil.password);
               
                String decryptedPwd = PasswordEncryptionWithAes.decrypt(encryptedPwd, userDb);
              
                if (userDb.equalsIgnoreCase( loginUserModel.getUser_name()) 
                        && decryptedPwd.equals((loginUserModel).getPassword())) {
                    // Login successful, return 1
                    return 1;
                } else {
                    // Username or password mismatch, return 0
                    return 0;
                }
            } else {
                // Username not found in the database, return -1
                return -1;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return -2;
        }
    }
    
    public int updateUserPassword(String username, String new_password) {
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement("UPDATE user SET password = ? WHERE user_name = ?")) {
            st.setString(1, PasswordEncryptionWithAes.encrypt(username, new_password));
            st.setString(2, username);

            int result = st.executeUpdate();

            if (result > 0) {
                return 1; // Password updated successfully
            } else {
                return 0; // No rows affected (username not found)
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            return -1; // Error
        }
    }

    public int updateUserPasswordIfValid(String username, String new_password) {
        try (Connection con = getConnection()) {
            // Check if the username exists in the database
            if (isUsernameExists(username)) {
                // Username exists, update the password
                return updateUserPassword(username, new_password);
            } else {
                // Username not found, return -1
                return -1;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return -3; // Error
        }
    }
}
