package movies;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProcessCommandServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
    	
        Key movieKey = KeyFactory.createKey("Movies", "Purdue");
        
        
      /*you don't need to worry about the variable below, this gets the value of the 
       * string entered in the text area as defined in the movies.jsp file
       */
        String content = req.getParameter("command");
        
        
        /*This string array contains the individual elements of the 
        command entered in the text area, e.g. if commandEls[0] gives "add_actor", 
        commandEls[1] gives the actor name, commandEls[2] gives the gender
        and commandEls[3] gives the date of birth*/ 
        String [] commandEls = content.split(":");
        
        /*This string contains the results to display to the user once a command is entered.
         * For a query, it should list the results of the query. 
         * For an insertion or deletion, it should either contain an error message or 
         * the message "Command executed successfully!"*/
        String results = null;
        
        
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        
        /*your implementation starts here*/
        
        if ( commandEls[0].equals( "add_actor" ) ) {
        	String name = commandEls[1];
        	String gender = commandEls[2];
        	String date_of_birth = commandEls[3];
        	
        	boolean duplicate = false;
        	
        	//check if actor exists in datastore, update value of duplicate
        	
        	
        	//if no duplicates, add an actor record with the given fields to the datastore
        	if (!duplicate) {
        		
        		//set the value of the "results" string
        		results = "Command executed successfully!";
        	}
        	
        	else {
        		results = "Actor already exists!";
        	}
        	
        }
        else if ( commandEls[0].equals( "add_director" ) ) {
        	String name = commandEls[1];
        	String gender = commandEls[2];
        	String date_of_birth = commandEls[3];
        	
        	//add a director record with the given fields to the datastore, don't forget to check for duplicates
        	
        	
        	//set the value of the "results" string
        		
        }
        
        //Include else-if statements for processing all the other commands 
        
        /*your implementation ends here */
        
        
        resp.sendRedirect( "/movies.jsp?moviedbName=Purdue&display=" + results );
    }  

}
