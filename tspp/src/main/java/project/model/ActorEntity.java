package project.model;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Elvira on 07.03.2017.
 */
@Entity
@Table(name = "actor", schema = "db_tsp")
public class ActorEntity {
    private int idActor;
    private String name;
    private int age;
    private int country;
    private CountryEntity actorByCountry;
    private List<FilmEntity> filmsByIdActor;

    @Id
    @GenericGenerator(name = "num", strategy = "increment")
    @GeneratedValue(generator = "num")
    @Column(name = "id_actor", nullable = false)
    public int getIdActor() {
        return idActor;
    }

    public void setIdActor(int idActor) {
        this.idActor = idActor;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "age", nullable = false)
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Basic
    @Column(name = "country", nullable = false)
    public int getCountry() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActorEntity that = (ActorEntity) o;

        if (idActor != that.idActor) return false;
        if (age != that.age) return false;
        if (country != that.country) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idActor;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + country;
        return result;
    }

    @Override
    public String toString() {
        return name;
    }

    @ManyToOne(cascade= {CascadeType.REFRESH}, fetch=FetchType.LAZY)
    @JoinColumn(name = "country", referencedColumnName = "id_country", nullable = false, insertable = false, updatable = false)
    public CountryEntity getActorByCountry() {
        return actorByCountry;
    }

    public void setActorByCountry(CountryEntity actorByCountry) {
        this.actorByCountry = actorByCountry;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "filmByActor")
    public List<FilmEntity> getFilmsByIdActor() {
       return filmsByIdActor;
    }

    public void setFilmsByIdActor(List<FilmEntity> filmsByIdActor) {
        this.filmsByIdActor = filmsByIdActor;
    }
}
