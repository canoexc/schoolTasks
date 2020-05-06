package service.impl;

import dao.impl.instituteDaoImpl;
import dao.instituteDao;
import org.apache.log4j.Logger;
import pojo.institute;
import service.instituteService;

import java.util.List;

public class instituteServiceImpl implements instituteService {
    Logger logger=Logger.getLogger(instituteServiceImpl.class);
    instituteDao id=new instituteDaoImpl();
    @Override
    public List<institute> showAllInstituteService() {
        List<institute> li=id.showAllInstituteDao();
        logger.debug("查询所有学院"+li);
        return li;
    }

    @Override
    public List<institute> showSubInstituteService(String type, String var) {
        List<institute> li=id.showSubInstituteDao(type, var);
        logger.debug("根据 "+type+":"+var+" 分类查询学院"+li);
        return li;
    }

    @Override
    public List<institute> showAllInstituteService(int start, int count) {
        List<institute> li=id.showAllInstituteDao(start,count);
        logger.debug("分页/查询所有学院"+li);
        return li;
    }

    @Override
    public List<institute> showSubInstituteService(String type, String var, int start, int count) {
        List<institute> li=id.showSubInstituteDao(type, var, start, count);
        logger.debug("根据 "+type+":"+var+" 分类查询学院"+li);
        return li;
    }

    @Override
    public boolean addInstituteService(institute i) {
        boolean flag=id.addInstituteDao(i);
        if(flag)
            logger.debug("添加"+i.getIname()+"学院成功");
        else  logger.debug("添加"+i.getIname()+"学院失败");
        return flag;
    }

    @Override
    public boolean delInstituteService(String iid) {
        boolean flag=id.delInstituteDao(iid);
        if(flag)
            logger.debug("删除"+iid+"学院成功");
        else  logger.debug("删除"+iid+"学院失败");
        return flag;
    }

    @Override
    public boolean upInstituteService(institute i) {
        boolean flag=id.upInstituteDao(i);
        if(flag)
            logger.debug("更新"+i.getIname()+"学院成功");
        else  logger.debug("更新"+i.getIname()+"学院失败");
        return flag;
    }
}
