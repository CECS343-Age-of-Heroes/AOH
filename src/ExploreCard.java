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
		setType(theType);
	}
	public ExploreCard(String a,String b){
		setName(a+" "+b);
		setType(b);
	}

}
