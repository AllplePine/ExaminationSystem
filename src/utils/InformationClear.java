package utils;

public class InformationClear {
    public static void clear(){
        String sql = "delete from information";
        try{
            DBUtil.executeUpdate(sql);
        }catch (Exception e){}
    }
}
