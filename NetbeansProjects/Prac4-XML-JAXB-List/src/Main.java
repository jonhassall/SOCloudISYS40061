import java.io.File;
import java.util.Iterator;
import java.util.List;

import Media.*;

/**
 *
 * @author taha-m
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Create root XML node 'todaysShow' and get its main element 'movies_today'
        ShowingToday todaysShow = new ShowingToday();
        List<MovieType> movies_today =  todaysShow.getMovieCollection();
        // Create Movie instanses and add them to the 'movies_today' collection
        MovieType film;

        film = new MovieType();
        film.setTitle("Red");
        film.setDirector("Robert Schwentke");
        film.setYear(2010);
        movies_today.add(film);

        film = new MovieType();
        film.setTitle("Kramer vs Kramer");
        film.setDirector("Robert Benton");
        film.setYear(1979);
        movies_today.add(film);

        film = new MovieType();
        film.setTitle("La Femme Nikita");
        film.setDirector("Luc Besson");
        film.setYear(1997);
        movies_today.add(film);

        film = new MovieType();
        film.setTitle("Feast of love");
        film.setDirector("Robert Benton");
        film.setYear(2007);
        movies_today.add(film);


        try {
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(todaysShow.getClass().getPackage().getName());
            javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            //Writing the whole XML document to file
            File movieStore = new File("Now_Showing.txt");
            marshaller.marshal(todaysShow, movieStore);

            //Print out only movies produced after 1990
            MovieType nextMovie = new MovieType();
            Iterator itr = movies_today.iterator();
            while(itr.hasNext()) {
                nextMovie = (MovieType) itr.next();
                if(nextMovie.getYear() > 1990) {
                    System.out.println(nextMovie.getTitle() + " @ " + nextMovie.getYear());
                }
            }
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }
        

    }
}
