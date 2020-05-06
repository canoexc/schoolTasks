package dao;

import pojo.institute;

import java.util.List;

public interface instituteDao {
    List<institute> showAllInstituteDao();
    List<institute> showSubInstituteDao(String type,String var);
    List<institute> showAllInstituteDao(int start,int count);
    List<institute> showSubInstituteDao(String type,String var,int start,int count);
    boolean addInstituteDao(institute i);
    boolean delInstituteDao(String iid);
    boolean upInstituteDao(institute i);
}
