package TP8;

import java.util.ArrayList;

abstract class compte{
    protected long Numero;
    protected String Nom;
    protected double solde;
    public compte(){
        solde=0;
        Nom="";
        Numero=0;
    }
    public compte(long Numero,String Nom,double solde){
        this.Numero=Numero;
        this.Nom=Nom;
        this.solde=solde;
    }
    public void set_Numero(long Numero){
        this.Numero=Numero;
    }
    public void set_Nom(String Nom){
        this.Nom=Nom;
    }
    public void set_solde(double solde){
        this.solde=solde;
    }
    public long get_Numero(){
    return Numero;
    }
    public String get_Nom(){
        return Nom;
    }
    public double get_solde(){
        return solde;
    }
    public abstract void debiter(long montant);
    public abstract void crediter(long montant);
}
class CompteSurCheque extends compte{
    private long NumeroCheque;
    private long NumeroCarte;
    public CompteSurCheque(){
        super();
        NumeroCheque=0;
        NumeroCarte=0;
    }
    public CompteSurCheque(long Numero,String Nom,double solde,long NumeroCheque,long NumeroCarte){
        super(Numero, Nom, solde);
        this.NumeroCarte=NumeroCarte;
        this.NumeroCheque=NumeroCheque;
    }
    public void set_NumeroCarte(long NumeroCarte){
        this.NumeroCarte=NumeroCarte;
    }
    public long get_NumeroCarte(){
        return NumeroCarte;
    }
    public void set_NumeroCheque(long NumeroCheque){
        this.NumeroCheque=NumeroCheque;
    }
    public long get_NumeroCheque(){
        return NumeroCheque;
    }
    public void crediter(long montant){
        super.solde+=montant;
    }
    public void debiter(long montant)throws IllegalArgumentException{
        if(montant>super.solde)throw new IllegalArgumentException("Solde insuffisant! ");
        else super.solde-=montant;
    }
}
class CompteSurCarnet extends compte{
    private long NumeroCarnet;
    public CompteSurCarnet(){
        super();
        NumeroCarnet=0;
    }
    public CompteSurCarnet(long Numero,String Nom,double solde,long NumeroCarnet){
        super(Numero, Nom, solde);
        this.NumeroCarnet=NumeroCarnet;
    }
    public void set_NumeroCarte(long NumeroCarnet){
        this.NumeroCarnet=NumeroCarnet;
    }
    public long get_NumeroCarnet(){
        return NumeroCarnet;
    }
    public void crediter(long montant){
        super.solde+=montant;
    }
    public void debiter(long montant)throws IllegalArgumentException{
        if(montant>10000)throw new IllegalArgumentException("Plafond dépassé");
        else if(montant>super.solde)throw new IllegalArgumentException("Solde insuffisant! "); 
        else super.solde-=montant;
    }

}
class banque{
    private ArrayList<compte> bank;
    public banque(){
        bank=new ArrayList<compte>();
    }
    public int Rechercher(long Numero){
        for(compte i:bank){
            if(i.get_Numero()==Numero)return bank.indexOf(i);
        }
        return -1;
    }
    public void AjouterCompteCarnet(CompteSurCarnet CompteCarnet){
        bank.add(CompteCarnet);
    }
    public void AjouterCompteCheque(CompteSurCheque CompteCheque){
        bank.add(CompteCheque);
    }
    public void Supprimer(long Numero){
        for (compte i:bank){
            if(i.get_Numero()==Numero){
                bank.remove(i);
            }
        }
    }
}
public class EX2 {
    public static void main(String[] args) {
        CompteSurCarnet compteCarnet =new CompteSurCarnet(123456789, "abdenour", 1500, 654321);
        CompteSurCheque compteCheque =new CompteSurCheque(987654321, "Boukhris", 2000, 12, 741185263);
        banque bank=new banque();
        bank.AjouterCompteCarnet(compteCarnet);
        bank.AjouterCompteCheque(compteCheque);
        compteCarnet.crediter(200);
        compteCheque.debiter(100);
        
    }
}
