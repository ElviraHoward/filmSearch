package project;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import project.dao.factory.Factory;
import project.exceptions.UIException;
import project.model.ActorEntity;
import project.model.CountryEntity;
import project.model.DirectorEntity;
import project.model.FilmEntity;

/**
 * Created by Elvira on 04.05.2017.
 */
public class WindowDialog {
    static Factory factory = Factory.getInstance();

    private WindowDialog() {
    }

    private static void filmAddWindow(Window window, GridLayout layout, Button confirm){
        BeanFieldGroup<FilmEntity> beanFieldGroup = initFilmWindow(layout, confirm, window);
        beanFieldGroup.setItemDataSource(new FilmEntity());

        confirm.addClickListener((Button.ClickListener) clickEvent -> {
            try {
                beanFieldGroup.commit();
                final FilmEntity filmEntity = beanFieldGroup.getItemDataSource().getBean();
                factory.getFilmDao().addFilm(filmEntity);
                MyUI.getFilmBeanContainer().addBean(filmEntity);
            } catch (FieldGroup.CommitException e) {
                throw new UIException("Error of fill table");
            }
            window.close();
        });
    }

    private static void actorAddWindow(Window window, GridLayout layout, Button confirm){
        final BeanFieldGroup<ActorEntity> beanFieldGroup = initActorWindow(layout, confirm, window);
        beanFieldGroup.setItemDataSource(new ActorEntity());

        confirm.addClickListener((Button.ClickListener) clickEvent -> {
            try {
                beanFieldGroup.commit();
                final ActorEntity actorEntity = beanFieldGroup.getItemDataSource().getBean();
                factory.getActorDao().addActor(actorEntity);
                MyUI.getActorBeanContainer().addBean(actorEntity);
            } catch (FieldGroup.CommitException e) {
                throw new UIException("Error of fill table");
            }
            window.close();
        });
    }


    private static void directorAddWindow(Window window, GridLayout layout, Button confirm){
        final BeanFieldGroup<DirectorEntity> beanFieldGroup = initDirectorWindow(layout, confirm, window);
        beanFieldGroup.setItemDataSource(new DirectorEntity());

        confirm.addClickListener((Button.ClickListener) clickEvent -> {
            try {
                beanFieldGroup.commit();
                final DirectorEntity directorEntity = beanFieldGroup.getItemDataSource().getBean();
                factory.getDirectorDao().addDirector(directorEntity);
                MyUI.getDirectorBeanContainer().addBean(directorEntity);
            } catch (FieldGroup.CommitException e) {
                throw new UIException("Error of fill table");
            }
            window.close();
        });
    }

    private static void countryAddWindow(Window window, GridLayout layout, Button confirm){
        final BeanFieldGroup<CountryEntity> beanFieldGroup = initCountryWindow(layout, confirm, window);
        beanFieldGroup.setItemDataSource(new CountryEntity());

        confirm.addClickListener((Button.ClickListener) clickEvent -> {
            try {
                beanFieldGroup.commit();
                final CountryEntity countryEntity = beanFieldGroup.getItemDataSource().getBean();
                factory.getCountryDao().addCountry(countryEntity);
                MyUI.getCountryBeanContainer().addBean(countryEntity);
            } catch (FieldGroup.CommitException e) {
                throw new UIException("Error of fill table");
            }
            window.close();
        });
    }
    //todo здесь добавить обработку исключений
    public static void addWindow(String tableName){
        final Window window = new Window("New " + tableName);
        GridLayout layout = new GridLayout(2,7);
        final Button confirm = new Button("Confirm");
        confirm.setStyleName(ValoTheme.BUTTON_FRIENDLY);
        window.setModal(true);
        switch (tableName) {
            case MyUI.FILM_TABLE_NAME:
                filmAddWindow(window, layout, confirm);
                break;
            case MyUI.ACTOR_TABLE_NAME:
                actorAddWindow(window, layout, confirm);
                break;
            case MyUI.DIRECTOR_TABLE_NAME:
                directorAddWindow(window, layout, confirm);
                break;
            case MyUI.COUNTRY_TABLE_NAME:
                countryAddWindow(window, layout, confirm);
            default:
                break;
        }
        window.setSizeUndefined();
        window.setResizable(false);
        MyUI.getCurrent().addWindow(window);
    }

    private static void abstractDeleteWindow(final Window window,
                                             final VerticalLayout layout,
                                             final Integer id,
                                             final String tableName) {
        Label header = null;
        switch (tableName) {
            case MyUI.FILM_TABLE_NAME:
                header = new Label("Are you really want to delete this note of "+tableName +  " ?");
                break;
            case MyUI.ACTOR_TABLE_NAME:
                header = new Label("Are you really want to delete this note of "+tableName + " ?");
                break;
            case MyUI.DIRECTOR_TABLE_NAME:
                header = new Label("Are you really want to delete this note of "+tableName + " ?");
                break;
            case MyUI.COUNTRY_TABLE_NAME:
                header = new Label("Are you really want to delete this note of "+tableName + " ?");
                break;
            default:
                break;
        }
        layout.addComponent(header);
        final Button confirm = new Button("Yes");
        confirm.setStyleName(ValoTheme.BUTTON_PRIMARY);
        layout.addComponent(confirm);
        confirm.addClickListener((Button.ClickListener) clickEvent -> {
            switch (tableName) {
                case  MyUI.FILM_TABLE_NAME:
                    factory.getFilmDao().deleteFilm(MyUI.getFilmBeanContainer().getItem(id).getBean());
                    MyUI.getFilmBeanContainer().removeItem(id);
                    break;
                case MyUI.ACTOR_TABLE_NAME:
                    factory.getActorDao().deleteActor(MyUI.getActorBeanContainer().getItem(id).getBean());
                    MyUI.getActorBeanContainer().removeItem(id);
                    break;
                case MyUI.DIRECTOR_TABLE_NAME:
                    factory.getDirectorDao().deleteDirector(MyUI.getDirectorBeanContainer().getItem(id).getBean());
                    MyUI.getDirectorBeanContainer().removeItem(id);
                    break;
                case MyUI.COUNTRY_TABLE_NAME:
                    factory.getCountryDao().deleteCountry(MyUI.getCountryBeanContainer().getItem(id).getBean());
                    MyUI.getCountryBeanContainer().removeItem(id);
                    break;
                default:
                    break;
            }
            window.close();
        });
    }

    public static void deleteWindow(final String tableName, final Integer id){
         final Window window = new Window("Delete " + tableName);
         final VerticalLayout layout = new VerticalLayout();
        window.setModal(true);
        abstractDeleteWindow(window, layout, id, tableName);
        layout.setMargin(true);
        layout.setSpacing(true);
        window.setContent(layout);
        window.setResizable(false);
        window.setSizeUndefined();
        MyUI.getCurrent().addWindow(window);
    }

    private static void filmUpdateWindow( final Window window,
                                          final GridLayout layout,
                                          final Button confirm,
                                          final Integer id){
        BeanFieldGroup<FilmEntity> beanFieldGroup = initFilmWindow(layout, confirm, window);
        beanFieldGroup.setItemDataSource(MyUI.getFilmBeanContainer().getItem(id).getBean());

        confirm.addClickListener((Button.ClickListener) clickEvent -> {
            try {
                beanFieldGroup.commit();
               final FilmEntity filmEntity = beanFieldGroup.getItemDataSource().getBean();
                factory.getFilmDao().updateFilm(filmEntity);
                MyUI.getFilmBeanContainer().removeItem(id);
                MyUI.getFilmBeanContainer().addBean(filmEntity);
            }catch (FieldGroup.CommitException e) {
                throw new UIException("Error of fill table");
            }
            window.close();
        });
    }


    private static void directorUpdateWindow( final Window window,
                                              final GridLayout layout,
                                              final Button confirm,
                                              final Integer id){

        final BeanFieldGroup<DirectorEntity> beanFieldGroup = initDirectorWindow(layout, confirm, window);
        beanFieldGroup.setItemDataSource(MyUI.getDirectorBeanContainer().getItem(id).getBean());

        confirm.addClickListener((Button.ClickListener) clickEvent -> {
            try {
                beanFieldGroup.commit();
                final DirectorEntity directorEntity = beanFieldGroup.getItemDataSource().getBean();
                factory.getDirectorDao().updateDirector(directorEntity);
                MyUI.getDirectorBeanContainer().removeItem(id);
                MyUI.getDirectorBeanContainer().addBean(directorEntity);
            }catch (FieldGroup.CommitException e) {
                throw new UIException("Error of fill table");
            }
            window.close();
        });
    }

    private static void actorUpdateWindow( final Window window,
                                           final GridLayout layout,
                                           final Button confirm,
                                           final Integer id){

       final BeanFieldGroup<ActorEntity> beanFieldGroup = initActorWindow(layout, confirm, window);
        beanFieldGroup.setItemDataSource(MyUI.getActorBeanContainer().getItem(id).getBean());

        confirm.addClickListener((Button.ClickListener) clickEvent -> {
            try {
                beanFieldGroup.commit();
                final ActorEntity actorEntity = beanFieldGroup.getItemDataSource().getBean();
                factory.getActorDao().updateActor(actorEntity);
                MyUI.getActorBeanContainer().removeItem(id);
                MyUI.getActorBeanContainer().addBean(actorEntity);
            }catch (FieldGroup.CommitException e) {
                throw new UIException("Error of fill table");
            }
            window.close();
        });
    }

    private static void countryUpdateWindow( final Window window,
                                           final GridLayout layout,
                                             final Button confirm,
                                           final Integer id){

         final BeanFieldGroup<CountryEntity> beanFieldGroup = initCountryWindow(layout, confirm, window);
        beanFieldGroup.setItemDataSource(MyUI.getCountryBeanContainer().getItem(id).getBean());

        confirm.addClickListener((Button.ClickListener) clickEvent -> {
            try {
                beanFieldGroup.commit();
                final CountryEntity countryEntity = beanFieldGroup.getItemDataSource().getBean();
                factory.getCountryDao().updateCountry(countryEntity);
                MyUI.getCountryBeanContainer().removeItem(id);
                MyUI.getCountryBeanContainer().addBean(countryEntity);
            } catch (FieldGroup.CommitException e) {
                throw new UIException("Error of fill table");
            }
            window.close();
        });
    }

    public static void updateWindow(String tableName, Integer id){
        final Window window = new Window("Modify " + tableName);
        final GridLayout layout = new GridLayout(2,7);
        final Button confirm = new Button("Confirm");
        confirm.setStyleName(ValoTheme.BUTTON_FRIENDLY);
        window.setModal(true);
        switch (tableName) {
            case MyUI.FILM_TABLE_NAME:
                filmUpdateWindow(window, layout, confirm, id);
                break;
            case MyUI.ACTOR_TABLE_NAME:
                actorUpdateWindow(window, layout, confirm, id);
                break;
            case MyUI.DIRECTOR_TABLE_NAME:
                directorUpdateWindow(window, layout, confirm, id);
                break;
            case MyUI.COUNTRY_TABLE_NAME:
                countryUpdateWindow(window, layout, confirm, id);
                break;
            default:
                break;
        }
        window.setSizeUndefined();
        window.setResizable(false);
        MyUI.getCurrent().addWindow(window);
    }

    private static BeanFieldGroup<FilmEntity> initFilmWindow(final GridLayout layout, final Button confirm, Window window){
         final TextField name = new TextField();
         final TextField releaseDate = new TextField();
         final TextField description = new TextField();
         final TextField country = new TextField();
         final TextField director = new TextField();
         final TextField actor = new TextField();
        final TextField filmByActor = new TextField();
        final TextField filmByDirector = new TextField();
        final TextField filmByCountry = new TextField();
        layout.addComponent(new Label("Name: "),0,0);
        layout.addComponent(name,1,0);
        layout.addComponent(new Label("Release Date: "),0,1);
        layout.addComponent(releaseDate,1,1);
        layout.addComponent(new Label("Description: "),0,2);
        layout.addComponent(description,1,2);
        layout.addComponent(new Label("Country: "),0,3);
        layout.addComponent(country,1,3);
        layout.addComponent(new Label("Director: "),0,4);
        layout.addComponent(director,1,4);
        layout.addComponent(new Label("Main actor: "),0,5);
        layout.addComponent(actor,1,5);
        layout.addComponent(confirm,1,6);
        layout.setMargin(true);
        layout.setSpacing(true);
        window.setContent(layout);
        final BeanFieldGroup<FilmEntity> beanFieldGroup = new BeanFieldGroup<>(FilmEntity.class);
        beanFieldGroup.bind(name,"name");
        beanFieldGroup.bind(releaseDate,"releaseDate");
        beanFieldGroup.bind(description,"description");
        beanFieldGroup.bind(country,"country");
        beanFieldGroup.bind(director,"director");
        beanFieldGroup.bind(actor,"actor");
        beanFieldGroup.bind(actor,"actor");
        beanFieldGroup.bind(actor,"actor");
        beanFieldGroup.bind(actor,"actor");
        return beanFieldGroup;
    }

    private static BeanFieldGroup<DirectorEntity> initDirectorWindow(final GridLayout layout, final Button confirm,  Window window){
        final TextField name = new TextField();
        final TextField country = new TextField();
        layout.addComponent(new Label("Name: "),0,0);
        layout.addComponent(name,1,0);
        layout.addComponent(new Label("Country: "),0,1);
        layout.addComponent(country,1,1);
        layout.addComponent(confirm,1,2);
        layout.setMargin(true);
        layout.setSpacing(true);
        window.setContent(layout);
        final BeanFieldGroup<DirectorEntity> beanFieldGroup = new BeanFieldGroup<>(DirectorEntity.class);
        beanFieldGroup.bind(name, "name");
        beanFieldGroup.bind(country, "country");
        return beanFieldGroup;
    }
    private static BeanFieldGroup<ActorEntity> initActorWindow(final GridLayout layout, final Button confirm, Window window){
        final TextField name = new TextField();
        final TextField age = new TextField();
        final TextField country = new TextField();
        layout.addComponent(new Label("Name: "),0,0);
        layout.addComponent(name,1,0);
        layout.addComponent(new Label("Age: "),0,1);
        layout.addComponent(age,1,1);
        layout.addComponent(new Label("Country: "),0,2);
        layout.addComponent(country,1,2);
        layout.addComponent(confirm,1,3);
        layout.setMargin(true);
        layout.setSpacing(true);
        window.setContent(layout);
        final BeanFieldGroup<ActorEntity> beanFieldGroup = new BeanFieldGroup<>(ActorEntity.class);
        beanFieldGroup.bind(name,"name");
        beanFieldGroup.bind(age,"age");
        beanFieldGroup.bind(country,"country");
        return beanFieldGroup;
    }
    private static BeanFieldGroup<CountryEntity> initCountryWindow(final GridLayout layout, final Button confirm, Window window){
        final TextField name = new TextField();
        layout.addComponent(new Label("Name: "),0,0);
        layout.addComponent(name,1,0);
        layout.addComponent(confirm,1,1);
        layout.setMargin(true);
        layout.setSpacing(true);
        window.setContent(layout);
        final BeanFieldGroup<CountryEntity> beanFieldGroup = new BeanFieldGroup<>(CountryEntity.class);
        beanFieldGroup.bind(name,"name");
        return beanFieldGroup;
    }
}
