package BUS;

import DAO.DangNhapDAO;

public class DangNhapBUS {
    public static String login(String username, String password, boolean remember) {
    	if (remember) {
			DangNhapDAO.saveLoginInfo(username, password);
		}else {
			DangNhapDAO.removeLoginInfo();
		}
        String result = DangNhapDAO.login(username, password);
        return result;
    }
    public static String[] getSavedLoginInfo() {
		String[] result = DangNhapDAO.getSavedLoginInfo();
		return result;
	}
}
