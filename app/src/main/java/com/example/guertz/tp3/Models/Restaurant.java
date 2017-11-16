package com.example.guertz.tp3.Models;

/**
 * Created by Guijet on 2017-11-10.
 */

public class Restaurant {

    private int id,nbEtoiles;
    private String nom,adresse,qualiteBouffe,qualiteService;
    private float prixMoyen;

    public  Restaurant(int id,String nom,String adresse,String qualiteBouffe, String qualiteService,Float prixMoyen, int nbEtoiles){
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.nbEtoiles = nbEtoiles;
        this.qualiteBouffe = qualiteBouffe;
        this.qualiteService = qualiteService;
        this.prixMoyen = prixMoyen;
    }

    //region GET SECTION
    public String getAdresse() {
        return adresse;
    }

    public float getPrixMoyen() {
        return prixMoyen;
    }

    public int getId() {
        return id;
    }

    public int getNbEtoiles() {
        return nbEtoiles;
    }

    public String getNom() {
        return nom;
    }

    public String getQualiteBouffe() {
        return qualiteBouffe;
    }

    public String getQualiteService() {
        return qualiteService;
    }
    //endregion

    //region SET SECTION
    public void setId(int id) {
        this.id = id;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setNbEtoiles(int nbEtoiles) {
        this.nbEtoiles = nbEtoiles;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrixMoyen(float prixMoyen) {
        this.prixMoyen = prixMoyen;
    }

    public void setQualiteBouffe(String qualiteBouffe) {
        this.qualiteBouffe = qualiteBouffe;
    }

    public void setQualiteService(String qualiteService) {
        this.qualiteService = qualiteService;
    }
    //endregion
}
