package test;

import AirMap.AirMap;
import com.mathworks.toolbox.javabuilder.MWArray;
import com.mathworks.toolbox.javabuilder.MWClassID;
import com.mathworks.toolbox.javabuilder.MWNumericArray;

/**
 * Created by honey on 17. 4. 14.
 */
public class GenAirMap {
    public void Gen_AirMap(){
        MWNumericArray n = null;
        MWNumericArray region_n = null;
        Object[] result_step1_1 = null;
        Object[] result_step1_2 = null;
        Object[] result_step1_3 = null;
        Object[] result_step2 = null;
        Object[] result_step2_1 = null;
        Object[] result_step2_2 = null;
        Object[] result_step2_3 = null;
        Object[] result_step3 = null;
        AirMap airMap = null;
        try {
            double beginTime = System.currentTimeMillis();
            airMap = new AirMap();
            for (int o = 0; o <= 1; o++){
                System.out.println("region_n = " +o);
                region_n = new MWNumericArray(Double.valueOf(o), MWClassID.DOUBLE);
                System.out.println("### Step1_1 Start ###");
                result_step1_1 = airMap.step1_1(5);
                System.out.println("### Step1_2 Start ###");
                result_step1_2 = airMap.step1_2(5);
                System.out.println("### Step1_3 Start ###");
                result_step1_3 = airMap.step1_3(result_step1_2[1], result_step1_2[3], result_step1_2[4],
                        region_n);
                System.out.println("### Step2 Start ###");
                result_step2 = airMap.step2(1,region_n);
                for (int i = 1; i <=51; i++){
                    n = new MWNumericArray(Double.valueOf(i), MWClassID.DOUBLE);
                    result_step2_1 = airMap.step2_1(1, result_step2[0], n, region_n);
                    result_step2_2 = airMap.step2_2(2, result_step2_1[0], 0.1, result_step2[0]);
                    result_step2_3 = airMap.step2_3(result_step1_2[0], result_step1_2[3], result_step2[0],
                            result_step2_2[0], n, region_n);
                }
                result_step3 = airMap.step3(result_step1_1[0], result_step1_1[1], result_step1_1[2],
                        result_step1_1[4], result_step2[0], region_n);

            }
            double endTime = System.currentTimeMillis();
            System.out
                    .println("------------------------------------------------------");
            System.out.println("Making the Noise Map took " + (endTime - beginTime) / 1000
                    + " seconds.");
            System.out
                    .println("------------------------------------------------------");
            System.exit(0);
        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e.toString());
        }
        finally {
            MWArray.disposeArray(n);
            MWArray.disposeArray(result_step1_1);
            MWArray.disposeArray(result_step1_2);
            MWArray.disposeArray(result_step1_3);
            MWArray.disposeArray(result_step2);
            MWArray.disposeArray(result_step2_1);
            MWArray.disposeArray(result_step2_2);
            MWArray.disposeArray(result_step2_3);
            MWArray.disposeArray(region_n);
        }
    }
}
