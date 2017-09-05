package project.dao.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dao.interfaces.*;
import project.model.ActorEntity;

/**
 * Created by Elvira on 12.03.2017.
 */
@Repository
@Service
@Transactional
public class ActorImpl extends AbstractImpl implements ActorDao {

    private ActorDao actorDao;

    public ActorImpl(ActorDao actorDao) {
        this.actorDao = actorDao;
    }

    public ActorImpl() {
    }

    public void addActor(ActorEntity actor) {
        getSession().persist(actor);
    }

    public void updateActor(ActorEntity actor) {
        getSession().update(actor);
    }
    @Transactional(readOnly = true)
    public ActorEntity getActorById(Integer idActor) {
        ActorEntity actor = (ActorEntity) getSession().load(ActorEntity.class, new Integer(idActor));
        return actor;
    }
    @Transactional(readOnly = true)
    public List<ActorEntity> listActors()  {
        List<ActorEntity> actorEntityList =  getSession().createQuery("from ActorEntity ").list();
        return actorEntityList;
    }

    public void deleteActor(ActorEntity actor) {
   //     ActorEntity actor = (ActorEntity) getSession().load(ActorEntity.class, new Integer(idActor));
        if (actor != null) {
            getSession().delete(actor);
        }
    }


   /* public List<ActorEntity> getActorByFilm(FilmEntity film) throws SQLException {
        List<ActorEntity> actor = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Integer film_id = film.getIdFilm();
            Query query = session.createQuery(
                    " select b "
                            + " from ActorEntity b INNER JOIN b.filmsByIdActor film"
                            + " where film.id = :idFilm "
            )
                    .setLong("idFilm", film_id);
            actor = (List<ActorEntity>) query.list();
            session.getTransaction().commit();

        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return actor;
    }*/

//    public List<ActorEntity> getActorByFilm(FilmEntity film) throws SQLException {
//        session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
//        List<ActorEntity> actor = null;
//        try {
//            org.hibernate.Transaction tx = session.beginTransaction();
//            Query query = session.createQuery("from Contract as C where C.clients = '" + clientId + "'");
//            actor = (List<ActorEntity>) query.list();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        session.close();
//        return actor;
//    }
}
