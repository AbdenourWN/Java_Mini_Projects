package TP8.Examen;
 
class AgeException extends Exception{
    public AgeException(){
        super();
    }
    public void affiche(){
        System.out.println("Age doit etre superieur a 16");
    }
}

class IR{
    private static double[] tranches={6000,18000,36000,60000};
    private static double[] tauxIR={0.0,0.05,0.15,0.25,0.35};
    public static double getIR(double salaire){
        for(int i=0;i<4;i++){
            if(salaire<tranches[i]){
                return tauxIR[i];
            }
        }
        return 0.35;
    }
}
interface IEmploye{
    
    public int age();
    public int anciennete();
    public int anneeRetraite( int a );

}
class Employe implements IEmploye{
    protected static int inc=0;
    protected int mtle;
    protected String nom;
    protected int anneeNaissance;
    protected int anneeEmbauche;
    protected double salaireBase;
    public Employe(){
        mtle=inc++;
        nom="";
        anneeEmbauche=0;
        anneeNaissance=0;
        salaireBase=0;
    }
    public Employe(String nom,int anneeNaissance,int anneeEmbauche,double salaireBase) throws AgeException{
        inc++;
        mtle=inc;
        this.nom=nom;
        this.anneeNaissance=anneeNaissance;
        set_anneeEmbauche(anneeEmbauche);
        this.salaireBase=salaireBase;
    }
    public void set_anneeEmbauche(int a) throws AgeException{
        if(a-anneeNaissance<16){
            throw new AgeException();
        }
        else {
            anneeEmbauche=a;
        }

    }
    public void set_anneeNaissance(int a){
        anneeNaissance=a;
    }
    public int get_anneeNaissance(){
        return anneeNaissance;
    }
    public int get_anneEmbauche(){
        return anneeEmbauche;
    }

    public int age(){
        return 2023-anneeNaissance;
    }
    public int anciennete(){
        return 2023-anneeEmbauche;
    }
    public int anneeRetraite(int age_retraite){
        return anneeNaissance+age_retraite;
    }
    public double salaireAPayer(){
        return salaireBase*(1- IR.getIR(salaireBase));
    }
    public String toString(){
        return "Nom Employee: "+nom+"\nMatricule :"+mtle+"\nAnnee de Naissance: "+anneeNaissance+"\nAnnee de recrutement: "+anneeEmbauche+"\nSalaire de Base: "+salaireBase+"\nSalaireNet: "+salaireAPayer();
    }
}
class formateur extends Employe{
    private double heureSup;
    private double remunerationHSup;
    public formateur(){
        super();
        heureSup=0;
        remunerationHSup=0;
    }
    public formateur(String nom,int anneeNaissance,int anneeEmbauche,double salaireBase,double heureSup,double remunerationHSup) throws AgeException{
        super(nom, anneeNaissance, anneeEmbauche, salaireBase);
        this.heureSup=heureSup;
        this.remunerationHSup=remunerationHSup;
    }
    public double salaireAPayer(){
        return (super.salaireBase+heureSup*remunerationHSup)*(1-IR.getIR(super.salaireBase));
    }
    public String toString(){
        return "\nNom Formateur: "+nom+"\nMatricule :"+mtle+"\nAnnee de Naissance: "+anneeNaissance+"\nAnnee de recrutement: "+anneeEmbauche+"\nSalaire de Base: "+salaireBase+"\n SalaireNet: "+salaireAPayer()+"\nheure supplementaire par mois: "+heureSup+"\nremuneration par heure supplementaire: "+remunerationHSup;
    }
}

public class test {
    public static void main(String[] args) {
        try {
            Employe e1=new Employe("abdenour", 2002, 2019, 6500);
            formateur f1 =new formateur("boukhris", 2003, 2019, 2000, 60, 10);
            System.out.println(e1.toString());
            System.out.println(f1.toString());
        } catch (AgeException e) {
            e.affiche();
        }
        
        
        
        

    }
}
