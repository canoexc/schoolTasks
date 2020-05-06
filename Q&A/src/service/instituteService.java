package service;

import pojo.institute;

import java.util.List;

public interface instituteService {
    List<institute> showAllInstituteService();
    List<institute> showSubInstituteService(String type,String var);
    List<institute> showAllInstituteService(int start,int count);
    List<institute> showSubInstituteService(String type,String var,int start,int count);
    boolean addInstituteService(institute i);
    boolean delInstituteService(String iid);
    boolean upInstituteService(institute i);
}
