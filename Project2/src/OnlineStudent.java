public class OnlineStudent extends Student{

    private boolean mTechFee;

    /**
     * create OnlineStudent Object and instantiate pId, pFirstName, pLastName
     */
    public OnlineStudent(String pId, String pFistName, String pLastName){
        super(pId, pFistName, pLastName);
    }

    /**
     * OnlineStudent implementation of calcTuition
     */
    @Override
    public void calcTuition(){
        double t = getCredits() * TuitionConstants.ONLINE_CREDIT_RATE;
        if(getTechFee()){
            t = t + TuitionConstants.ONLINE_TECH_FEE;
        }
        setTuition(t);
    }

    /**
     * Accessor and Mutator methods for OnlineStudent
     */

    public boolean getTechFee(){
        return mTechFee;
    }

    public void setTechFee(boolean pTechFee){
        mTechFee = pTechFee;
    }
}
