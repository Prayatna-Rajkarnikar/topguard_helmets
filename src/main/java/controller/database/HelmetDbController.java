package controller.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import model.HelmetModel;
import model.HelmetTableModel;
import model.LoginStatus;
import model.LoginUserModel;
import model.PasswordEncryptionWithAes;
import model.UpdateUserModel;
import model.HelmetUserModel;
import util.StringUtil;


public class HelmetDbController {
    private static final String url = "jdbc:mysql://localhost:3306/topguard_helmets";
    private static final String user = "root";
    private static final String pass = "";

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, pass);
    }
    
    public ArrayList<HelmetUserModel> getAllStudentsInfo() {
    	try (Connection con = getConnection()){
    		PreparedStatement st = con.prepareStatement(StringUtil.GET_USER_INFO);
    		ResultSet pr = st.executeQuery();
    		
    		ArrayList<HelmetUserModel> userModels = new ArrayList<>();
    		while (pr.next()) {
    			HelmetUserModel userModel = new HelmetUserModel();
        		
    			userModel.setUserName(pr.getString("userName"));
    			userModel.setFullName(pr.getString("full_name"));
    			userModel.setEmail(pr.getString("email"));
    			userModel.setPhoneNumber(pr.getString("Phone_number"));
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
             PreparedStatement st = con.prepareStatement("SELECT COUNT(*) FROM user WHERE userName = ?")) {
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
             PreparedStatement st = con.prepareStatement("SELECT COUNT(*) FROM user WHERE contact_number = ?")) {
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
    
    public int addNewUser(HelmetUserModel helmetModel) {
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(StringUtil.INSERT_NEW_USER)) {
            st.setString(1, helmetModel.getUserName());
            st.setString(2, helmetModel.getFullName());
            st.setString(3, helmetModel.getEmail());
            st.setString(4, helmetModel.getPhoneNumber());
            st.setDate(5, Date.valueOf(helmetModel.getDob()));
            st.setString(6, helmetModel.getAddress());
            st.setString(7, PasswordEncryptionWithAes.encrypt(helmetModel.getUserName(), helmetModel.getPassword()));
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

    
    public LoginStatus getUserLoginInfo(LoginUserModel loginModel) {
        try (Connection con = getConnection()) {
            PreparedStatement st = con.prepareStatement(StringUtil.GET_USER_INFO);

            // Set the username in the first parameter of the prepared statement
            st.setString(1, loginModel.getUserName());

            ResultSet rs = st.executeQuery();
            if (rs.next()) {

                String userDb = rs.getString("userName");

                String encryptedPwd = rs.getString(StringUtil.password);

                String decryptedPwd = PasswordEncryptionWithAes.decrypt(encryptedPwd, userDb);

                if (userDb.equalsIgnoreCase(loginModel.getUserName()) && decryptedPwd != null && decryptedPwd.equals((loginModel).getPassword())) {
                    String role = rs.getString("role"); // Assuming 'role' is the column name for the user's role
                    if (role != null) {
                        // User role found, return login result with role
                        return new LoginStatus(1, role); // 1 indicates successful login
                    } else {
                        // Role not found, return login result without role
                        return new LoginStatus(1, null); // 1 indicates successful login
                    }
                } else {
                    // Username or password mismatch, return login result without role
                    return new LoginStatus(0, null); // 0 indicates username or password mismatch
                }
            } else {
                // Username not found in the database, return login result without role
                return new LoginStatus(-1, null); // -1 indicates username not found
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return new LoginStatus(-2, null); // -2 indicates error
        }
    }
    
    public int updateUserPassword(String username, String new_password) {
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement("UPDATE user SET password = ? WHERE userName = ?")) {
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
    
    public int addHelmet(HelmetModel helmetModel) {
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement("INSERT INTO helmet (helmet_Name, price, brand, color, size, helmet_image) VALUES (?, ?, ?, ?,?,?)")) 
        {
            st.setString(1, helmetModel.getHelmet_Name());
            st.setDouble(2, helmetModel.getPrice());
            st.setString(3, helmetModel.getBrand());
            st.setString(4, helmetModel.getColor());
            st.setString(5, helmetModel.getSize());
            st.setString(6, helmetModel.getUserImageUrl());
            int result = st.executeUpdate();

            if (result > 0) {
                return 1; // Success
            } else {
                return 0; // No rows affected
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            return -1; // Error
        }
    }
    
    public int deleteHelmet(int deleteId) {
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement("DELETE FROM helmet WHERE helmet_ID = ?")) {
            st.setInt(1, deleteId);
            
            return st.executeUpdate();

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            return -1; // Error
        }
    }
    
    public int updateHelmet(HelmetTableModel helmet) {
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement("UPDATE helmet SET helmet_Name = ?, price = ?, brand = ?, color = ?, size = ? WHERE helmet_ID = ?")) {
        	st.setString(1, helmet.getHelmet_Name());
            st.setDouble(2, helmet.getPrice());
            st.setString(3, helmet.getBrand());
            st.setString(4, helmet.getColor());
            st.setString(5, helmet.getSize());
            st.setInt(6, helmet.getHelmet_ID());


            int result = st.executeUpdate();

            if (result > 0) {
                System.out.println("Database updated successfully");
                return 1; // Success
            } else {
                System.out.println("No rows affected, database not updated");
                return 0; // No rows affected
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return -1; // Error
        }
    }
    
    public int updateProfile(HelmetUserModel user) {
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement("UPDATE user SET user_fullName=?, email=?, contact_number=?, address=? WHERE userName=?")) {         
            st.setString(1, user.getFullName());
            st.setString(2, user.getEmail());
            st.setString(3, user.getPhoneNumber());
            st.setString(4, user.getAddress());
            st.setString(5, user.getUserName());

            return st.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return -1; // Error
        }
    }
    
    public HelmetUserModel getUserProfile(String username) {
        try (Connection con = getConnection()) {
            PreparedStatement st = con.prepareStatement("SELECT * FROM user WHERE userName = ?");
            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
            	HelmetUserModel userProfile = new HelmetUserModel();
                userProfile.setUserName(rs.getString("userName"));
                userProfile.setFullName(rs.getString("user_fullName"));
                userProfile.setEmail(rs.getString("email"));
                userProfile.setPhoneNumber(rs.getString("contact_number"));
                userProfile.setDob(rs.getDate("dob").toLocalDate());
                userProfile.setAddress(rs.getString("address"));
                userProfile.setImageUrlFromDB("imageUrl");
                return userProfile;
            } else {
                // User not found in the database
                return null;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }



    public ArrayList<HelmetTableModel> getAllHelmets() {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM helmet");
             ResultSet rs = stmt.executeQuery()) {

            ArrayList<HelmetTableModel> helmets = new ArrayList<>();
            while (rs.next()) {
                HelmetTableModel helmet = new HelmetTableModel();
                helmet.setHelmet_ID(rs.getInt("helmet_ID"));
                helmet.setHelmet_Name(rs.getString("helmet_Name"));
                helmet.setPrice(rs.getDouble("price"));
                helmet.setBrand(rs.getString("brand"));
                helmet.setColor(rs.getString("color"));
                helmet.setSize(rs.getString("size"));
                helmet.setUserImageUrl(rs.getString("helmet_image"));
                helmets.add(helmet);
            }
            return helmets;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null; // Error
        }
    }
    
    
}
