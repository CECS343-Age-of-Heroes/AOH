/*
 * ExploreCard:
 * 
 */
/*
 * Add:
 * 	-
 * Fix:
 * 	-
 */
@SuppressWarnings("serial")
public class ExploreCard extends ActionCard {

	// constructor
	public ExploreCard(String theType) {
		setName(theType);
		setType("random");
	}
	public ExploreCard(String a,String b){
		setName(a+" "+b);
		setType("god");
	}

}
