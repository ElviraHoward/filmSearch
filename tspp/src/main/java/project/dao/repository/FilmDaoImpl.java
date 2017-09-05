package project.dao.repository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dao.interfaces.*;
import project.model.FilmEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Elvira on 14.03.2017.
 */
@Repository
@Service
@Transactional
public class FilmDaoImpl extends AbstractImpl implements FilmDao {

    public FilmDaoImpl(FilmDao filmDao) {
        this.filmDao = filmDao;
    }

    private FilmDao filmDao;

    public FilmDaoImpl() {
    }

    public void addFilm(FilmEntity film) {
    getSession().persist(film);
    }

    public void updateFilm(FilmEntity film) {
        getSession().update(film);
    }

    public FilmEntity getFilmById(Integer idFilm)  {
        FilmEntity film = (FilmEntity) getSession().load(FilmEntity.class, new Integer(idFilm));
        return film;
    }

    public List<FilmEntity> listFilms()  {
            List<FilmEntity> filmEntityList = getSession().createQuery("from FilmEntity ").list();
            return filmEntityList;
    }

    public void deleteFilm(FilmEntity film) {
       // FilmEntity film = (FilmEntity)  getSession().load(FilmEntity.class, new Integer(idFilm));
        if(film!=null){
            getSession().delete(film);
        }
    }

}
