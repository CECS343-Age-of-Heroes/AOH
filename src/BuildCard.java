/*
 * BuildCard:
 * 
 */
/*
 * Add:
 * 	-
 * Fix:
 * 	-
 */
@SuppressWarnings("serial")
public class BuildCard extends ActionCard {

	// constructor
	public BuildCard(String theName) {
		setName(theName);
		setType("random");
	}
	public BuildCard(String a, String b){
		setName(a+" "+b);
		setType("god");
	}

}
