import Mp3.*;
import java.lang.*;
import java.util.Scanner;

public class Client {
    public static void main(String [] args)
    {

      int status=0;
      Ice.Communicator ic = null;
      try{
         ic = Ice.Util.initialize(args);
      	 Ice.ObjectPrx base =  ic.stringToProxy("SimplePrinter:default -p 10000 -h 192.168.1.53");
         if(base == null){
            	   throw  new RuntimeException("cannot create Proxy");
         }
      	 LecteurMp3Prx lecteurMp3 = LecteurMp3PrxHelper.checkedCast(base);
      	 if (lecteurMp3 == null)
         {
          	throw new Error("Invalid proxy");
         }
         Scanner sc = new Scanner(System.in);
         while(true){
               System.out.println("Voulez vous jouer un morceau o/n____#");
          	   str = sc.nextLine();
          	   if(str.charAt(0)=='o' || str.charAt(0)=='O')
          	   {
          	     System.out.println("choisir parmis la liste ___#");
          	     String str1 = sc.nextLine();
          	     System.out.println("Streaming: " + lecteurMp3.jouer());
          	     Runtime rt = Runtime.getRuntime();
          	     Process pr = rt.exec("cvlc http://localhost:8080");
          	   }
         }
      }catch(Ice.LocalException e){
            e.printStackTrace();
          	status = 1;
      }catch(Exception e){
          	System.err.println(e.getMessage());
            status = 1;
      }

      if (ic != null){
           try{
              ic.destroy();
           }catch(Exception e){
             System.err.println(e.getMessage());
    	       status = 1;
           }
      }
    	System.exit(status);
    }
}
