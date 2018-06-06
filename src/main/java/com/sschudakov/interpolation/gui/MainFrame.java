/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sschudakov.interpolation.gui;

import com.sschudakov.interpolation.controller.DisplayButtonController;
import com.sschudakov.interpolation.dto.PlotConvertedParametersDTO;
import com.sschudakov.interpolation.dto.PlotStringParametersDTO;
import com.sschudakov.interpolation.evaluation.StringFunctionEvaluator;
import com.sschudakov.interpolation.generator.ChebishevPolynomialZerosITGenerator;
import com.sschudakov.interpolation.generator.EquallySpacedPointsITGenerator;
import com.sschudakov.interpolation.generator.InterpolatingPolynomialGenerator;
import com.sschudakov.interpolation.table.InterpolationTable;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.script.ScriptException;
import javax.swing.*;
import java.awt.*;

/**
 * @author Semen Chudakov
 */
public class MainFrame extends javax.swing.JFrame {

    private static final String FRAME_TITLE = "Function Interpolation";
    private static final String CHART_TITLE = "Interpolated Function";

    private static final Double DATASET_POINTS_SPACE = 0.01;

    private static final String X_AXIS_LABEL = "x";
    private static final String Y_AXIS_LABEL = "y";

    private static final String FUNCTION_DATASET_NAME = "funciton";
    private static final String EQUALLY_SPACED_DATASET_NAME = "equally spaced points";
    private static final String CHEBISHEV_DATASET_NAME = "Chebishev polynomial zeros points";

    private static final int FUNCTION_DATASET_INDEX = 0;
    private static final int EQUALLY_SPACED_DATASET_INDEX = 1;
    private static final int CHEBISHEV_DATASET_INDEX = 2;

    private static final int FUNCTION_SERIES_INDEX = 0;
    private static final int EQUALLY_SPACED_SERIES_INDEX = 0;
    private static final int CHEBISHEV_POLYNOMIAL_SERIES_INDEX = 0;

    private static final Color FUNCTION_DATASET_COLOR = Color.BLACK;
    private static final Color EQUALLY_SPACED_DATASET_COLOR = Color.RED;
    private static final Color CHEBISHEV_DATASET_COLOR = Color.BLUE;

    private XYPlot plot;

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
    }


    private void displayInterpolationCharts(String function, Double aEndpoint, Double bEndpoint,
                                            Integer numOfPoints, Integer polynomialDegree, Double datasetPointsSpace) {
        XYDataset functionDataset = createFunctionDataset(function, aEndpoint, bEndpoint, datasetPointsSpace);
        System.out.println("FUNCTION DATASET IS DONE");
        XYDataset equallySpacedInterpolationDataset = createEquallySpacedPointsDataset(function, aEndpoint, bEndpoint, numOfPoints, datasetPointsSpace);
        System.out.println("EQUALLY SPACED DATASET IS DONE");
        XYDataset chebishevDataset = createChebishevPolynomialDataset(function, aEndpoint, bEndpoint, polynomialDegree, datasetPointsSpace);
        System.out.println("CHEBISHEV DATASET IS DONE");

        final JFreeChart chart = ChartFactory.createXYAreaChart(
                CHART_TITLE,
                X_AXIS_LABEL,
                Y_AXIS_LABEL,
                functionDataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        this.plot = chart.getXYPlot();
        ValueAxis domainAxis = this.plot.getDomainAxis();
        domainAxis.setAutoRange(true);
        ValueAxis rangeAxis = this.plot.getRangeAxis();
//        rangeAxis.setLowerBound(-10);
//        rangeAxis.setUpperBound(10);

        StandardXYItemRenderer functionDatasetRenderer = new StandardXYItemRenderer();
        functionDatasetRenderer.setSeriesPaint(FUNCTION_SERIES_INDEX, FUNCTION_DATASET_COLOR);
        this.plot.setRenderer(FUNCTION_DATASET_INDEX, functionDatasetRenderer);

        StandardXYItemRenderer equallySpacedDatasetRenderer = new StandardXYItemRenderer();
        equallySpacedDatasetRenderer.setSeriesPaint(EQUALLY_SPACED_SERIES_INDEX, EQUALLY_SPACED_DATASET_COLOR);
        this.plot.setDataset(EQUALLY_SPACED_DATASET_INDEX, equallySpacedInterpolationDataset);
        this.plot.setRenderer(EQUALLY_SPACED_DATASET_INDEX, equallySpacedDatasetRenderer);

        StandardXYItemRenderer chebishevDatasetRenderer = new StandardXYItemRenderer();
        chebishevDatasetRenderer.setSeriesPaint(CHEBISHEV_POLYNOMIAL_SERIES_INDEX, CHEBISHEV_DATASET_COLOR);
        this.plot.setDataset(CHEBISHEV_DATASET_INDEX, chebishevDataset);
        this.plot.setRenderer(CHEBISHEV_DATASET_INDEX, chebishevDatasetRenderer);

        this.plot.setBackgroundPaint(Color.lightGray);
        this.plot.setDomainGridlinePaint(Color.white);
        this.plot.setRangeGridlinePaint(Color.white);

        this.chartPanel.setChart(chart);
        this.chartPanel.setSize(new Dimension(this.plotJP.getWidth(), this.plotJP.getHeight()));
        this.plotJP.add(chartPanel);
        this.plotJP.repaint();
        pack();
    }

    private XYDataset createFunctionDataset(String function, Double aEndpoint, Double bEndpoint, Double datasetPointsSpace) {
        return createDataset(
                function,
                aEndpoint,
                bEndpoint,
                datasetPointsSpace,
                FUNCTION_DATASET_NAME
        );
    }

    private XYDataset createEquallySpacedPointsDataset(String function, Double aEndpoint, Double bEndpoint, Integer numOfPoints, Double datasetPointsSpace) {
        EquallySpacedPointsITGenerator pointsITGenerator = new EquallySpacedPointsITGenerator();
        InterpolationTable equallySpacedPointsIT = pointsITGenerator.generate(
                function,
                aEndpoint,
                bEndpoint,
                numOfPoints
        );
        InterpolatingPolynomialGenerator polynomialGenerator = new InterpolatingPolynomialGenerator();
        String equallySpacedPointsPolynomial = polynomialGenerator.generate(equallySpacedPointsIT);

        return createDataset(
                equallySpacedPointsPolynomial,
                aEndpoint,
                bEndpoint,
                datasetPointsSpace,
                EQUALLY_SPACED_DATASET_NAME
        );
    }

    private XYDataset createChebishevPolynomialDataset(String function, Double aEndpoint, Double bEndpoint, Integer polynomialDegree, Double datasetPointsSpace) {
        ChebishevPolynomialZerosITGenerator pointsITGenerator = new ChebishevPolynomialZerosITGenerator();
        InterpolationTable equallySpacedPointsIL = pointsITGenerator.generate(
                function,
                aEndpoint,
                bEndpoint,
                polynomialDegree
        );
        InterpolatingPolynomialGenerator polynomialGenerator = new InterpolatingPolynomialGenerator();
        String equallySpacedPointsPolynomial = polynomialGenerator.generate(equallySpacedPointsIL);

        return createDataset(
                equallySpacedPointsPolynomial,
                aEndpoint,
                bEndpoint,
                datasetPointsSpace,
                CHEBISHEV_DATASET_NAME
        );
    }

    private XYDataset createDataset(String function, Double aEndPoint,
                                    Double bEndpoint, Double pointsSpace, String seriesName) {
        StringFunctionEvaluator evaluator = StringFunctionEvaluator.forFunction(function);

        XYSeriesCollection result = new XYSeriesCollection();
        XYSeries pointsSeries = new XYSeries(seriesName, true, false);
        for (double point = aEndPoint; point <= bEndpoint; point += pointsSpace) {
            try {
                pointsSeries.add(
                        point,
                        evaluator.count(point)
                );
            } catch (ScriptException e) {
                e.printStackTrace();
            }
        }
        result.addSeries(pointsSeries);
        return result;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        plotJP = new javax.swing.JPanel();
        inputJP = new javax.swing.JPanel();
        functionJL = new javax.swing.JLabel();
        aEndpointJL = new javax.swing.JLabel();
        bEndpointJL = new javax.swing.JLabel();
        functionTF = new javax.swing.JTextField();
        aEndpointsTF = new javax.swing.JTextField();
        bEndpointTF = new javax.swing.JTextField();
        numOfPointsJL = new javax.swing.JLabel();
        chebishevPolynomialDegreeJL = new javax.swing.JLabel();
        numOfPointsTF = new javax.swing.JTextField();
        chebishevPolynomialDegreeTF = new javax.swing.JTextField();
        displayJB = new javax.swing.JButton();
        datasetPointsSpaceJL = new javax.swing.JLabel();
        datasetPointsSpaceTF = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout plotJPLayout = new javax.swing.GroupLayout(plotJP);
        plotJP.setLayout(plotJPLayout);
        plotJPLayout.setHorizontalGroup(
                plotJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        plotJPLayout.setVerticalGroup(
                plotJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 392, Short.MAX_VALUE)
        );

        functionJL.setText("function");

        aEndpointJL.setText("a endpoint");

        bEndpointJL.setText("b endpoint");

        numOfPointsJL.setText("num of points");

        chebishevPolynomialDegreeJL.setText("chebishev polynomial degree");

        displayJB.setText("display");
        displayJB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayJBActionPerformed(evt);
            }
        });

        datasetPointsSpaceJL.setText("dataset points space");

        javax.swing.GroupLayout inputJPLayout = new javax.swing.GroupLayout(inputJP);
        inputJP.setLayout(inputJPLayout);
        inputJPLayout.setHorizontalGroup(
                inputJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(inputJPLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(inputJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(inputJPLayout.createSequentialGroup()
                                                .addComponent(functionJL, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(functionTF, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(numOfPointsJL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(inputJPLayout.createSequentialGroup()
                                                .addComponent(aEndpointJL, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(aEndpointsTF, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(chebishevPolynomialDegreeJL, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(inputJPLayout.createSequentialGroup()
                                                .addGroup(inputJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(displayJB, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(inputJPLayout.createSequentialGroup()
                                                                .addComponent(bEndpointJL, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(bEndpointTF, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(18, 18, 18)
                                                .addComponent(datasetPointsSpaceJL, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(18, 18, 18)
                                .addGroup(inputJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(datasetPointsSpaceTF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(chebishevPolynomialDegreeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(numOfPointsTF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        inputJPLayout.setVerticalGroup(
                inputJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(inputJPLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(inputJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(inputJPLayout.createSequentialGroup()
                                                .addGroup(inputJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(numOfPointsTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(inputJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(functionJL, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(functionTF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(numOfPointsJL, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(inputJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(inputJPLayout.createSequentialGroup()
                                                                .addGap(20, 20, 20)
                                                                .addComponent(aEndpointJL, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(inputJPLayout.createSequentialGroup()
                                                                .addGap(18, 18, 18)
                                                                .addComponent(chebishevPolynomialDegreeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(inputJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(aEndpointsTF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(chebishevPolynomialDegreeJL, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(inputJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(bEndpointJL, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bEndpointTF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(datasetPointsSpaceJL, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(datasetPointsSpaceTF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                                .addComponent(displayJB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(plotJP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(inputJP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(plotJP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inputJP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void displayJBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayJBActionPerformed
        PlotStringParametersDTO stringParametersDTO = new PlotStringParametersDTO();

        stringParametersDTO.setFunctionString(this.functionTF.getText());
        stringParametersDTO.setaEndpointString(this.aEndpointsTF.getText());
        stringParametersDTO.setbEndpointString(this.bEndpointTF.getText());
        stringParametersDTO.setNumOfPointsString(this.numOfPointsTF.getText());
        stringParametersDTO.setPolynomialDegreeString(this.chebishevPolynomialDegreeTF.getText());
        stringParametersDTO.setDatasetPointsSpaceString(this.datasetPointsSpaceTF.getText());

        PlotConvertedParametersDTO convertedParametersDTO = null;
        try {
            convertedParametersDTO = this.controller.verifyPlotParameters(stringParametersDTO);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        displayInterpolationCharts(
                convertedParametersDTO.getFunction(),
                convertedParametersDTO.getaEndpoint(),
                convertedParametersDTO.getbEndpoint(),
                convertedParametersDTO.getNumOfPoints(),
                convertedParametersDTO.getPolynomialDegree(),
                convertedParametersDTO.getDatasetPointsSpace()
        );

    }//GEN-LAST:event_displayJBActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    private DisplayButtonController controller = new DisplayButtonController();
    private ChartPanel chartPanel = new ChartPanel(null);
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel aEndpointJL;
    private javax.swing.JTextField aEndpointsTF;
    private javax.swing.JLabel bEndpointJL;
    private javax.swing.JTextField bEndpointTF;
    private javax.swing.JLabel chebishevPolynomialDegreeJL;
    private javax.swing.JTextField chebishevPolynomialDegreeTF;
    private javax.swing.JLabel datasetPointsSpaceJL;
    private javax.swing.JTextField datasetPointsSpaceTF;
    private javax.swing.JButton displayJB;
    private javax.swing.JLabel functionJL;
    private javax.swing.JTextField functionTF;
    private javax.swing.JPanel inputJP;
    private javax.swing.JLabel numOfPointsJL;
    private javax.swing.JTextField numOfPointsTF;
    private javax.swing.JPanel plotJP;
    // End of variables declaration//GEN-END:variables
}
