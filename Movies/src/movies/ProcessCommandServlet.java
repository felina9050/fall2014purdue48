package movies;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.CompositeFilter;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query.SortDirection;

import java.io.IOException;
import java.util.ArrayList;
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
        
        Query mydeleteq = new Query();
	    PreparedQuery pqdel = datastore.prepare(mydeleteq);
	    for (Entity result : pqdel.asIterable()) {
	        datastore.delete(result.getKey());      
	    }   
		test.init();
		for(int ii=1;ii<test.length;ii++){
			commandEls=test.input[ii].split(":");
        
        
        /*your implementation starts here*/
        if ( commandEls[0].equals( "add_actor" ) ) {
        	String name = commandEls[1];
        	String gender = commandEls[2];
        	String date_of_birth = commandEls[3];
        	
        	boolean duplicate = true;
        	
        	//check if actor exists in datastore, update value of duplicate
        	Key key = KeyFactory.createKey("ACTOR", name);
        	
        	
    		try {
				datastore.get(key);
			} catch (EntityNotFoundException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				duplicate = false;
			}
        	
        	//if no duplicates, add an actor record with the given fields to the datastore
        	if (!duplicate) {
        		Entity entity = new Entity(key);
        		entity.setProperty("NAME", name);
        		entity.setProperty("GENDER", gender);
        		entity.setProperty("DATE_OF_BIRTH", date_of_birth);
        		datastore.put(entity);
   
        		//set the value of the "results" string
        		results = "Command executed successfully!";
        	}
        	
        	else {
        		results = "Error: Actor already exists!";
        	}
        	
        }
        else if ( commandEls[0].equals( "add_director" ) ) {
        	String name = commandEls[1];
        	String gender = commandEls[2];
        	String date_of_birth = commandEls[3];
        	
        	boolean duplicate = true;
        	
        	//check if actor exists in datastore, update value of duplicate
        	Key key = KeyFactory.createKey("DIRECTOR", name);
        	
        	
    		try {
				datastore.get(key);
			} catch (EntityNotFoundException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				duplicate = false;
			}
        	
        	//if no duplicates, add an actor record with the given fields to the datastore
        	if (!duplicate) {
        		Entity entity = new Entity(key);
        		entity.setProperty("NAME", name);
        		entity.setProperty("GENDER", gender);
        		entity.setProperty("DATE_OF_BIRTH", date_of_birth);
        		datastore.put(entity);
   
        		//set the value of the "results" string
        		results = "Command executed successfully!";
        	}
        	
        	else {
        		results = "Error: Director already exists!";
        	}
        }
        else if ( commandEls[0].equals( "add_company" ) ) {
        	String name = commandEls[1];
        	String address = commandEls[2];
        	
        	boolean duplicate = true;
        	
        	//check if actor exists in datastore, update value of duplicate
        	Key key = KeyFactory.createKey("PRODUCTION_COMPANY", name);
        	
        	
    		try {
				datastore.get(key);
			} catch (EntityNotFoundException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				duplicate = false;
			}
        	
        	//if no duplicates, add an actor record with the given fields to the datastore
        	if (!duplicate) {
        		Entity entity = new Entity(key);
        		entity.setProperty("NAME", name);
        		entity.setProperty("ADDRESS", address);
        		datastore.put(entity);
   
        		//set the value of the "results" string
        		results = "Command executed successfully!";
        	}
        	
        	else {
        		results = "Error: Company already exists!";
        	}
        }
        else if ( commandEls[0].equals( "add_movie" ) ) {
        	String title = commandEls[1];
        	String release_year = commandEls[2];
        	String length = commandEls[3];
        	String genre = commandEls[4];
        	String plot = commandEls[5];
        	String director = commandEls[6];
        	String company = commandEls[7];
        	boolean duplicate = true;
        	boolean foreign = true;
        	Key key = KeyFactory.createKey("MOVIE", title+", "+release_year);   
    		try {
				datastore.get(key);
			} catch (EntityNotFoundException e) {
				duplicate = false;
			}
        	if (!duplicate) {
        		
        		Key key1 = KeyFactory.createKey("PRODUCTION_COMPANY", company);
				Key key2 = KeyFactory.createKey("DIRECTOR", director);
				try{
					datastore.get(key1);
					datastore.get(key2);
				}catch(EntityNotFoundException e){
					foreign = false;
				}	
				if(foreign){
					Entity entity = new Entity(key);
					entity.setProperty("TITLE", title);
					entity.setProperty("RELEASE_YEAR", release_year);
					entity.setProperty("LENGTH", length);
					entity.setProperty("GENRE", genre);
					entity.setProperty("PLOT", plot);
					entity.setProperty("DIRECTOR", director);
					entity.setProperty("COMPANY", company);
					datastore.put(entity);
					results = "Command executed successfully!";
				}
				else
					results = "Foreign key constraints violated!";
        	}
        	else
        		results = "Error: Movie already exists!";
        }
        else if ( commandEls[0].equals( "add_awards_event" ) ) {
        	String event_name = commandEls[1];
        	String year = commandEls[2];
        	String venue = commandEls[3];
        	
        	boolean duplicate = true;
        	Key key = KeyFactory.createKey("AWARDS_EVENT", event_name+", "+year);   
    		try {
				datastore.get(key);
			} catch (EntityNotFoundException e) {
				duplicate = false;
			}
        	if (!duplicate) {
					Entity entity = new Entity(key);
					entity.setProperty("EVENT_NAME", event_name);
					entity.setProperty("YEAR", year);
					entity.setProperty("VENUE", venue);

					datastore.put(entity);
					results = "Command executed successfully!";
        	}
        	else
        		results = "Error: Event already exists!";
        }
        else if ( commandEls[0].equals( "add_user" ) ) {
        	String user_id = commandEls[1];
        	
        	boolean duplicate = true;
        	Key key = KeyFactory.createKey("USER", user_id);   
    		try {
				datastore.get(key);
			} catch (EntityNotFoundException e) {
				duplicate = false;
			}
        	if (!duplicate) {
					Entity entity = new Entity(key);
					entity.setProperty("USER_ID", user_id);
					datastore.put(entity);
					results = "Command executed successfully!";
        	}
        	else
        		results = "Error: User already exists!";
        }
        else if ( commandEls[0].equals( "add_movie_rating" ) ) {
        	String user_id = commandEls[1];
        	String movie_title = commandEls[2];
        	String release_year = commandEls[3];
        	String rating = commandEls[4];
        
        	boolean duplicate = true;
        	boolean foreign = true;
        	Key key = KeyFactory.createKey("RATED_BY", user_id+", "+movie_title+", "+release_year);   
    		try {
				datastore.get(key);
			} catch (EntityNotFoundException e) {
				duplicate = false;
			}
        	if (!duplicate) {
        		
        		Key key1 = KeyFactory.createKey("USER", user_id);
				Key key2 = KeyFactory.createKey("MOVIE", movie_title+", "+release_year);
				try{
					datastore.get(key1);
					datastore.get(key2);
				}catch(EntityNotFoundException e){
					foreign = false;
				}	
				if(foreign){
					Entity entity = new Entity(key);
					entity.setProperty("USER_ID", user_id);
					entity.setProperty("MOVIE_TITLE", movie_title);
					entity.setProperty("RELEASE_YEAR", release_year);
					entity.setProperty("RATING", rating);
				
					datastore.put(entity);
					results = "Command executed successfully!";
				}
				else
					results = "Foreign key constraints violated!";
        	}
        	else
        		results = "Error: Rating already exists!";
        }
        else if ( commandEls[0].equals( "add_cast" ) ) {
        	String movie_title = commandEls[1];
        	String release_year = commandEls[2];
        	String actor_name = commandEls[3];
        	String role = commandEls[4];
        
        	boolean duplicate = true;
        	boolean foreign = true;
        	Key key = KeyFactory.createKey("CAST", movie_title+", "+release_year+", "+actor_name);   
    		try {
				datastore.get(key);
			} catch (EntityNotFoundException e) {
				duplicate = false;
			}
        	if (!duplicate) {
        		
        		Key key1 = KeyFactory.createKey("MOVIE", movie_title+", "+release_year);
				Key key2 = KeyFactory.createKey("ACTOR", actor_name);
				try{
					datastore.get(key1);
					datastore.get(key2);
				}catch(EntityNotFoundException e){
					foreign = false;
				}	
				if(foreign){
					Entity entity = new Entity(key);
					entity.setProperty("MOVIE_TITLE", movie_title);
					entity.setProperty("RELEASE_YEAR", release_year);
					entity.setProperty("ACTOR_NAME", actor_name);
					entity.setProperty("ROLE", role);

					datastore.put(entity);
					results = "Command executed successfully!";
				}
				else
					results = "Foreign key constraints violated!";
        	}
        	else
        		results = "Error: Cast already exists!";
        }  
        else if ( commandEls[0].equals( "add_nomination_category" ) ) {
        	String category_name = commandEls[1];
        	
        	boolean duplicate = true;
        	Key key = KeyFactory.createKey("NOMINATION", category_name);   
    		try {
				datastore.get(key);
			} catch (EntityNotFoundException e) {
				duplicate = false;
			}
        	if (!duplicate) {
					Entity entity = new Entity(key);
					entity.setProperty("CATEGORY_NAME", category_name);
					datastore.put(entity);
					results = "Command executed successfully!";
        	}
        	else
        		results = "Error: Category already exists!";
        }
        else if ( commandEls[0].equals( "add_nomination" ) ) {
        	String movie_title = commandEls[1];
        	String movie_year = commandEls[2];
        	String event = commandEls[3];
        	String event_year = commandEls[4];
        	String category = commandEls[5];
        	String won = commandEls[6];
        
        	boolean duplicate = true;
        	boolean foreign = true;
        	Key key = KeyFactory.createKey("NOMINATED", movie_title+", "+movie_year+", "+event+", "+event_year+", "+category);   
    		try {
				datastore.get(key);
			} catch (EntityNotFoundException e) {
				duplicate = false;
			}
        	if (!duplicate) {
        		
        		Key key1 = KeyFactory.createKey("MOVIE", movie_title+", "+movie_year);
				Key key2 = KeyFactory.createKey("AWARDS_EVENT", event+", "+event_year);
				Key key3 = KeyFactory.createKey("NOMINATION", category);
				try{
					datastore.get(key1);
					datastore.get(key2);
					datastore.get(key3);
				}catch(EntityNotFoundException e){
					foreign = false;
				}	
				if(foreign){
					Entity entity = new Entity(key);
					entity.setProperty("MOVIE_TITLE", movie_title);
					entity.setProperty("MOVIE_YEAR", movie_year);
					entity.setProperty("EVENT", event);
					entity.setProperty("EVENT_YEAR", event_year);
					entity.setProperty("CATEGORY", category);
					entity.setProperty("WON", won);

					datastore.put(entity);
					results = "Command executed successfully!";
				}
				else
					results = "Foreign key constraints violated!";
        	}
        	else
        		results = "Error: Nomination already exists!";
        }  
        else if(commandEls[0].equals( "get_movies_by_company" )){
        	String company_name = commandEls[1];
        	
        	Filter filter = new FilterPredicate("COMPANY",FilterOperator.EQUAL,company_name);
        	Query q = new Query("MOVIE").setFilter(filter);
        			//.addSort("TITLE", SortDirection.DESCENDING)
        			//.addSort("RELEASE_YEAR", SortDirection.DESCENDING);
        	PreparedQuery pq = datastore.prepare(q);
        	
        	results = "";
        	for(Entity result : pq.asIterable()){
        		results += ((String)result.getProperty("TITLE")+", "+(String)result.getProperty("RELEASE_YEAR")+"; ");
        		
        	}
        	results = results.substring(0, results.length()-2);
        	
        }
        else if(commandEls[0].equals( "get_movies_by_director" )){
        	String director_name = commandEls[1];
        	
        	Filter filter = new FilterPredicate("DIRECTOR",FilterOperator.EQUAL,director_name);
        	Query q = new Query("MOVIE").setFilter(filter);
        		//	.addSort("TITLE", SortDirection.DESCENDING)
        		//	.addSort("RELEASE_YEAR", SortDirection.DESCENDING);
        	PreparedQuery pq = datastore.prepare(q);
        	
        	results = "";
        	for(Entity result : pq.asIterable()){
        		results += ((String)result.getProperty("TITLE")+", "+(String)result.getProperty("RELEASE_YEAR")+"; ");
        	}
        	results = results.substring(0, results.length()-2);
        	
        }
        else if(commandEls[0].equals( "get_nominations_for_actor" )){
        	String actor_name = commandEls[1];
        	
        	Filter filter = new FilterPredicate("ACTOR_NAME",FilterOperator.EQUAL,actor_name);
        	Query q = new Query("CAST").setFilter(filter);
        	PreparedQuery pq = datastore.prepare(q);
        	
        //	Entity entity = new Entity("ACTOR", actor_name);
        //	String gender = (String) entity.getProperty("GENDER");
        	/*
        	Key key = KeyFactory.createKey("ACTOR", actor_name);
        	Entity entity = datastore.get(key);
        	String gender = (String) entity.getProperty("GENDER");
        	*/
        	List<Filter> filterSub = new ArrayList<Filter>();
        	for(Entity result : pq.asIterable()){
        		
        		String movie_title = (String)result.getProperty("MOVIE_TITLE");
        		String movie_year = (String)result.getProperty("RELEASE_YEAR");
        		String role = (String)result.getProperty("ROLE");
        		String category = "best "+role;
        	//	if( (gender.toLowerCase() == "male" && role.contains("actor")) || (gender.toLowerCase() == "female" && role.contains("actress")) ){
        	//		category = "best "+role;
        	//	}  		
        		Filter filter1 = new FilterPredicate("MOVIE_TITLE",FilterOperator.EQUAL, movie_title);
        		Filter filter2 = new FilterPredicate("MOVIE_YEAR",FilterOperator.EQUAL, movie_year);
        		Filter filter3 = new FilterPredicate("CATEGORY",FilterOperator.EQUAL, category);
        		filterSub.add(CompositeFilterOperator.and(filter1,filter2,filter3));
        				
        	} 	
        	Filter filterNew = CompositeFilterOperator.or(filterSub);
        	Query qNew = new Query("NOMINATED").setFilter(filterNew);
    			//	.addSort("EVENT", SortDirection.ASCENDING)
        		//	.addSort("EVENT_YEAR", SortDirection.DESCENDING)
    			//	.addSort("CATEGORY", SortDirection.ASCENDING)
    			//	.addSort("WON", SortDirection.DESCENDING);
    		PreparedQuery pqNew = datastore.prepare(qNew);
    		results = "";
    		for(Entity resultNew : pqNew.asIterable()){
    			results += ((String)resultNew.getProperty("EVENT")+", "+(String)resultNew.getProperty("EVENT_YEAR")+", "+(String)resultNew.getProperty("CATEGORY")+", "+(String)resultNew.getProperty("WON")+"; ");
    		} results = results.substring(0, results.length()-2);
        }
        else if(commandEls[0].equals( "get_movies_of_genre_for_actor" )){
        	String actor_name = commandEls[1];
        	String genre = commandEls[2];
        	
        	Filter filter = new FilterPredicate("ACTOR_NAME",FilterOperator.EQUAL,actor_name);
        	Query q = new Query("CAST").setFilter(filter);
        	PreparedQuery pq = datastore.prepare(q);
        	
        	List<Filter> filterSub = new ArrayList<Filter>();
        	for(Entity result : pq.asIterable()){
        		String movie_title = (String)result.getProperty("MOVIE_TITLE");
        		String movie_year = (String)result.getProperty("RELEASE_YEAR");
        		
           		Filter filter1 = new FilterPredicate("TITLE",FilterOperator.EQUAL, movie_title);
        		Filter filter2 = new FilterPredicate("RELEASE_YEAR",FilterOperator.EQUAL, movie_year);
        		Filter filter3 = new FilterPredicate("GENRE",FilterOperator.EQUAL, genre);
        		filterSub.add(CompositeFilterOperator.and(filter1,filter2,filter3));
        	}
        	Filter filterNew = CompositeFilterOperator.or(filterSub);
        	Query qNew = new Query("MOVIE").setFilter(filterNew);
    			//	.addSort("TITLE", SortDirection.ASCENDING)
    			//	.addSort("RELEASE_YEAR", SortDirection.DESCENDING);
    		PreparedQuery pqNew = datastore.prepare(qNew);
    		results = "";
    		for(Entity resultNew : pqNew.asIterable()){
    			results += ((String)resultNew.getProperty("TITLE")+", "+(String)resultNew.getProperty("RELEASE_YEAR")+"; ");
    		} 
    		results = results.substring(0, results.length()-2);
        	
        }
        
   
        else if(commandEls[0].equals( "get_number_of_nominations_for_movie" )){ 
        	String movie_title = commandEls[1];
        	String release_year = commandEls[2];
        	
        	Filter filter1 = new FilterPredicate("MOVIE_TITLE",FilterOperator.EQUAL,movie_title);
        	Filter filter2 = new FilterPredicate("MOVIE_YEAR",FilterOperator.EQUAL,release_year);
        	
        	Query q = new Query("NOMINATED").setFilter(CompositeFilterOperator.and(filter1,filter2));
        	PreparedQuery pq = datastore.prepare(q);
 
        	int i=0;
        	for(Entity result : pq.asIterable()){
        		i++;
        	}

    		results = ""+i;
  
        }
        
        else if(commandEls[0].equals( "get_average_rating_for_movie" )){ 
        	String movie_title = commandEls[1];
        	String release_year = commandEls[2];
        	
        	Filter filter1 = new FilterPredicate("MOVIE_TITLE",FilterOperator.EQUAL,movie_title);
        	Filter filter2 = new FilterPredicate("RELEASE_YEAR",FilterOperator.EQUAL,release_year);
        	
        	Query q = new Query("RATED_BY").setFilter(CompositeFilterOperator.and(filter1,filter2));
        	PreparedQuery pq = datastore.prepare(q);
 
        	int i = 0;
        	double sum = 0;
        	for(Entity result : pq.asIterable()){
        		i++;
        		sum += Double.parseDouble(result.getProperty("RATING").toString());
        	}
        	//System.out.println("sum:"+sum+" it:"+pq.countEntities()+""+i);
    		results = ""+ (Math.round(sum/i*10)/10.0);
  
        }
        else if(commandEls[0].equals( "get_average_rating_of_user" )){ 
        	String user_id = commandEls[1];

        	
        	Filter filter = new FilterPredicate("USER_ID",FilterOperator.EQUAL,user_id);

        	
        	Query q = new Query("RATED_BY").setFilter(filter);
        	PreparedQuery pq = datastore.prepare(q);
 
        	int i = 0;
        	double sum = 0;
        	for(Entity result : pq.asIterable()){
        		i++;
        		sum += Double.parseDouble(result.getProperty("RATING").toString());
        	}
   
    		results = ""+ (Math.round(sum/i*10)/10);
  
        }
        
      
        else if(commandEls[0].equals( "delete_company" )){ 
        	String company_name = commandEls[1];

        	Filter filter = new FilterPredicate("COMPANY",FilterOperator.EQUAL,company_name);
        	Query q = new Query("MOVIE").setFilter(filter);
        	PreparedQuery pq = datastore.prepare(q);
        	int i = 0;
        	for(Entity result : pq.asIterable()){
        		i++;
        	}
        	if(i == 0){
        		Key key = KeyFactory.createKey("PRODUCTION_COMPANY", company_name);
        		datastore.delete(key);
        		results = "Command executed successfully!";
        	}
        	else
        		results = "Referential integrity violation!";		
        }
        else if(commandEls[0].equals( "delete_user" )){ 
        	String user_id = commandEls[1];

        	Filter filter = new FilterPredicate("USER_ID",FilterOperator.EQUAL,user_id);
        	Query q = new Query("RATED_BY").setFilter(filter);
        	PreparedQuery pq = datastore.prepare(q);
        	int i = 0;
        	for(Entity result : pq.asIterable()){
        		i++;
        	}
        	if(i == 0){
        		Key key = KeyFactory.createKey("USER", user_id);
        		datastore.delete(key);
        		results = "Command executed successfully!";
        	}
        	else
        		results = "Referential integrity violation!";		
        }
        
        if(!test.output[ii].equals(results)){
			System.out.print("\n\nFailed test "+ii);
			System.out.print("\nInput:\n"+test.input[ii]);
			System.out.print("\nExpected:\n"+test.output[ii]);
			System.out.print("\nOutput:\n"+results);
		}
       
        
        resp.sendRedirect( "/movies.jsp?moviedbName=Purdue&display=" + results );
		}
        //System.out.println("success");
    }  

}
