package park.tools;

import park.dal.*;
import park.model.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Date;

public class inserter {

    public static void main(String[] args) throws SQLException {
        UsersDao usersDao = UsersDao.getInstance();
        NationalParksDao nationalParksDao = NationalParksDao.getInstance();
        RecommendedRestaurantDao recommendedRestaurantDao = RecommendedRestaurantDao.getInstance();
        RecommendedHotelDao recommendedHotelDao = RecommendedHotelDao.getInstance();
        VisitsDao visitsDao = VisitsDao.getInstance();
        TrailsDao trailsDao = TrailsDao.getInstance();
        ReviewsDao reviewsDao = ReviewsDao.getInstance();
        SpeciesDao speciesDao = SpeciesDao.getInstance();
        PointOfInterestDao pointOfInterestDao = PointOfInterestDao.getInstance();
        PointOfInterestReviewsDao pointOfInterestReviewDao = PointOfInterestReviewsDao.getInstance();


        // user insert
        Users user1 = new Users("un1","pw1","fn1","ln1","e1","pn1", Users.UserType.travel);
        user1 = usersDao.create(user1);
        Users user2 = new Users("un2","pw2","fn2","ln2","e2","pn2", Users.UserType.travel);
        user2 = usersDao.create(user2);

        // park insert
        NationalParks park1 = new NationalParks("BADL",17,"BadLand national Park",76519, new BigDecimal(38.6800), new BigDecimal(-109.5700),true,"Moab","UT","84532","GOOD PLACE");
        park1 = nationalParksDao.create(park1);
        NationalParks park2 = new NationalParks("parkid",12,"pn",10, new BigDecimal(10), new BigDecimal(-20),true,"city","state","zip","BAD PLACE");
        park2 = nationalParksDao.create(park2);

        // visit insert
        Date date1 = new Date();
        Date date2 = new Date();
        Visits visit1 = new Visits(111, date1, 5, "un1", "BADL");
        visit1 = visitsDao.create(visit1);
        Visits visit2 = new Visits(222, date2, 7, "un2", "parkid");
        visit2 = visitsDao.create(visit2);

        // restaurant and hotel insert
        RecommendedRestaurant restaurant1 = new RecommendedRestaurant(1,"Restaurant1", "2061234567",(float) 4.5,"BADL");
	    restaurant1 = recommendedRestaurantDao.create(restaurant1);
	    RecommendedRestaurant restaurant2 = new RecommendedRestaurant(2,"Restaurant2", "3061234567",(float) 4.5,"BADL");
	    restaurant2 = recommendedRestaurantDao.create(restaurant2);
	    RecommendedHotel hotel1 = new RecommendedHotel(1,"Hotel1", "4256668888",(float) 5.0,"BADL");
	    hotel1 = recommendedHotelDao.create(hotel1);
	    RecommendedHotel hotel2 = new RecommendedHotel(2,"Hotel2", "42567778888",(float) 5.0,"BADL");
	    hotel2 = recommendedHotelDao.create(hotel2);

	    // trail insert
        Trails trails1 = new Trails("a", "BADL", "location1",
            "popularity1", 9800.0f, 1.1f, 1, "type", "feature1", "activity1");
        trails1 = trailsDao.create(trails1);
        Trails trails2 = new Trails("a", "parkid", "location1",
          "popularity1", 9800.0f, 1.1f, 1, "type", "feature1", "activity1");
        trails2 = trailsDao.create(trails2);

        // Reviews insert
        Date date = new Date();
        Reviews rev1 = new Reviews(date, "Review1", 5, user1, park1);
        rev1 = reviewsDao.create(rev1);
        Reviews rev2 = new Reviews(date, "Review2", 5, user2, park2);
        rev2 = reviewsDao.create(rev2);

        // Species insert
        Species spe1 = new Species("ACAD-1000", park1, "Moose", "Mammal", "Cervidae", "Present", "Native");
        spe1 = speciesDao.create(spe1);
        Species spe2 = new Species("ACAD-1001", park2, "orthern White-Tailed Deer, Virginia Deer, White-Tailed Deer"
                , "Mammal", "Cervidae", "Present", "Native");
        spe2 = speciesDao.create(spe2);

        // PointOfInterest insert
        PointOfInterest point1 = new PointOfInterest("point1", "red", "natural", park1, true, "description1", "ranger");
        point1 = pointOfInterestDao.create(point1);
        PointOfInterest point2 = new PointOfInterest("point2", "blue", "building", park2, false, "description2", "volunteer");
        point2 = pointOfInterestDao.create(point2);

        // PointOfInterestReview inert
        PointOfInterestReviews pointReview1 = new PointOfInterestReviews("name1", date, "content1", 5, park1, user1, point1);
        pointReview1 = pointOfInterestReviewDao.create(pointReview1);
        PointOfInterestReviews pointReview2 = new PointOfInterestReviews("name2", date, "content2", 4, park2, user2, point2);
        pointReview2 = pointOfInterestReviewDao.create(pointReview2);
        
        // read
        Users user = usersDao.getUserFromUserName("un1");
		System.out.format("Reading user: username:%s password:%s firstname:%s lastname:%s email:%s phone:%s, userType:%s\n",
				user.getUserName(), user.getPassWord(), user.getFirstName(),
				user.getLastName(), user.getEmail(), user.getPhoneNumber(), user.getUserType().name());
		
		NationalParks park11 = nationalParksDao.getParkById("BADL");
		System.out.format("looping park located in BADL: parkId:%s, ranking:%d, name:%s,"
				+ "acres:%d, latitude:%f, longitude:%f, active:%s "
				+ "city:%s, state:%s, zip:%s, description:%s \n",
				park11.getParkId(), park11.getRanking(), park11.getParkName(), park11.getAcres(), park11.getLatitude(), park11.getLongitude(),
				park11.isActive(), park11.getCity(), park11.getState(), park11.getZip(), park11.getDescription());
		
        
		List<NationalParks> parksList = nationalParksDao.getParksByState("UT");
		for (NationalParks park : parksList) {
			System.out.format("looping park located in UT: parkId:%s, ranking:%d, name:%s,"
					+ "acres:%d, latitude:%f, longitude:%f, active:%s "
					+ "city:%s, state:%s, zip:%s, description:%s \n",
					park.getParkId(), park.getRanking(), park.getParkName(), park.getAcres(), park.getLatitude(), park.getLongitude(),
					park.isActive(), park.getCity(), park.getState(), park.getZip(), park.getDescription());
		}

        Visits visit = visitsDao.getVisitById(111);
        System.out.format("Reading visit: visitsId:%s visitTime:%s travelPeriod:%s userName:%s parkId:%s\n",
                visit.getVisitsId(), visit.getVisitTime(), visit.getTravelPeriod(),
                visit.getUserName(), visit.getParkId());

        Trails trails = trailsDao.getTrailsFromTrailID(1);
        System.out.format("Reading trail: %s\n", trails.getParkId());
        
        List<Trails> trailsList = trailsDao.getTrailsFromParkID("BADL");
        for(Trails trail: trailsList) {
          System.out.format("looping trails whose parkId is name1: %s\n", trail.toString());
        }

        // Reviews Read
        Reviews review1 = reviewsDao.getReviewById(1);
        System.out.format("Reading review by id1: ReviewId:%s, CreatedTime:%s, WrittenReview:%s, Rating:%s, UserName:%s, ParkId:%s \n", review1.getReviewId(), review1.getCreatedTime(),
                review1.getWrittenReview(), review1.getRating(), review1.getUser().getUserName(), review1.getPark().getParkId());

        List<Reviews> revList1 = reviewsDao.getReviewsByUserName("un1");
        for (Reviews rev : revList1) {
            System.out.format("Looping reviews by username un1: ReviewId:%s, CreatedTime:%s, WrittenReview:%s, Rating:%s, UserName:%s, ParkId:%s \n", rev.getReviewId(), rev.getCreatedTime(),
                    rev.getWrittenReview(), rev.getRating(), rev.getUser().getUserName(), rev.getPark().getParkId());
        }

        List<Reviews> revList2 = reviewsDao.getReviewsByParkId("BADL");
        for (Reviews rev : revList2) {
            System.out.format("Looping reviews by ParkId BADL: ReviewId:%s, CreatedTime:%s, WrittenReview:%s, Rating:%s, UserName:%s, ParkId:%s \n", rev.getReviewId(), rev.getCreatedTime(),
                    rev.getWrittenReview(), rev.getRating(), rev.getUser().getUserName(), rev.getPark().getParkId());
        }

       
        
        List<RecommendedRestaurant> rList = recommendedRestaurantDao.getRestaurantByParkId("MORA");
	    for(RecommendedRestaurant r : rList) {
	    	System.out.format("Loop recommendedRestaurant by parkId, r%d resName:%s p:%s r:%f p:%s \n",
	    			r.getRestaurantId(),r.getRestaurantName(),r.getPhone(),r.getRating(),r.getParkId());
	    }

	    List<RecommendedRestaurant> rList2 = recommendedRestaurantDao.getRestaurantByRating((float) 4.5);
	    
	    for(RecommendedRestaurant r : rList2) {
	    	System.out.format("Loop recommendedRestaurant by rating, resId:%d resName:%s p:%s r:%f p:%s \n",
	    			r.getRestaurantId(),r.getRestaurantName(),r.getPhone(),r.getRating(),r.getParkId());
	    	
	    }

	    List<RecommendedHotel> hList = recommendedHotelDao.getHotelByParkId("ARCH");
	    for(RecommendedHotel h : hList) {
	    	System.out.format("Looping recommendedHotel by parkId, hId:%d hName:%s p:%s r:%f p:%s \n",
	    			h.getHotelId(),h.getHotelName(),h.getPhone(),h.getRating(),h.getParkId());
	    }

	    List<RecommendedHotel> hList1 = recommendedHotelDao.getHotelByRating((float) 5.0);
	    for(RecommendedHotel h : hList1) {
	    	System.out.format("Looping recommendedHotel by parkId, hId:%d hName:%s p:%s r:%f p:%s \n",
	    			h.getHotelId(),h.getHotelName(),h.getPhone(),h.getRating(),h.getParkId());
	    }
	    
	    // Species Read
        Species species1 = speciesDao.getSpeciesById("ACAD-1000");
        System.out.format("Reading species by speciesId ACAD-1000: SpeciesId:%s, ParkId:%s, CommonName:%s, Category:%s,"
                        + "Family:%s, Occurence:%s, Nativeness:%s \n", species1.getSpeciesId(), species1.getPark().getParkId(), species1.getCommonName(),
                species1.getCategory(), species1.getFamily(), species1.getOccurence(), species1.getNativeness());
        
        List<Species> speList = speciesDao.getSpeciesByParkId("BADL");
        for (Species spe : speList) {
            System.out.format("Looping species by ParkId BADL: SpeciesId:%s, ParkId:%s, CommonName:%s, Category:%s,"
                            + "Family:%s, Occurence:%s, Nativeness:%s \n", spe.getSpeciesId(), spe.getPark().getParkId(), spe.getCommonName(),
                    spe.getCategory(), spe.getFamily(), spe.getOccurence(), spe.getNativeness());
        }
        

//        // update
        
        nationalParksDao.updateDescription(park2,"not bad");
//        // delete
//        usersDao.delete(user1);
//        nationalParksDao.delete(park2);
//        visitsDao.delete(visit1);
//        trailsDao.delete(trails1);
//
//
//        //Review Delete
//
//        Reviews deletedReview = reviewsDao.delete(rev1);
//        if (deletedReview == null) {
//            System.out.println("Review 1 has been deleted");
//        } else {
//            System.out.format("Fail to delete review 1: ReviewId:%s, CreatedTime:%s, WrittenReview:%s, Rating:%s, UserName:%s, ParkId:%s \n", rev1.getReviewId(), rev1.getCreatedTime(),
//                    rev1.getWrittenReview(), rev1.getRating(), rev1.getUser().getUserName(), rev1.getPark().getParkId());
//        }
//
//        // Species Delete
//        Species deletedSpecies = speciesDao.delete(spe1);
//        if (deletedSpecies == null) {
//            System.out.println("spe1 has been deleted");
//        } else {
//            System.out.format("fail to delete spe1: SpeciesId:%s, ParkId:%s, CommonName:%s, Category:%s,"
//                            + "Family:%s, Occurence:%s, Nativeness:%s", deletedSpecies.getSpeciesId(), deletedSpecies.getPark().getParkId(), deletedSpecies.getCommonName(),
//                    deletedSpecies.getCategory(), deletedSpecies.getFamily(), deletedSpecies.getOccurence(), deletedSpecies.getNativeness());
//        }
    }
}
