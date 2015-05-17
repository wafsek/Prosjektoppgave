package baminsurances.data.generation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import baminsurances.data.*;

/**
 * Provides methods for generating claim advices.
 * 
 * @author martin
 */
public class ClaimAdviceGenerator {
    
    /*
     * Map explanation:
     * 
     * The key in the outer map is the name of a folder, which contains all the
     * damage pictures for a given insurance type. They will be named 'car,
     * 'boat', etc. This key points to an inner map.
     * 
     * The key in the inner map is a type of damage, that fits with the
     * insurance type. Examples are (in Norwegian) 'brann' and 'kollisjon'.
     * This key points to a list of images, which are the damage pictures
     * related to the damage type.
     */
    private Map<String, HashMap<String, ArrayList<BufferedImage>>> pictures =
            new HashMap<String, HashMap<String, ArrayList<BufferedImage>>>();
    
    private static final String resourceFolderName = "claimadvice_images";
    
    /**
     * Creates a new claim advice generator.
     */
    public ClaimAdviceGenerator() {
        loadResources();
    }
    
    /**
     * Takes a subclass of {@link Insurance} and generates a claim advice for
     * this insurance type. The registration date of the insurance is used to
     * ensure that the claim advice occurs after this date.
     * 
     * @param type a subclass of {@link Insurance} 
     * @param insuranceDate the registration date of the insurance
     * @return a claim advice for the given insurance type
     */
    public ClaimAdvice generateClaimAdvice(Class<? extends Insurance> type,
            LocalDate insuranceDate) {
        ClaimAdvice ca = null;
        if (type.equals(CarInsurance.class)) {
            ca = generateCarClaimAdvice(insuranceDate);
        } else if (type.equals(BoatInsurance.class)) {
            ca = generateBoatClaimAdvice(insuranceDate);
        } else if (type.equals(HomeInsurance.class)) {
            ca = generateHomeClaimAdvice(insuranceDate);
        } else if (type.equals(HolidayHomeInsurance.class)) {
            ca = generateHolidayHomeClaimAdvice(insuranceDate);
        } else if (type.equals(TravelInsurance.class)) {
            ca = generateTravelClaimAdvice(insuranceDate);
        }
        return ca;
    }
    
    /*******************************************
     *                                         *
     *   Methods common to all claim advices   *
     *                                         *
     ******************************************/
    
    /**
     * Generates and returns a list with a number of witnesses between 0
     * (inclusive) and 5 (exclusive). 
     *  
     * @return a list with a number of witnesses between 0 (inclusive) and 5
     * (exclusive)
     */
    public List<Person> generateWitnesses() {
        int numWitnesses = (int) (Math.random() * 5);
        List<Person> witnesses = new LinkedList<>();
        for (int i = 0; i < numWitnesses; i++) {
            witnesses.add(new PersonGenerator().generatePerson());
        }
        return witnesses;
    }
    
    /**
     * Generates and returns an assessment amount between 500 (inclusive) and
     * 80 000 (exclusive).
     * 
     * @return an assessment amount between 500 (inclusive) and 80 000
     * (exclusive)
     */
    public int generateAssessmentAmount() {
        return (int) (Math.random() * 75000) + 5000;
    }
    
    /**
     * Generates and returns a compensation amount, based on a given
     * assessment amount.
     * <p>
     * The compensation amount will be 0, or a value between 20 (inclusive) and
     * 100 (inclusive). 
     * 
     * @param assessmentAmount the assessment amount
     * @return a compensation amount
     */
    public int generateCompensationAmount(int assessmentAmount) {
        int percent = 0;
        int x = (int) Math.random();
        if (x < 0.9) {
            percent = (int) (Math.random() * 81) + 20;
        }
        return assessmentAmount * percent / 100;
    }
    
    
    /***********************************************
     *                                             *
     *         Loading and utility methods         *
     *                                             *
     ***********************************************/
    
    /**
     * Loads all the resources necessary for generating claim advices, as
     * reading them for every generation would consume a large amount of
     * resources.
     */
    private void loadResources() {
        pictures.put("car", new HashMap<String, ArrayList<BufferedImage>>());
        pictures.put("boat", new HashMap<String, ArrayList<BufferedImage>>());
        pictures.put("home", new HashMap<String, ArrayList<BufferedImage>>());
        loadCarDamagePictures();
        loadBoatDamagePictures();
        loadHomeDamagePictures();
        // Holiday home insurances share pictures with home insurances, and
        // travel insurances have no pictures at all, which is why there are
        // no resources loaded for either.
    }
    
    /**
     * Loads the pictures used for car insurance claim advices.
     */
    private void loadCarDamagePictures() {
        String[] damageTypes = {"kollisjon", "hærverk", "totalskade",
                "naturforårsaket"};
        
        Map<String, ArrayList<BufferedImage>> carPictures =
                pictures.get("car");
                
        for (String damageType : damageTypes) {
            ArrayList<BufferedImage> pics =
                    getImagesInFolderWithName("car", damageType);
            carPictures.put(damageType,
                    pics);
        }
    }
    
    /**
     * Loads the pictures used for boat insurance claim advices.
     */
    private void loadBoatDamagePictures() {
        String[] damageTypes = {"hærverk", "brann", "kantring",
                "sammenstøt"};
        
        Map<String, ArrayList<BufferedImage>> boatPictures =
                pictures.get("boat");
                
        for (String damageType : damageTypes) {
            boatPictures.put(damageType,
                    getImagesInFolderWithName("boat", damageType));
        }
    }
    
    /**
     * Loads the pictures used for home insurance claim advices.
     */
    private void loadHomeDamagePictures() {
        String[] damageTypes = {"brann", "vannskade", "naturforårsaket"};
        
        Map<String, ArrayList<BufferedImage>> homePictures =
                pictures.get("boat");
        
        for (String damageType : damageTypes) {
            homePictures.put(damageType,
                    getImagesInFolderWithName("home", damageType));
        }
    }
    
    /**
     * Gets a list of images in the given folder, with a filename that contains
     * the given filename.
     * 
     * @param folderName the name of the folder
     * @param filename the name of the files 
     * @return a list of images
     */
    protected ArrayList<BufferedImage> getImagesInFolderWithName(String folderName,
            String filename) {
        File dir = new File(
                ClassLoader.getSystemClassLoader().getResource(".").getPath()
                + resourceFolderName +  "/" + folderName);
        
        File[] matches = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.contains(filename.toLowerCase());
            }
        });
        
        ArrayList<BufferedImage> pictures = new ArrayList<BufferedImage>();
        try {
            for (File img : matches) {
                pictures.add(ImageIO.read(img));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pictures;
    }
    
    /**
     * Takes a list of images, and randomly removes elements from it.
     * <p>
     * This method is used to whittle down the amount of images being stored,
     * as they can take up a lot of storage space.
     * 
     * @param images the list of images
     * @return a list containing all the elements from the given list, some of
     * them, or none
     */
    private List<BufferedImage> removeRandomPictures(List<BufferedImage> images) {
        Iterator<BufferedImage> iter = images.iterator();
        while (iter.hasNext()) {
            iter.next();
            
            // There's an 40% chance of the current picture being removed.
            if (Math.random() < 0.4) {
                iter.remove();
            }
        }
        return images;
    }
    
    /**
     * Swaps any Norwegian characters to an English one.
     * <p>
     * <ul>
     *  <li>'æ' becomes "ae"</li>
     *  <li>'ø' becomes 'o'</li>
     *  <li>'å' becomes 'a'</li>
     * </ul>
     * 
     * @param s the string to alter
     * @returns the given string with any Norwegian characters swapped
     */
    public String replaceNorwegianChars(String s) {
        return s.replace("æ", "ae").replace('ø', 'o').replace('å', 'a');
    }
    
    
    /***********************************************
     *                                             *
     *   Methods for car insurance claim advices   *
     *                                             *
     ***********************************************/
    
    /**
     * Generates and returns a claim advice for a car insurance.
     * 
     * @param insuranceDate the registration date of the insurance
     * @return a claim advice for a car insurance
     */
    public ClaimAdvice generateCarClaimAdvice(LocalDate insuranceDate) {
        String damageType = generateCarDamageType();
        int assessmentAmount = generateAssessmentAmount();
        ClaimAdvice ca =new ClaimAdvice(
                DateGenerator.generateDateAfter(insuranceDate),
                damageType,
                generateCarDamageDescription(damageType),
                assessmentAmount,
                generateCompensationAmount(assessmentAmount)
        );
        ca.getWitnesses().addAll(generateWitnesses());
        ca.addPicturesOfDamage(generateCarDamagePictures(damageType));
        return ca;
    }
    
    /**
     * Generates and returns a type of damage for a car insurance.
     * 
     * @return a type of damage for a car insurance
     */
    public String generateCarDamageType() {
        String[] types = {"Kollisjon", "Tyveri", "Hærverk", "Totalskade",
                "Påkjørsel", "Naturforårsaket"};
        return types[(int) (Math.random() * types.length)];
    }
    
    /**
     * Generates and returns a description of a car insurance damage.
     * 
     * @param damageType the type of damage
     * @return a description of a car insurance damage
     */
    public String generateCarDamageDescription(String damageType) {
        String description = null;
        switch (damageType) {
            case "Kollisjon":
                description = Math.random() < 0.5 ?
                    "Ble påkjørt bakfra i rundkjøring." :
                    "Frontkollisjon på motorveien.";
                break;
            case "Tyveri":
                description = Math.random() < 0.5 ? "Veske som lå i bilen ble "
                        + "tatt under innbrudd på parkeringsplass." :
                        "Alle fire dekk på bilen frastålet mens bilen stod i "
                        + "parkeringshus.";
                break;
            case "Hærverk":
                description = Math.random() < 0.5 ? "Bilen ble ramponert under "
                        + "opptøyer utenfor eiers hjem." : "Vindu knust mens "
                        + "eier var parkert utenfor matbutikk.";
                break;
            case "Totalskade":
                description = Math.random() < 0.5 ? "Bil totalvraket som følge "
                        + "av kollisjon med midtrabatten." : "Bil kjørte utfor "
                        + " stup og ble totalvraket.";
                break;
            case "Påkjørsel":
                description = "Bilen ble påkjørt mens den stod parkert på "
                        + "parkeringsplass.";
                break;
            case "Naturforårsaket":
                description = Math.random() < 0.5 ? "Tre falt ned på den "
                        + "parkerte bilen under tordenvær" : "Bilen tok skade "
                        + "under oversvømmelse.";
                break;
            default:
                description = "";
                break;
        }
        return description;
    }
    
    /**
     * Generates and returns a list with a number of car damage pictures
     * between 0 (inclusive) and 3 (exclusive). The pictures chosen are based
     * on the given damage type.
     * 
     * @param damageType the type of damage
     * @return a list with a number of car damage pictures between 0
     * (inclusive) and 3 (exclusive)
     */
    public List<BufferedImage> generateCarDamagePictures(String damageType) {
        ArrayList<BufferedImage> pics = pictures.get("car").get(
                replaceNorwegianChars(damageType.toLowerCase().trim()));
        if (pics == null) { // no pics available for the damage type
            return new ArrayList<BufferedImage>();
        }
        return removeRandomPictures(pics);
    }
    
    
    /************************************************
     *                                              *
     *   Methods for boat insurance claim advices   *
     *                                              *
     ***********************************************/
    
    /**
     * Generates and returns a claim advice for a boat insurance.
     * 
     * @param insuranceDate the registration date of the insurance
     * @return a claim advice for a boat insurance
     */
    public ClaimAdvice generateBoatClaimAdvice(LocalDate insuranceDate) {
        String damageType = generateBoatDamageType();
        int assessmentAmount = generateAssessmentAmount();
        ClaimAdvice ca = new ClaimAdvice(
                DateGenerator.generateDateAfter(insuranceDate),
                damageType,
                generateBoatDamageDescription(damageType),
                assessmentAmount,
                generateCompensationAmount(assessmentAmount)
        );
        ca.getWitnesses().addAll(generateWitnesses());
        ca.addPicturesOfDamage(generateBoatDamagePictures(damageType));
        return ca;
    }
    
    /**
     * Generates and returns a type of damage for a boat insurance.
     * 
     * @return a type of damage for a boat insurance
     */
    public String generateBoatDamageType() {
        String[] types = {"Sammenstøt", "Kantring", "Synking", "Brann",
                "Hærverk"};
        return types[(int) (Math.random() * types.length)];
    }
    
    /**
     * Generates and returns a description of a travel insurance damage.
     * 
     * @param damageType the type of damage
     * @return a description of a travel insurance damage
     */
    public String generateBoatDamageDescription(String damageType) {
        String description = null;
        switch (damageType) {
            case "Sammenstøt":
                description = Math.random() < 0.5 ? "Sammenstøt med annen båt "
                        + "på åpent hav." : "Sammenstøt med seilbåt i havn.";
                break;
            case "Kantring":
                description = "Båten kantret på et skjær.";
                break;
            case "Synking":
                description = "Båten sank etter å ha tatt inn for mye vann.";
                break;
            case "Brann":
                description = Math.random() < 0.5 ? "Båten brant til grunnen "
                        + " som følge av batterifeil." : "Båten tok "
                        + "brannskader etter å ha blitt tent på.";
                break;
            case "Hærverk":
                description = Math.random() < 0.5 ? "Tagging på skroget." :
                    "Noen hadde kastet maling på båten mens den lå til kai.";
                break;
            default:
                description = "";
                break;
        }
        return description;
    }
    
    /**
     * Generates and returns a list with a number of boat damage pictures
     * between 0 (inclusive) and 3 (exclusive). The pictures chosen are based
     * on the given damage type.
     * 
     * @param damageType the type of damage
     * @return a list with a number of pictures between 0 (inclusive) and 3
     * (exclusive)
     */
    public List<BufferedImage> generateBoatDamagePictures(String damageType) {
        ArrayList<BufferedImage> pics = pictures.get("boat").get(
                replaceNorwegianChars(damageType.toLowerCase().trim()));
        if (pics == null) { // no pics available for the damage type
            return new ArrayList<BufferedImage>();
        }
        return removeRandomPictures(pics);
    }
    
    
    /************************************************
     *                                              *
     *   Methods for home insurance claim advices   *
     *                                              *
     ***********************************************/
    
    /**
     * Generates and returns a claim advice for a home insurance.
     * 
     * @param insuranceDate the regisration date of the insuranec
     * @return a claim advice for a home insurance
     */
    public ClaimAdvice generateHomeClaimAdvice(LocalDate insuranceDate) {
        String damageType = generateHomeDamageType();
        int assessmentAmount = generateAssessmentAmount();
        ClaimAdvice ca = new ClaimAdvice(
                DateGenerator.generateDateAfter(insuranceDate),
                damageType,
                generateHomeDamageDescription(damageType),
                assessmentAmount,
                generateCompensationAmount(assessmentAmount)
        );
        ca.getWitnesses().addAll(generateWitnesses());
        ca.addPicturesOfDamage(generateHomeDamagePictures(damageType));
        return ca;
    }
    
    /**
     * Generates and returns a type of damage for a home insurance.
     * 
     * @return a type of damage for a home insurance
     */
    public String generateHomeDamageType() {
        String[] types = {"Brann", "Innbrudd", "Vannskade", "Naturforårsaket"};
        return types[(int) (Math.random() * types.length)];
    }
    
    /**
     * Generates and returns a damage description of a home insurance damage.
     * 
     * @param damageType the type of damage
     * @return a description of a home insurance damage
     */
    public String generateHomeDamageDescription(String damageType) {
        String description = null;
        switch (damageType) {
            case "Brann":
                description = Math.random() < 0.5 ? "Terrassen brant ned "
                        + "som følge av en ikke-slukket røyk ble liggende i "
                        + "blomsterbeddet." : "Kjøkkenet ødelagt etter brann. "
                        + "Brannen oppstod som følge av feil med ovnen.";
                break;
            case "Innbrudd":
                description = Math.random() < 0.5 ? "TV-er og flere PCer ble "
                        + "stjålet mens beboerne var på jobb." : "Smykker "
                        + "til stor verdi stålet.";
                break;
            case "Vannskade":
                description = Math.random() < 0.5 ? "Oversvømmelse i "
                        + "kjelleren." : "Doen flommet over, og resulterte i "
                        + "store skader på toalettet.";
                break;
            case "Naturforårsaket":
                description = Math.random() < 0.5 ? "Lynnedslag resulterte i "
                        + "skade på flere elektronikkapparater." : "Tre falt "
                        + "inn i huset under en storm.";
                break;
            default:
                description = "";
                break;
        }
        return description;
    }
    
    /**
     * Generates and returns a list with a number of home damage pictures
     * between 0 (inclusive) and 3 (exclusive). The pictures chosen are based
     * on the given damage type.
     * 
     * @param damageType the type of damage
     * @return a list with a number of home damage pictures between 0
     * (inclusive) and 3 (exclusive)
     */
    public List<BufferedImage> generateHomeDamagePictures(String damageType) {
        ArrayList<BufferedImage> pics = pictures.get("home").get(
                replaceNorwegianChars(damageType.toLowerCase().trim()));
        if (pics == null) { // no pics available for the damage type
            return new ArrayList<BufferedImage>();
        }
        return removeRandomPictures(pics);
    }
    
    
    /********************************************************
     *                                                      *
     *   Methods for holiday home insurance claim advices   *
     *                                                      *
     *******************************************************/
    
    /**
     * Generates a claim advice for a holiday home insurance.
     * 
     * @param insuranceDate the registration date of the insurance
     * @return a claim advice for a holiday home insurance
     */
    public ClaimAdvice generateHolidayHomeClaimAdvice(LocalDate insuranceDate) {
        String damageType = generateHolidayHomeDamageType();
        int assessmentAmount = generateAssessmentAmount();
        ClaimAdvice ca = new ClaimAdvice(
                DateGenerator.generateDateAfter(insuranceDate),
                damageType,
                generateHolidayHomeDamageDescription(damageType),
                assessmentAmount,
                generateCompensationAmount(assessmentAmount)
        );
        ca.getWitnesses().addAll(generateWitnesses());
        ca.addPicturesOfDamage(generateHolidayHomeDamagePictures(damageType));
        return ca;
    }
    
    /**
     * Generates and returns a damage type for a holiday home insurance.
     * 
     * @return a damage type for a holiday home insurance
     */
    public String generateHolidayHomeDamageType() {
        return generateHomeDamageType();
    }
    
    /**
     * Generates and returns a damage description of a holiday home insurance
     * damage.
     * 
     * @param damageType the type of damage
     * @return a damage description of a holiday home insurance damage
     */
    public String generateHolidayHomeDamageDescription(String damageType) {
        return generateHomeDamageDescription(damageType);
    }
    
    /**
     * Generates and returns a list with a number of holiday home damage 
     * pictures between 0 (inclusive) and 3 (exclusive). The pictures chosen
     * are based on the given damage type.
     * 
     * @param damageType the type of damage
     * @return a list with a number of holiday home damage pictures between 0
     * (inclusive) and 3 (exclusive).
     */
    public List<BufferedImage> generateHolidayHomeDamagePictures(
            String damageType) {
        return generateHomeDamagePictures(damageType);
    }
    
    
    /**************************************************
     *                                                *
     *   Methods for travel insurance claim advices   *
     *                                                *
     *************************************************/
    
    /**
     * Generates and returns a claim advice for a travel insurance.
     * 
     * @return a claim advice for a travel insurance
     */
    public ClaimAdvice generateTravelClaimAdvice(LocalDate date) {
        String damageType = generateTravelDamageType();
        int assessmentAmount = generateAssessmentAmount();
        ClaimAdvice ca = new ClaimAdvice(
                DateGenerator.generateDateAfter(date),
                damageType,
                generateTravelDamageDescription(damageType),
                assessmentAmount,
                generateCompensationAmount(assessmentAmount));
        ca.getWitnesses().addAll(generateWitnesses());
        ca.addPicturesOfDamage(generateTravelDamagePictures(damageType));
        return ca;
    }
    
    /**
     * Generates and returns a damage type for a travel insurance.
     * 
     * @return a damage type for a travel insurance
     */
    public String generateTravelDamageType() {
        String[] types = {"Sykdom", "Personskade", "Mistet bagasje",
                "Forsinkelse", "Rettshjelp"};
        return types[(int) (Math.random() * types.length)];
    }
    
    /**
     * Generates and returns a damage description of a travel insurance
     * damage.
     * 
     * @param damageType the type of damage
     * @return a damage description of a travel insurance damage
     */
    public String generateTravelDamageDescription(String damageType) {
        String description = null;
        switch (damageType) {
            case "Sykdom":
                description = Math.random() < 0.5 ? "En av de reisende ble syk "
                        + "som følge av matforgiftning." : "Flere av de "
                        + "reisende behøvde medisiner til influensa.";
                break;
            case "Personskade":
                description = Math.random() < 0.5 ? "En av de reisende brakk "
                        + "benet under en utflukt, og måtte tilbringe et døgn "
                        + "på sykehus." : "En av de reisende fikk "
                        + "hjernerystelse på skitur.";
                break;
            case "Mistet bagasje":
                description = Math.random() < 0.5 ? "Bagasjen til alle de "
                        + "reisende forsvant under hjemreisen." : "Suvenirer "
                        + "og smykker ble stjålet fra hotellrommet mens de "
                        + "reisende var ute.";
                break;
            case "Forsinkelse":
                description = Math.random() < 0.5 ? "Flyet på veien til "
                        + "reisemålet ble over 24 timer forsinket." : "Toget "
                        + " de reisende skulle ta ble forsinket, som "
                        + "resulterte i at de ikke rakk flyet de skulle ta.";
                break;
            case "Rettshjelp":
                description = Math.random() < 0.5 ? "De reisende hadde leid "
                        + "en bil, og kolliderte med en annen bil. De behøvde "
                        + "derfor rettshjelp." : "En av de reisende ble "
                        + "beskyldt for å ha ramponert hotellrommet sitt, og "
                        + "behøvde derfor rettshjelp.";
                break;
            default:
                description = "";
                break;
        }
        return description;
    }
    
    /**
     * At this time, we have no pictures to use when generating claim advices
     * for travel insurances. Therefore, this method will always return an
     * empty list.
     * <p> 
     * The single parameter would be used to chose the appropriate
     * pictures, if there were any.
     * 
     * @param damageType the type of damage
     * @return an empty list
     */
    public List<BufferedImage> generateTravelDamagePictures(String damageType) {
        return new LinkedList<BufferedImage>();
    }
}
