public class OnCampusStudent extends Student{

    final public static int RESIDENT = 1;
    final public static int NON_RESIDENT = 2;
    private int mResident;
    private double mProgramFee;

    /**
     * Create an OnCampusStudent object and initialize pId, pFirstname, and pLastname
     */

    public OnCampusStudent(String pId, String pFirstName, String pLastName){
        super(pId, pFirstName, pLastName);
    }

    /**
     * OnCampusStudent implementation of calcTuition method
     */
    @Override
    public void calcTuition(){
        double t;

        if(getResidency() == 1){
            t = TuitionConstants.ONCAMP_RES_BASE;
        }
        else{
            t = TuitionConstants.ONCAMP_NONRES_BASE;
        }

        t = t + getProgramFee();

        if(getCredits() > TuitionConstants.ONCAMP_MAX_CREDITS){
            t = t + (getCredits() - TuitionConstants.ONCAMP_MAX_CREDITS) * (TuitionConstants.ONCAMP_ADD_CREDITS);
        }
        setTuition(t);
    }

    /** Accessor and Mutator methods for OnCampusStudent
     *
     */

    public double getProgramFee(){
        return mProgramFee;
    }

    public int getResidency(){
        return mResident;
    }

    public void setProgramFee(double pProgramFee){
        mProgramFee = pProgramFee;
    }

    public void setResidency(int pResident){
        mResident = pResident;
    }
}
