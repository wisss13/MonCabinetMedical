package android.exemple.com.moncabinetmedical.model;

/**
 * Created by lenovo on 08/11/2015.
 */

public class WrapObjToNetwork {
    private Contact contact;
    private String method;
    private boolean isNewer;
    private String term;


    public WrapObjToNetwork(Contact contact, String method, boolean isNewer) {
        this.contact = contact;
        this.method = method;
        this.isNewer = isNewer;
    }
    public WrapObjToNetwork(Contact contact, String method, String term) {
        this.contact = contact;
        this.method = method;
        this.term = term;
    }
    public WrapObjToNetwork(Contact contact, String method) {
        this.contact = contact;
        this.method = method;
    }


    public Contact getCar() {
        return contact;
    }

    public void setCar(Contact contact) {
        this.contact = contact;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public boolean isNewer() {
        return isNewer;
    }

    public void setIsNewer(boolean isNewer) {
        this.isNewer = isNewer;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

}

