/**
 * A class for controlling a set of Anim instances
 *
 * @author    Dr Steve Maddock
 * @version   1.0 (21/11/2013)
 */
  
public class AnimationScene {

  public static final int ROBOT_X_PARAM = 0;
  public static final int ROBOT_Y_PARAM = 1;
  public static final int ROBOT_Z_PARAM = 2;
  public static final int ROBOT_Y_ROTATE_PARAM = 3;
  
  public static final int MAX_PARAMS = 10;
  private Anim[] param;
  private int numParams;
  private double globalStartTime, localTime, repeatTime, savedLocalTime; 
    
  /**
   * Constructor.
   *
   * @param keys List of key info, i.e. list of pairs {key frame value, key parameter value}
   */    
  public AnimationScene() {
    param = new Anim[MAX_PARAMS];
    param[ROBOT_X_PARAM] = create(0.0, 30.0, true, true,  // robot x
                               new double[]{0.0,0.0, 0.1, 3.0, 0.2, -5.0, 0.3, -5.0, 0.5, -6.0, 0.6, 2.0, 0.7, 11.0, 0.8, 11.0, 1.0, 0.0});
    param[ROBOT_Y_PARAM] = create(0.0, 30.0, true, true,  // robot y
                               new double[]{0.0,0.0, 0.1,10.5, 0.2, 10.5, 0.3, 10.5, 0.5, -5.5, 0.6, -5.5, 0.7, -10.5, 0.8, -10.5, 1.0, 0.0});
    param[ROBOT_Z_PARAM] = create(0.0, 30.0, true, true,  // robot z
                               new double[]{0.0,0.0, 0.1,-5.0, 0.2, -5.0, 0.3,0.0, 0.5, 12.0, 0.6, 12.0, 0.7, 12.0, 0.8, 2.0, 1.0, 0.0});
    param[ROBOT_Y_ROTATE_PARAM] = create(0.0, 30.0, true, true,
                               new double[]{0.0,0.0, 0.1, 110, 0.2, 180, 0.5, 270, 0.7, 360, 0.8, 360, 0.9, 420, 1.0, 360});
    numParams = ROBOT_Y_ROTATE_PARAM+1;
    localTime = 0;
    savedLocalTime = 0;
    repeatTime = 30;
    globalStartTime = getSeconds();
  }
  
  public Anim create (double start, double duration, boolean pre, boolean post, double[] data) {
    KeyInfo[] k = new KeyInfo[data.length/2];
    for (int i=0; i<data.length/2; ++i) {
      k[i] = new KeyInfo(data[i*2], data[i*2+1]);
    }    
    return new Anim(start, duration, pre, post, k);
  }
  
  public void startAnimation() {
    globalStartTime = getSeconds() - savedLocalTime;
  }
  
  public void pauseAnimation() {
    savedLocalTime = getSeconds() - globalStartTime;
  }
  
  public void reset() {
    globalStartTime = getSeconds();
    savedLocalTime = 0;
    for (int i=0; i<numParams; ++i) {
      param[i].reset();
    }
  }
  
  private double getSeconds() {
    return System.currentTimeMillis()/1000.0;
  }
  
  /**
   * 
   */ 
  public void update() {
    localTime = getSeconds() - globalStartTime;
    if (localTime > repeatTime) {
      globalStartTime = getSeconds();
      localTime = 0;
      savedLocalTime = 0;
    }  
    for (int i=0; i<numParams; ++i) {
      param[i].update(localTime);
    }
  }

 /**
   * 
   *
   * @return The current parameter value
   */   
  public double getParam(int i) {
    if (i<0 || i>=numParams) {
      System.out.println("EEError: parameter out of range");
      return 0;
    }
    else {
      return param[i].getCurrValue();
    }
  }
  
  /**
   * Standard use of toString method
   * 
   * @return A string representing the key data
   */      
  public String toString() {
    String s = "Anim manager: ";
    return s;
  }
  
}
