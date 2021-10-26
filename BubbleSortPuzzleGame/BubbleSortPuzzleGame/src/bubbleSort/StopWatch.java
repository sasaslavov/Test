package bubbleSort;

/**
 * Simulacija ötoperice.<br>
 * Omogu?ava da se izmeri trajanje jednog pojedina?nog procesa, ali i ukupno trajanje ve?eg broja iteracija.<br>
 * Metode klase vra?aju teku?i objekat, kako bi se omogu?io "method chaining".
 */
public class StopWatch {
   /**
    * Format tipskog izveötaja
    */
   private static final String REPORT_FORMAT = "Process [%s] lasted %.3f second(s)";
   
   /**
    * Naziv procesa ?ije trajanje merimo.<br>
    * Smisao ovog atributa je da nam (u situaciji kada simultano merimo trajanje ve?eg broja procedura) omogu?i razlikovanje procesa "po njihovom imenu".
    */
   private String processName;
   
   /**
    * Trenutak u kome zapo?inje merenje vremena
    */
   private long start;
   
   /**
    * Trenutak u kome se zavröava merenje vremena
    */
   private long end;
   
   /**
    * Ukupno trajanje svih merenja procesa
    */
   private long sumOfDurations = 0;
   
   /**
    * Kreiranje procesa sa datim imenom
    * @param processName Ime procesa (da bismo ga, po imenu, razlikovati od drugih simultanih procesa)
    */
   public StopWatch(String processName) {
      this.processName = processName;
   }
   
   /**
    * Kreiranje procesa (u situaciji kada ime procesa nije vaéno).
    */
   public StopWatch() {
      this("");
   }
   
   /**
    * Zapo?inje merenje vremena
    */
   public StopWatch start() {
      start = System.currentTimeMillis();
      end();
      return this;
   }
   
   /**
    * Zaustavlja merenje vremena
    */
   public StopWatch end() {
      end = System.currentTimeMillis();
      return this;
   }
   
   /**
    * Zaustavlja merenje vremena i dodaje taj period na ukupno vreme trajanja. 
    */
   public StopWatch add() {
      end();
      sumOfDurations += end-start; 
      return this;
   }

   /**
    * Resetovanje ukupnog trajanja 
    */
   public StopWatch reset() {
      sumOfDurations = 0;
      return this;
   }
   
   /**
    * @return Vreme trajanja poslednjeg merenja
    */
   public long getDuration() {
      return end-start;
   }
   
   /**
    * @return Ukupno vreme trajanja
    */
   public long getSumOfDurations() {
      return sumOfDurations;
   }
   
   /**
    * @return Tipski izveötaj o trajanju poslednjeg merenja
    */
   public String report() {
      return String.format(REPORT_FORMAT, processName, getDuration()/1000.0);
   }
   
   /**
    * @return Tipski izveötaj o ukupnom trajanju
    */
   public String reportDuration() {
      return String.format(REPORT_FORMAT, processName, getSumOfDurations()/1000.0);
   }
}
