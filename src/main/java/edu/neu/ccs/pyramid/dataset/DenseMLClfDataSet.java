package edu.neu.ccs.pyramid.dataset;


/**
 * Created by chengli on 9/27/14.
 */
public class DenseMLClfDataSet extends DenseDataSet implements MultiLabelClfDataSet{
    private int numClasses;
    private MultiLabel[] multiLabels;
    private MLClfDataSetSetting dataSetSetting;
    private MLClfDataPointSetting[] dataPointSettings;


    public DenseMLClfDataSet(int numDataPoints, int numFeatures,
                             boolean missingValue, int numClasses){
        super(numDataPoints, numFeatures, missingValue);
        this.numClasses=numClasses;
        this.multiLabels=new MultiLabel[numDataPoints];
        for (int i=0;i<numDataPoints;i++){
            this.multiLabels[i]= new MultiLabel();
        }
        this.dataSetSetting = new MLClfDataSetSetting();
        this.dataPointSettings = new MLClfDataPointSetting[numDataPoints];
        for (int i=0;i<numDataPoints;i++){
            this.dataPointSettings[i] = new MLClfDataPointSetting();
        }
    }

    @Override
    public int getNumClasses() {
        return this.numClasses;
    }

    @Override
    public MultiLabel[] getMultiLabels() {
        return this.multiLabels;
    }

    @Override
    public void addLabel(int dataPointIndex, int classIndex) {
        this.multiLabels[dataPointIndex].addLabel(classIndex);
    }

    @Override
    public MLClfDataSetSetting getSetting() {
        return this.dataSetSetting;
    }

    @Override
    public MLClfDataPointSetting getDataPointSetting(int dataPointIndex) {
        return this.dataPointSettings[dataPointIndex];
    }

    @Override
    public void putDataSetSetting(MLClfDataSetSetting dataSetSetting) {
        this.dataSetSetting = dataSetSetting;
    }

    @Override
    public void putDataPointSetting(int dataPointIndex, MLClfDataPointSetting dataPointSetting) {
        this.dataPointSettings[dataPointIndex] = dataPointSetting;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("numClasses=").append(numClasses).append("\n");
        sb.append(super.toString());
        sb.append("labels").append("\n");
        for (int i=0;i<numDataPoints;i++){
            sb.append(i).append(":").append(multiLabels[i]).append(",");
        }
        return sb.toString();
    }

    @Override
    public String getMetaInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.getMetaInfo());
        sb.append("type = ").append("dense multi-label classification").append("\n");
        sb.append("number of classes = ").append(this.numClasses);
        return sb.toString();
    }
}
