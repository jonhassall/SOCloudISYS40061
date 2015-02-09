import Media.ShowingToday;
import java.util.Iterator;
import java.util.List;

import Media.*;

/**
 * 
 * Create a root class instance 'todaysShow' of ShowingToday type as per XML Schema
 * Unmarshal (open) from XML file
 * Create a list of MovieTypes under the ShowingToday type root node
 * Iterate through films, and only display if director equals 'Robert Benton'
 * Research XPath or XQuery
 * 
 */
public class Read {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Create root XML node 'todaysShow' and get its main element 'movies_today'
        ShowingToday todaysShow = new ShowingToday();
        
        try
        {
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(todaysShow.getClass().getPackage().getName());
            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            todaysShow = (ShowingToday) unmarshaller.unmarshal(new java.io.File("Now_Showing.txt")); //NOI18N
            
            //Print out only movies produced after 1990
            MovieType nextMovie = new MovieType();
            
            List<MovieType> movies_today =  todaysShow.getMovieCollection();
            MovieType film = new MovieType();
            
            Iterator itr = movies_today.iterator();
            while(itr.hasNext()) {
                nextMovie = (MovieType) itr.next();
                if(nextMovie.getDirector().contentEquals("Robert Benton")) {
                    System.out.println(nextMovie.getTitle() + " @ " + nextMovie.getYear());
                }

            }
            
        } catch (javax.xml.bind.JAXBException ex)
        {
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }
               

    }
}
