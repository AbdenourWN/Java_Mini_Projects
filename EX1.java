package TP8;
interface Drawable {
    public void setColor( int couleur) ;
    public int getColor() ;
    public void affiche() ; 
}
interface Moveable{
    void translate(int dx,int dy);
}
class Point implements Moveable{
    private int x,y;
    public Point(){
        x=0;
        y=0;
    }
    public Point(int x,int y){
        this.x=x;
        this.y=y;
    }
    public int get_x(){
        return x;
    }
    public int get_y(){
        return y;
    }
    public void set_x(int x){
        this.x=x;
    }
    public void set_y(int y){
        this.y=y;
    }
    public void translate(int dx,int dy){
        x+=dx;
        y+=dy;
    }
    public String toString(){
        return "Point: ("+x+","+y+")";
    }

}
class Segment implements Drawable,Moveable{
    private Point endPoint1,endPoint2;
    private int couleur;
    public Segment(Point endPoint1,Point endPoint2,int couleur){
        this.endPoint1=endPoint1;
        this.endPoint2=endPoint2;
        this.couleur=couleur;
    }
    public Point get_endpoint1(){
        return endPoint1;
    }
    public Point get_endpoint2(){
        return endPoint2;
    }
    public int getColor(){
        return couleur;
    }
    public void set_endpoint1(Point endPoint1 ){
        this.endPoint1=endPoint1;
    }
    public void set_endpoint2(Point endPoint2 ){
        this.endPoint2=endPoint2;

    }
    public void setColor(int couleur ){
        this.couleur=couleur;
    }
    public String toString(){
        return "Segment: ("+endPoint1.toString()+","+endPoint2.toString()+")\n";
    }
    public void affiche(){
        System.out.println(toString()+"Couleur: "+couleur);
    }
    public void translate(int dx,int dy){
        endPoint1.translate(dx, dy);
        endPoint2.translate(dx, dy);
    }
}
class Triangle implements Drawable,Moveable{
    private Segment segment1 ; 
    private Segment segment2 ; 
    private Segment segment3 ; 
    private int couleur ;
    public Triangle(Point endPoint1,Point endPoint2,Point endPoint3,int couleur){
        segment1=new Segment(endPoint1, endPoint2, couleur);
        segment2=new Segment(endPoint2, endPoint3, couleur);
        segment3=new Segment(endPoint1, endPoint3, couleur);
        this.couleur=couleur;
    }
    public Segment get_segment1(){
        return segment1;
    }
    public Segment get_segment2(){
        return segment2;
    }
    public Segment get_segment3(){
        return segment3;
    }
    public void set_segment1(Segment segment1 ){
        this.segment1=segment1;
    }
    public void set_segment2(Segment segment2 ){
        this.segment2=segment2;
    }
    public void set_segment3(Segment segment3 ){
        this.segment3=segment3;
    }
    public int getColor(){
        return couleur;
    }
    public void setColor(int couleur ){
        this.couleur=couleur;
    }   
    public void translate(int dx,int dy){
        segment1.translate(dx, dy);
        segment3.get_endpoint2().translate(dx, dy);;
        
    }
    public String toString(){
        return "\nTriangle: [\n"+segment1.toString()+segment2.toString()+segment3.toString()+"]\n";
    }
    public void affiche(){
        System.out.println(toString()+"Couleur: "+couleur);
    }

}
    
public class EX1{
    public static void main(String[] args) {
        Point a=new Point(0, 2);
        Point b=new Point(0, 0);
        Point c=new Point(2, 0);
        Point k=new Point(3, 2);
        Point q=new Point(2, 3);
        Segment s1=new Segment(k, q, 24);
        Triangle t=new Triangle(a, b, c, 12);
        System.out.println(a.toString()+"\n");
        s1.affiche();
        t.affiche();
        s1.translate(1, 1);
        t.translate(2, 2);
        s1.affiche();
        t.affiche();


    }
}