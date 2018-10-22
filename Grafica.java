//a R b | a-b = 5q
class Grafica{
  public static void main(String[] args){
    int largo = 15;
    int alto = 15;
    System.out.print("   ");
    for (int i = -largo;i< largo ;i++ ) {
      if (i<-9) {
        System.out.print(i+" ");
      } else if (i<-1) {
        System.out.print(i+"  ");
      } else if (i<10) {
        System.out.print(i+"   ");
      } else {
        System.out.print(i+"  ");
      }
    }
    System.out.print("\n");
    for(int b = alto; b > -alto; b--){
      if(b > 9){
        System.out.print(" ");
      }else if (b > -1) {
        System.out.print("  ");
      } else if (b > -10) {
        System.out.print(" ");
      }
      System.out.print(b+" ");
      for(int a =-largo; a<largo;a++){
        if((a-b)%5==0){
          System.out.print("+   ");
        } else {
          System.out.print("    ");
        }
      }
      System.out.print("\n");
    }
  }
}
