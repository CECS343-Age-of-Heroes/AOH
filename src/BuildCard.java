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
	public BuildCard(String theType) {
		setName(theType);
		setType(theType);
	}
	public BuildCard(String a, String b){
		setName(a+" "+b);
		setType(b);
	}

}
