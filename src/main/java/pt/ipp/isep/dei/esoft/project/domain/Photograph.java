package pt.ipp.isep.dei.esoft.project.domain;


import java.io.Serializable;

public class Photograph implements Serializable {

    private static final long serialVersionUID = 6215117796260559635L;

    private String photoLink;


    public Photograph(){


    }
    public Photograph(String photoLink){
        this.photoLink = photoLink;

    }

}
