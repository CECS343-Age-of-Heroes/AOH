/*
 * GatherCard:
 * 
 */
/*
 * Add:
 * 	-
 * Fix:
 * 	-
 */
@SuppressWarnings("serial")
public class GatherCard extends ActionCard {

	// constructor
	public GatherCard(String theType) {
		setName(theType);
		setType(theType);
	}
	public GatherCard(String a, String b){
		setName(a+" "+b);
		setType(b);
	}

}
