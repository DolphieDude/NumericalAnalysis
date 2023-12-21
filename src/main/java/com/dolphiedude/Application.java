package com.dolphiedude;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {

    public Application(String title) {
        super(title);

        XYSeriesCollection dataset = getXySeriesCollection();

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Function and Derivative",
                "x",
                "y",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        // Customize the chart
        chart.setBackgroundPaint(Color.white);

        // Display the chart in a panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        setContentPane(chartPanel);
    }

    private static XYSeriesCollection getXySeriesCollection() {
        XYSeries seriesFunction = new XYSeries("Original Function");
        XYSeries seriesPartialDerivative = new XYSeries("Derivative");

        double a = 0;
        double b = 10;
        int numPoints = 100;

        double step = (b - a) / numPoints;

        for (double x = a; x <= b; x += step) {
            double y = Math.cos(x / 10) + Math.cos(x);
            double partialDerivative = -Math.sin(x / 10) / 10 - Math.sin(x);

            seriesFunction.add(x, y);
            seriesPartialDerivative.add(x, partialDerivative);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(seriesFunction);
        dataset.addSeries(seriesPartialDerivative);
        return dataset;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Application example = new Application("Andrii Cherevatyi TR-15");
            example.setSize(800, 600);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });
    }
}
