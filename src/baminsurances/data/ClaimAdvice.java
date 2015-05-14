package baminsurances.data;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/**
 * A class representing a claim advice in the company's data bank. It contains
 * information about the claim advice itself, as well as witnesses, if any.
 * 
 * @author Martin Jackson
 */
public class ClaimAdvice implements Serializable {
    private static final long serialVersionUID = -7432562192214939768L;
    private static int nextDamageNo = 1;
    private int damageNo;
    private LocalDate dateOfDamage;
    private String damageType;
    private String damageDescription;
    private List<Person> witnesses = new ArrayList<>();
    private int numPictures = 0;
    private long assessmentAmount;
    private long compensationAmount;
    
    /**
     * Creates a new claim advice with the given values.
     * <p>
     * To add pictures and witnesses, instantiate a ClaimAdvice before using
     * the {@link #addWitness(Person) addWitness} and {@link
     * #addPictureOfDamage(BufferedImage) addPictureOfDamage} methods.
     * 
     * @param dateOfDamage the date of the damage
     * @param damageType the type of damage (crash, fire, etc.)
     * @param damageDescription description of the damage
     * @param assessmentAmount the assessment amount
     * @param compensationAmount the compensation amount
     * @throws NullPointerException if any of the arguments are null
     */
    public ClaimAdvice(LocalDate dateOfDamage, String damageType,
            String damageDescription, long assessmentAmount,
            long compensationAmount) {
        damageNo = nextDamageNo++;
        setDateOfDamage(dateOfDamage);
        setDamageType(damageType);
        setDamageDescription(damageDescription);
        this.assessmentAmount = assessmentAmount;
        this.compensationAmount = compensationAmount;
    }

    /**
     * Returns the next damage number to be assigned to a claim advice.
     * 
     * @return the next damage number to be assigned to a claim advice
     */
    public static int getNextDamageNo() {
        return nextDamageNo;
    }

    /**
     * Sets the next damage number to be the given number.
     * 
     * @param nextDamageNo the new next damage number
     */
    public static void setNextDamageNo(int nextDamageNo) {
        ClaimAdvice.nextDamageNo = nextDamageNo;
    }

    /**
     * Returns the damage number.
     * 
     * @return the damage number
     */
    public int getDamageNo() {
        return damageNo;
    }

    /**
     * Returns the date of the damage.
     * 
     * @return the date of the damage
     */
    public LocalDate getDateOfDamage() {
        return dateOfDamage;
    }

    /**
     * Sets the date of the damage to the given value.
     * 
     * @param dateOfDamage the new date of the damage
     * @throws NullPointerException if argument is null
     */
    public void setDateOfDamage(LocalDate dateOfDamage) {
        if (dateOfDamage == null) {
            throw new NullPointerException("Date of damage cannot be null.");
        }
        this.dateOfDamage = dateOfDamage;
    }

    /**
     * Returns the damage type.
     * 
     * @return the damage type
     */
    public String getDamageType() {
        return damageType;
    }

    /**
     * Sets the damage type to the given string.
     * 
     * @param damageType the new damage type
     * @throws NullPointerException if argument is null
     */
    public void setDamageType(String damageType) {
        if (damageType == null) {
            throw new NullPointerException("Damage type cannot be null.");
        }
        this.damageType = damageType;
    }

    /**
     * Returns a description of the damage.
     * 
     * @return a description of the damage
     */
    public String getDamageDescription() {
        return damageDescription;
    }

    /**
     * Sets the description of the damage to the given string.
     * 
     * @param damageDescription the new damage description
     * @throws NullPointerException if argument is null
     */
    public void setDamageDescription(String damageDescription) {
        this.damageDescription = damageDescription;
    }

    /**
     * Returns a list containing pictures of the damage.
     * 
     * @return a list containing pictures of the damage
     */
    public List<BufferedImage> getPicturesOfDamage() {
        File dir = new File(
                ClassLoader.getSystemClassLoader().getResource(".").getPath()
                + "claimadvices");
        
        // Getting an array of all files in /claimadvices
        // with name matching the pattern 'damageNo_xxx'
        File[] files = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.matches(
                        String.valueOf(damageNo) + "_[0-9]+");
            }
        });
        
        List<BufferedImage> pictures = new ArrayList<>();
        try {
            for (File img : files) {
                pictures.add(ImageIO.read(img));
            }
            return pictures;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Adds the given picture to the list containing pictures of the damage.
     * 
     * @param picture the picture to add
     */
    public void addPictureOfDamage(BufferedImage picture) {
        String filename = String.valueOf(damageNo) + "_" +
                String.valueOf(++numPictures);
        try {
            File dir = new File(
                    ClassLoader.getSystemClassLoader().getResource(".").getPath()
                    + "claimadvices");
            
            dir.mkdir(); // directory will be made if it does not already exist
            
            ImageIO.write(picture, "png", new File(dir.getPath() + "/" + filename + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Adds the given pictures to this claim advice.
     * 
     * @param pictures a list of pictures
     */
    public void addPicturesOfDamage(List<BufferedImage> pictures) {
        for (BufferedImage img : pictures) {
            addPictureOfDamage(img);
        }
    }

    /**
     * Returns a list of witnesses for the damage.
     * 
     * @return a list of witnesses for the damage
     */
    public List<Person> getWitnesses() {
        return witnesses;
    }

    /**
     * Adds a person to the list of witnesses for the damage.
     * 
     * @param witnesses the person to add
     * @throws NullPointerException if argument is null
     */
    public void addWitness(Person witness) {
        if (witness == null) {
            throw new NullPointerException("Witness cannot be null.");
        }
        witnesses.add(witness);
    }

    /**
     * Returns the assessment amount for this damage.
     * 
     * @return the assessment amount for this damage
     */
    public long getAssessmentAmount() {
        return assessmentAmount;
    }

    /**
     * Sets the assessment amount for this damage to be the given value
     * 
     * @param assessmentAmount the new assesssment amount
     */
    public void setAssessmentAmount(long assessmentAmount) {
        this.assessmentAmount = assessmentAmount;
    }

    /**
     * Returns the compensation amount for this damage.
     * 
     * @return the compensation amount for this damage
     */
    public long getCompensationAmount() {
        return compensationAmount;
    }

    /**
     * Sets the compensation amount for this damage to be the given value.
     * 
     * @param compensationAmount the new compensation amount
     */
    public void setCompensationAmount(long compensationAmount) {
        this.compensationAmount = compensationAmount;
    }
    
    @Override
    public String toString() {
        return damageNo + ", "
                + damageType + ", "
                + "Vitner: " + witnesses.size() + ", "
                + "Bilder: " + numPictures + ", "
                + "Taksering: " + assessmentAmount + ", "
                + "Erstatning: " + compensationAmount;
    }
}
