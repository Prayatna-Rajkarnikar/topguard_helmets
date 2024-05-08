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

    public boolean isUsernameExists(String username) {
        try (Connection con = getConnection();
                PreparedStatement sm = con.prepareStatement(StringUtil.CHECK_USERNAME_EXISTS)) {
            sm.setString(1, username);
            try (ResultSet re = sm.executeQuery()) {
                if (re.next()) {
                    int count = re.getInt(1);
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
                PreparedStatement sm = con.prepareStatement(StringUtil.CHECK_EMAIL_EXISTS)) {
            sm.setString(1, email);
            try (ResultSet re = sm.executeQuery()) {
                if (re.next()) {
                    int count = re.getInt(1);
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
                PreparedStatement sm = con.prepareStatement(StringUtil.CHECK_CONTACT_EXISTS)) {
            sm.setString(1, phoneNumber);
            try (ResultSet re = sm.executeQuery()) {
                if (re.next()) {
                    int count = re.getInt(1);
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
                PreparedStatement sm = con.prepareStatement(StringUtil.INSERT_NEW_USER)) {
            sm.setString(1, helmetModel.getUserName());
            sm.setString(2, helmetModel.getFullName());
            sm.setString(3, helmetModel.getEmail());
            sm.setString(4, helmetModel.getPhoneNumber());
            sm.setDate(5, Date.valueOf(helmetModel.getDob()));
            sm.setString(6, helmetModel.getAddress());
            sm.setString(7, PasswordEncryptionWithAes.encrypt(helmetModel.getUserName(), helmetModel.getPassword()));
            sm.setString(8, helmetModel.getGender());
            sm.setString(9, helmetModel.getUserImageUrl());
            sm.setString(10, helmetModel.getRole());

            int result = sm.executeUpdate();
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

    public LoginStatus getUserLoginDetails(LoginUserModel helmetModel) {
        try (Connection con = getConnection()) {
            PreparedStatement sm = con.prepareStatement(StringUtil.GET_USER_INFO);

            // Set the username in the first parameter of the prepared statement
            sm.setString(1, helmetModel.getUserName());

            ResultSet re = sm.executeQuery();
            if (re.next()) {

                String helmetUserDb = re.getString(StringUtil.userName);

                String encryptedPwd = re.getString(StringUtil.password);

                String decryptedPwd = PasswordEncryptionWithAes.decrypt(encryptedPwd, helmetUserDb);

                if (helmetUserDb.equalsIgnoreCase(helmetModel.getUserName()) && decryptedPwd != null
                        && decryptedPwd.equals((helmetModel).getPassword())) {
                    String role = re.getString("role"); // Assuming 'role' is the column name for the user's role
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

    public int updateUserPwd(String userName, String new_password) {
        try (Connection con = getConnection();
                PreparedStatement sm = con.prepareStatement(StringUtil.UPDATE_PWD)) {
            sm.setString(1, PasswordEncryptionWithAes.encrypt(userName, new_password));
            sm.setString(2, userName);

            int result = sm.executeUpdate();

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

    public int updateUserPwdIfValid(String userName, String new_password) {
        try (Connection con = getConnection()) {
            // Check if the username exists in the database
            if (isUsernameExists(userName)) {
                // Username exists, update the password
                return updateUserPwd(userName, new_password);
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
                PreparedStatement sm = con.prepareStatement(StringUtil.ADD_HELMET)) {
            sm.setString(1, helmetModel.getHelmet_Name());
            sm.setDouble(2, helmetModel.getPrice());
            sm.setString(3, helmetModel.getBrand());
            sm.setString(4, helmetModel.getColor());
            sm.setString(5, helmetModel.getSize());
            sm.setString(6, helmetModel.getUserImageUrl());
            int result = sm.executeUpdate();

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
                PreparedStatement sm = con.prepareStatement(StringUtil.DELETE_HELMET)) {
            sm.setInt(1, deleteId);

            return sm.executeUpdate();

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            return -1; // Error
        }
    }

    public int updateHelmet(HelmetTableModel helmet) {
        try (Connection con = getConnection();
                PreparedStatement sm = con.prepareStatement(StringUtil.UPDATE_HELMET)) {
            sm.setString(1, helmet.getHelmet_Name());
            sm.setDouble(2, helmet.getPrice());
            sm.setString(3, helmet.getBrand());
            sm.setString(4, helmet.getColor());
            sm.setString(5, helmet.getSize());
            sm.setInt(6, helmet.getHelmet_ID());

            int result = sm.executeUpdate();

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

    public int updateProfile(HelmetUserModel helmetuser) {
        try (Connection con = getConnection();
                PreparedStatement sm = con.prepareStatement(StringUtil.UPDATE_USER_INFO)) {
            sm.setString(1, helmetuser.getFullName());
            sm.setString(2, helmetuser.getEmail());
            sm.setString(3, helmetuser.getPhoneNumber());
            sm.setString(4, helmetuser.getAddress());
            sm.setString(5, helmetuser.getUserName());

            return sm.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return -1; // Error
        }
    }

    public HelmetUserModel obtainUserProfile(String username) {
        try (Connection con = getConnection()) {
            PreparedStatement sm = con.prepareStatement(StringUtil.OBTAIN_USER_INFO);
            sm.setString(1, username);
            ResultSet re = sm.executeQuery();

            if (re.next()) {
                HelmetUserModel profile = new HelmetUserModel();
                profile.setUserName(re.getString("userName"));
                profile.setFullName(re.getString("user_fullName"));
                profile.setEmail(re.getString("email"));
                profile.setPhoneNumber(re.getString("contact_number"));
                profile.setDob(re.getDate("dob").toLocalDate());
                profile.setAddress(re.getString("address"));
                profile.setImageUrlFromDB("user_image");
                return profile;
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
                PreparedStatement sm = conn.prepareStatement(StringUtil.GET_HELMETS);
                ResultSet re = sm.executeQuery()) {

            ArrayList<HelmetTableModel> helmets = new ArrayList<>();
            while (re.next()) {
                HelmetTableModel helmet = new HelmetTableModel();
                helmet.setHelmet_ID(re.getInt("helmet_ID"));
                helmet.setHelmet_Name(re.getString("helmet_Name"));
                helmet.setPrice(re.getDouble("price"));
                helmet.setBrand(re.getString("brand"));
                helmet.setColor(re.getString("color"));
                helmet.setSize(re.getString("size"));
                helmet.setUserImageUrl(re.getString("helmet_image"));
                helmets.add(helmet);
            }
            return helmets;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null; // Error
        }
    }
}
