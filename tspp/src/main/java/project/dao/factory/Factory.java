package project.dao.factory;

import project.dao.interfaces.*;
import project.dao.repository.*;

/**
 * Created by Elvira on 12.03.2017.
 */
public class Factory {

    private static ActorDao ActorDao = null;
    private static CountryDao CountryDao = null;
    private static FilmDao FilmDao = null;
    private static DirectorDao DirectorDao = null;
    private static Factory instance = null;

    public static synchronized Factory getInstance(){
        if (instance == null){
            instance = new Factory();
        }
        return instance;
    }

    public ActorDao getActorDao(){
        if (ActorDao == null){
            ActorDao = new ActorImpl();
        }
        return ActorDao;
    }
    public CountryDao getCountryDao(){
        if (CountryDao == null){
            CountryDao = new CountryImpl();
        }
        return CountryDao;
    }
    public FilmDao getFilmDao(){
        if (FilmDao == null){
            FilmDao = new FilmDaoImpl();
        }
        return FilmDao;
    }
    public DirectorDao getDirectorDao(){
        if (DirectorDao == null){
            DirectorDao = new DirectorImpl();
        }
        return DirectorDao;
    }
}
