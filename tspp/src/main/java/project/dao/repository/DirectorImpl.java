package project.dao.repository;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dao.interfaces.DirectorDao;
import org.springframework.stereotype.Repository;
import project.model.DirectorEntity;

/**
 * Created by Elvira on 14.03.2017.
 */
@Repository
@Service
@Transactional
public class DirectorImpl extends AbstractImpl implements DirectorDao {

    private DirectorDao directorDao;

    public DirectorImpl(DirectorDao directorDao) {
        this.directorDao = directorDao;
    }

    public DirectorImpl() {
    }

    public void addDirector(DirectorEntity director){
        getSession().persist(director);
    }

    public void updateDirector(DirectorEntity director) {
        getSession().update(director);
    }
    public DirectorEntity getDirectorById(Integer idDirector){
        DirectorEntity director = (DirectorEntity) getSession().load(DirectorEntity.class, new Integer(idDirector));
        return director;
    }
    public List<DirectorEntity> listDirectors(){
        List<DirectorEntity> directorEntityList =  getSession().createQuery("from DirectorEntity ").list();
        return directorEntityList;
    }
    public void deleteDirector(DirectorEntity director) {
     //   DirectorEntity director = getSession().load(DirectorEntity.class, new Integer(idDirector));
        if (director!= null) {
            getSession().delete(director);
        }
    }
}
